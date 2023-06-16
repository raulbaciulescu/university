package exam.server;

import exam.services.IExamServices;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExamServicesImpl implements IExamServices {
    /*private IGameRepository repoGames;
    private IConfigurationRepository repoConf;
    private IPlayerRepository repoPlayers;
    private Map<String, IModelObserver> loggedPlayers;

    public ExamServicesImpl(IGameRepository repoGames, IConfigurationRepository repoConf, IPlayerRepository repoPlayers) {
        this.repoGames = repoGames;
        this.repoConf = repoConf;
        this.repoPlayers = repoPlayers;
        loggedPlayers=new ConcurrentHashMap<>();
    }
    */

    /*@Override
    public Player login(String alias, IModelObserver client) throws ModelException {
        Player foundPlayer = repoPlayers.findById(alias);
        if (foundPlayer != null) {
            if (loggedPlayers.get(foundPlayer.getID()) != null)
                throw new ModelException("Player already logged in.");
            loggedPlayers.put(foundPlayer.getID(), client);
            return foundPlayer;
        } else
            throw new ModelException("Authentication failed.");
    }

    @Override
    public void logout(String alias, IModelObserver client) throws ModelException {
        IModelObserver localClient = loggedPlayers.remove(alias);
        if (localClient == null)
            throw new ModelException("Player " + alias + " is not logged in.");

    }

    @Override
    public Game startGame(String alias, LocalDateTime date) throws ModelException {
        Configuration conf=repoConf.getRandomConfig();
        Game game=new Game(null, conf.getID(),alias,date,50,0,"-----",-1);
        Game newGame=repoGames.add(game);
        if (newGame != null) {
            return newGame;
        } else
            throw new ModelException("Start failed.");
    }

    @Override
    public Game makeMove(Game game, int step, int finished) throws ModelException {
        game.setFinished(finished);
        boolean plus=false;
        if(game.getCurPoz()+step>4)
            plus=true;
        if(plus)
            game.setSum(game.getSum()+5);
        int newPoz=(game.getCurPoz()+step)%5;
        game.setCurPoz(newPoz);
        String state=game.getState();
        Configuration conf=repoConf.findById(game.getId_conf());
        if(state.charAt(newPoz)=='-'){
            int value=getValue(newPoz,conf);
            game.setSum(game.getSum()-value);
            StringBuilder stateNew = new StringBuilder(state);
            stateNew.setCharAt(newPoz, 'x');
            game.setState(String.valueOf(stateNew));
        }
        System.out.println(game);
        repoGames.update(game);
        return repoGames.findById(game.getID());
    }

    @Override
    public Configuration getConfiguration(Integer id_conf) throws ModelException {
        Configuration conf=repoConf.findById(id_conf);

        if (conf != null) {
            return conf;
        } else
            throw new ModelException("Conf not found.");
    }

    private int getValue(int poz,Configuration conf){
        if(poz==0)
            return conf.getPoz1();
        else if(poz==1)
            return conf.getPoz2();
        else if(poz==2)
            return conf.getPoz3();
        else if(poz==3)
            return conf.getPoz4();
        else
            return conf.getPoz5();
    }

    @Override
    public List<GameDTO> getLeaderBoard() throws ModelException {
        List<Game> games=repoGames.getFinished();
        List<GameDTO> gamesDTO=games.stream().sorted(Comparator.comparingInt(Game::getSum).reversed()).map(game->new GameDTO(game.getAlias(),game.getDate(), game.getSum())).toList();


        return gamesDTO;
    }
    */

}
