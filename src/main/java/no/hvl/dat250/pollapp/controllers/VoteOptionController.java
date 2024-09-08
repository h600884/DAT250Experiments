package no.hvl.dat250.pollapp.controllers;

import io.swagger.v3.oas.models.security.SecurityScheme;
import no.hvl.dat250.pollapp.Managers.DomainManager;
import no.hvl.dat250.pollapp.models.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voteoptions")
public class VoteOptionController {

    @Autowired
    private DomainManager repo;

    @PostMapping
    public ResponseEntity<VoteOption> createVoteOption(@RequestBody VoteOption voteOption) {
        VoteOption createdOption = repo.createVoteOption(voteOption);
        return new ResponseEntity<>(createdOption, HttpStatus.CREATED);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<VoteOption> getVoteOption(@PathVariable Integer optionId) {
        VoteOption voteOption = repo.getVoteOption(optionId);
        return new ResponseEntity<>(voteOption, HttpStatus.OK);
    }

    @GetMapping
    public List<VoteOption> getAllVoteOptions() {
        return repo.getAllVoteOptions();
    }

    @PutMapping("/{optionId}")
    public ResponseEntity<Void> updateVoteOption(@PathVariable Integer optionId, @RequestBody VoteOption voteOption) {
        VoteOption updatedOption = repo.updateVoteOption(optionId, voteOption);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<Void> deleteVoteOption(@PathVariable Integer optionId) {
        repo.deleteVoteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}

