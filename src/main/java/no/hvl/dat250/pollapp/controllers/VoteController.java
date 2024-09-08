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
        Vote newVote = repo.addVoteOnOption(vote.getUsername(), vote.getPollId(), vote.getVoteOptionId(), vote.getPublishedAt());
        return new ResponseEntity<>(newVote, HttpStatus.CREATED);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<Vote> getVote(@PathVariable Integer voteId) {
        Vote vote = repo.getVote(voteId);
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @GetMapping
    public List<Vote> getAllVotes() {
        return repo.getAllVotes();
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<Vote> updateVote(@PathVariable Integer voteId, @RequestBody Vote vote) {
        Vote updatedVote = repo.updateVote(voteId, vote);
        return new ResponseEntity<>(updatedVote, HttpStatus.OK);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> deleteVote(@PathVariable Integer voteId) {
        repo.deleteVote(voteId);
        return ResponseEntity.noContent().build();
    }
}


