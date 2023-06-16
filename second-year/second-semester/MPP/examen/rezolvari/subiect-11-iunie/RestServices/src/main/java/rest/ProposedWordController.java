package rest;


import api.ProposedWordRepository;
import domain.ProposedWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/proposed-word")
public class ProposedWordController {
    private static final String template = "Hello, %s!";

    @Autowired
    private ProposedWordRepository proposedWordRepository;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("hello");
        return String.format(template, name);
    }

    @RequestMapping("/{id}")
    public List<ProposedWord> findByGameId(@PathVariable Integer id) {
        System.out.println("Find by game id ...");
        return proposedWordRepository.findByGameId(id);
    }
}
