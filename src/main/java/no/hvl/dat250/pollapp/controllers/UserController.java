package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Managers.DomainManager;
import no.hvl.dat250.pollapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private DomainManager repo;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = repo.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = repo.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(repo.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        repo.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

