package exam.services.rest;


import exam.model.Configuration;
import exam.model.Game;
import exam.model.Player;
import exam.model.dto.GameDTO;
import exam.model.dto.GameForLeaderBoard;
import exam.persistence.db.ConfigurationDbRepository;
import exam.persistence.db.GameDbRepository;
import exam.persistence.db.PlayerDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/exam")
public class RestController {


    // sa pui @Component la repo-uri
    @Autowired
    private GameDbRepository repoGames;

    @Autowired
    private ConfigurationDbRepository repoConfigurations;

    @Autowired
    private PlayerDbRepository repoPlayers;


    @RequestMapping(value="/configurations",method=RequestMethod.POST)
    public Configuration createConf(@RequestBody Configuration config){
        System.out.println("adding config..."+ config);
        repoConfigurations.add(config);
        //System.out.println(config.getId());
        return config;
    }

    @RequestMapping(value="/players/{alias}",method = RequestMethod.GET)
    public Player login(@PathVariable String alias){
        Player player=repoPlayers.findById(alias);
        return player;
    }

    @RequestMapping(value="/games",method = RequestMethod.POST)
    public GameDTO startGame(@RequestBody Game game){
        Configuration conf=repoConfigurations.getRandomConfig();
        game.setIdConf(conf.getID());
        repoGames.add(game);
        GameDTO gameDTO=new GameDTO(game,conf);
        return gameDTO;
    }

    @RequestMapping(value="/games/leaderboard",method=RequestMethod.GET)
    public List<GameForLeaderBoard> getLeaderBoard(){
        List<GameForLeaderBoard> games=repoGames.getFinished().stream().sorted(Comparator.comparingInt(Game::getPoints).reversed())
                .map(game->new GameForLeaderBoard(game.getAlias(),game.getDate(),game.getPoints(),game.getValue())).toList();
        return games;
    }

    @RequestMapping(value="/games/modified",method = RequestMethod.POST)
    public Game update(@RequestBody Game game){
        repoGames.update(game);
        Game gameNew=repoGames.findById(game.getID());
        return gameNew;
    }


   /* @RequestMapping(value="/players/{alias}/games",method = RequestMethod.GET)
    public ResponseEntity<?> getByAlias(@PathVariable String alias){
        System.out.println("Get by alias "+alias);
        List<Game> games=repoGames.findFinishedForPlayer(alias);
        if(games.size()==0)
            return new ResponseEntity<String>("Games not found!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<Game>>(games,HttpStatus.OK);
    }
    */
    //Request cu filter
    @RequestMapping(value="/games",method = RequestMethod.GET)
    public ResponseEntity<?> getByAlias(@RequestParam(value="alias",defaultValue="") String alias){
        System.out.println("Get by alias "+alias);
        List<Game> games=repoGames.findFinishedForPlayer(alias);
        if(games.size()==0)
            return new ResponseEntity<String>("Games not found!", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<List<Game>>(games,HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET)
    public String create(){
        System.out.println("test...");

        return "End point de test";
    }





    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String eventError(ServiceException ex) {
        return ex.getMessage();
    }
}
