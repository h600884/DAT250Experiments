package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Exception.VoteOptionNotFoundException;
import no.hvl.dat250.pollapp.Managers.PollManager;
import no.hvl.dat250.pollapp.models.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voteOptions")
@CrossOrigin
public class VoteOptionController {

    @Autowired
    private PollManager repo;

    @PostMapping
    public ResponseEntity<VoteOption> createVoteOption(@RequestBody VoteOption voteOption) {
        VoteOption createdVoteOption = repo.createVoteOption(voteOption);
        return new ResponseEntity<>(createdVoteOption, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteOption> getVoteOption(@PathVariable Integer id) {
        VoteOption voteOption = repo.getVoteOption(id);
        return new ResponseEntity<>(voteOption, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VoteOption>> getAllVoteOptions() {
        return new ResponseEntity<>(repo.getAllVoteOptions(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoteOption> updateVoteOption(@PathVariable Integer id, @RequestBody VoteOption voteOption) {
        try {
            VoteOption updatedVoteOption = repo.updateVoteOption(id, voteOption);
            return new ResponseEntity<>(updatedVoteOption, HttpStatus.OK);
        } catch (VoteOptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable Integer id) {
        try {
            repo.deleteVoteOption(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (VoteOptionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

