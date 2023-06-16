package rest;

import api.ConfigurationRepository;
import api.GameRepository;
import api.PlayerRepository;
import domain.Configuration;
import domain.Game;
import domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/exam")
public class Controller {
    private static final String template = "Hello, %s!";

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;

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

    @RequestMapping("/games/{alias}")
    public List<Game> getFinishedGamesByAlias(@PathVariable String alias) {
        List<Game> games = gameRepository.getFinishedGamesByAlias(alias);
        List<Game> finishedGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getFinish())
                finishedGames.add(game);
        }
        return finishedGames;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Configuration add(@RequestBody Configuration configuration){
        System.out.println("Add configuration ...");
        System.out.println(configuration);
        configurationRepository.add(configuration);
        return configuration;
    }

    @RequestMapping("/configuration")
    public List<Configuration> getAllConfigurations() {
        System.out.println("get all ...");
        return configurationRepository.getAll();
    }
}

