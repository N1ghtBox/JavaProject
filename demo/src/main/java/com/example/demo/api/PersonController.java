package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "api/v1/person",
                consumes={"application/json"})
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<User> getAllPeople() {
        return personService.getAllPeople();
    }

    @PostMapping
    public void RegisterNewUser(@Valid @RequestBody User user) {
        System.out.println(user);
    }

}
