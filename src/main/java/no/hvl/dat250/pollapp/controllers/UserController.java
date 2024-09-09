package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Managers.PollManager;
import no.hvl.dat250.pollapp.Exception.UserNotFoundException;
import no.hvl.dat250.pollapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PollManager repo;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = repo.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = repo.getUser(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(repo.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user) {
        try {
            User updatedUser = repo.updateUser(username, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        repo.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

