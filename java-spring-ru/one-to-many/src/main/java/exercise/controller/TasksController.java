package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public List<TaskDTO> getTasks() {
        List<TaskDTO> taskDTOS = taskRepository.findAll().stream()
                .map(x -> taskMapper.map(x))
                .toList();

        return taskDTOS;
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable long id) {
        TaskDTO taskDTO = taskMapper.map(
                taskRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")
                        )
        );

        return taskDTO;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        taskRepository.save(taskMapper.map(taskCreateDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id) {
        taskRepository.delete(taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found")));
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable long id, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
        taskMapper.update(taskUpdateDTO, task);

        task.setAssignee(userRepository.findById(taskUpdateDTO.getAssigneeId()).orElseThrow(() -> new ResourceNotFoundException(id + " not found")));

        taskRepository.save(task);

        var taskDTO = taskMapper.map(task);

        return taskDTO;
    }

    // END
}
