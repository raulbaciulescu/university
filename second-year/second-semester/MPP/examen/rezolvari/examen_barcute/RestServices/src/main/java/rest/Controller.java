package rest;

import api.PlayerRepository;
import domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/exam")
public class Controller {
    private static final String template = "Hello, %s!";

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("hello");
        return String.format(template, name);
    }

    @RequestMapping("/players")
    public List<Player> getAll() {
        System.out.println("get all ...");
        return playerRepository.getAll();
    }
}

