package no.hvl.dat250.pollapp.controllers;

import no.hvl.dat250.pollapp.Managers.DomainManager;
import no.hvl.dat250.pollapp.models.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vote-options")
public class VoteOptionController {

    @Autowired
    private DomainManager repo;

    @PostMapping
    public ResponseEntity<VoteOption> createVoteOption(@RequestBody VoteOption voteOption) {
        VoteOption createdOption = repo.addVoteOption(voteOption);
        return new ResponseEntity<>(createdOption, HttpStatus.CREATED);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<VoteOption> getVoteOption(@PathVariable String optionId) {
        Optional<VoteOption> voteOption = repo.getVoteOption(optionId);
        return voteOption.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<VoteOption> getAllVoteOptions() {
        return repo.getAllVoteOptions();
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable String optionId) {
        repo.deleteVoteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}

