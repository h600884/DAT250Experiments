package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Exception.PollNotFoundException;
import no.hvl.dat250.pollapp.Exception.UserNotFoundException;
import no.hvl.dat250.pollapp.Exception.VoteNotFoundException;
import no.hvl.dat250.pollapp.Exception.VoteOptionNotFoundException;
import no.hvl.dat250.pollapp.Managers.PollManager;
import no.hvl.dat250.pollapp.models.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private PollManager repo;

    @PostMapping
    public ResponseEntity<Vote> voteOnOption(@RequestBody Vote vote) {
        try {
            Vote newVote = repo.voteOnOption(vote.getUsername(), vote.getPollId(), vote.getVoteOptionId(), vote.getPublishedAt());
            return new ResponseEntity<>(newVote, HttpStatus.CREATED);
        } catch (UserNotFoundException | PollNotFoundException | VoteOptionNotFoundException | IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVote(@PathVariable Integer id) {
        Vote vote = repo.getVote(id);
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        return new ResponseEntity<>(repo.getAllVotes(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable Integer id, @RequestBody Vote vote) {
        try {
            Vote updatedVote = repo.updateVote(id, vote);
            return new ResponseEntity<>(updatedVote, HttpStatus.OK);
        } catch (VoteNotFoundException | VoteOptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Integer id) {
        repo.deleteVote(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


