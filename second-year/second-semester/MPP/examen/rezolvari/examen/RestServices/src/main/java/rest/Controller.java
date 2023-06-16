package rest;

import api.ConfigurationRepository;
import api.GameRepository;
import api.PlayerRepository;
import api.ProposalRepository;
import domain.*;
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
    @Autowired
    private ProposalRepository proposalRepository;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("hello");
        return String.format(template, name);
    }

    @RequestMapping("/players")
    public List<Player> getAllPlayers() {
        System.out.println("get all players...");
        return playerRepository.getAll();
    }

    @RequestMapping("/games")
    public List<Game> getAllGames() {
        System.out.println("get all games...");
        return gameRepository.getAll();
    }

    @RequestMapping("/configuration")
    public List<Configuration> getAllConfigurations() {
        System.out.println("get all config...");
        return configurationRepository.getAll();
    }

    @RequestMapping("/games/{alias}")
    public List<GameRest> getGamesByAlias(@PathVariable String alias) {
        Player player = playerRepository.findPlayer(alias).get();
        List<Game> games = gameRepository.getGamesByAlias(alias);
        List<GameRest> finishedGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getFinish()) {
                List<Proposal> proposals = proposalRepository.getGhicite(game.getId(),
                        player.getId());
                Integer len = proposals.size();
                finishedGames.add(new GameRest(game, proposals, len));
            }
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
}

