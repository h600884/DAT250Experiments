package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Managers.DomainManager;
import no.hvl.dat250.pollapp.models.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private DomainManager repo;

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
        Vote createdVote = repo.addVote(vote);
        return new ResponseEntity<>(createdVote, HttpStatus.CREATED);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<Vote> getVote(@PathVariable String voteId) {
        Optional<Vote> vote = repo.getVote(voteId);
        return vote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return repo.getAllVotes();
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVote(@PathVariable String voteId) {
        repo.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }
}


