package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Managers.DomainManager;
import no.hvl.dat250.pollapp.models.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private DomainManager repo;

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = repo.addPoll(poll);
        return new ResponseEntity<>(createdPoll, HttpStatus.CREATED);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<Poll> getPoll(@PathVariable Integer pollId) {
        Poll poll = repo.getPoll(pollId);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return repo.getAllPolls();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Integer id, @RequestBody Poll poll) {
            Poll updatedPoll = repo.updatePoll(id, poll);
            return new ResponseEntity<>(updatedPoll, HttpStatus.OK);
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<Void> deletePoll(@PathVariable Integer pollId) {
        repo.deletePoll(pollId);
        return ResponseEntity.noContent().build();
    }
}

