package exercise.controller;

import exercise.model.Person;
import exercise.dto.PersonDto;
import exercise.repository.PersonRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path="")
    public void createPerson(@RequestBody PersonDto person) {
        Person person1 = new Person();
        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        this.personRepository.save(person1);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        this.personRepository.deleteById(id);
    }

    @PatchMapping(path = "{id}")
    public void patchPerson(@PathVariable long id, @RequestBody PersonDto person) {
        Person person1 = this.personRepository.findById(id).orElseThrow();
        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        this.personRepository.save(person1);
    }
    // END
}
