package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO postNewContact(@RequestBody ContactCreateDTO contactCreateDTO) {

        Contact newContact = new Contact();

        newContact.setPhone(contactCreateDTO.getPhone());
        newContact.setFirstName(contactCreateDTO.getFirstName());
        newContact.setLastName(contactCreateDTO.getLastName());

        contactRepository.save(newContact);

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setFirstName(newContact.getFirstName());
        contactDTO.setPhone(newContact.getPhone());
        contactDTO.setLastName(newContact.getLastName());
        contactDTO.setId(newContact.getId());
        contactDTO.setCreatedAt(newContact.getCreatedAt());
        contactDTO.setUpdatedAt(newContact.getUpdatedAt());

        return contactDTO;

    }
    // END
}
