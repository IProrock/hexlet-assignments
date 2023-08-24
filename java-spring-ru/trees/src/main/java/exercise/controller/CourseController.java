package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public List<Course> getPrevious(@PathVariable long id) {

        List<Course> result = new ArrayList<>();
        Course course = courseRepository.findById(id);

        if (course == null) {
            return result;
        }

        String parents = course.getPath();

        if (parents == null) {
            return result;
        }

        String[] strPartents = parents.split("\\.");

        List<Course> lst = Arrays.stream(strPartents)
                .map(prnt -> courseRepository.findById(Long.parseLong(prnt)))
                .collect(Collectors.toList());

        return lst;
    }

    // END

}
