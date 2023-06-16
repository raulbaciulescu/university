package exam.client;

import exam.model.Game;
import exam.model.Player;
import exam.model.dto.GameDTO;
import exam.model.dto.GameForLeaderBoard;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import exam.services.rest.ServiceException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;

public class RestClient {
    public static final String URL = "http://localhost:8080/exam";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    //public Contest[] getAll(){return execute(()->restTemplate.getForObject(URL,Contest[].class));}

    public Player loginPlayer(String alias){
        return restTemplate.getForObject(String.format("%s/players/%s",URL,alias),Player.class);
    }

    public GameDTO startGame(String alias, LocalDateTime date){
        Game game=new Game(null,-1,alias,date);
        return restTemplate.postForObject(String.format("%s/games",URL),game, GameDTO.class);
    }

    public Game updateGame(Game game){
        return restTemplate.postForObject(String.format("%s/games/modified",URL),game,Game.class);
    }

    public GameForLeaderBoard[] getLeaderBoard(){
        return restTemplate.getForObject(URL+"/games/leaderboard",GameForLeaderBoard[].class);
    }
    /*public Contest create(Contest contest){
        return execute(()->{var res=restTemplate.postForObject(URL,contest,Contest.class);
            //System.out.println(res.getId());
            return res;
        });
    }

    public void update(Contest contest){
        execute(()->{
            restTemplate.put(String.format("%s/%s",URL,contest.getId()),contest);
            return null;
        });
    }

    public void delete(Integer id){
        execute(()->{
            restTemplate.delete(String.format("%s/%s",URL,id));
            return null;
        });
    }

     */
}
