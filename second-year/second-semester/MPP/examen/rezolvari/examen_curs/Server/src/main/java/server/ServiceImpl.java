package server;

import api.*;
import domain.*;
import services.LoginException;
import services.Observer;
import services.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements Service {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final ProposalRepository proposalRepository;

    private final Map<Integer, Observer> loggedUsers;
    private final List<Integer> playersId;

    private static Integer noOfPlayers = 0;
    private static Integer round = 0;
    private static Integer playersPerRound = 0;

    private static Game currentGame;
    private final ConfigurationRepository configurationRepository;

    public ServiceImpl(PlayerRepository playerRepository,
                       GameRepository gameRepository, ProposalRepository proposalRepository,
                       ConfigurationRepository configurationRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.proposalRepository = proposalRepository;
        this.configurationRepository = configurationRepository;
        this.loggedUsers = new ConcurrentHashMap<>();
        playersId = new ArrayList<>();
    }

    @Override
    public synchronized void login(Player player, Observer client) throws LoginException {
        Optional<Player> playerOptional = playerRepository.findPlayer(player.getAlias());
        if (playerOptional.isPresent()) {
            loggedUsers.put(playerOptional.get().getId(), client);
            playersId.add(playerOptional.get().getId());
        }
        else
            throw new LoginException("Authentication failed!");
    }

    @Override
    public synchronized void logout(Player player, Observer client) throws LoginException {
        Optional<Player> playerOptional = playerRepository.findPlayer(player.getAlias());
        if (playerOptional.isPresent()) {
            Observer client1 = loggedUsers.remove(playerOptional.get().getId());
            if (client1 == null) {
                throw new LoginException("User " + player.getAlias() + " is not logged in.");
            }
        }
    }

    @Override
    public synchronized GameDTO startGame(String alias) {
        // get a random configuration
        Random random = new Random();
        List<Configuration> configurations = configurationRepository.getAll();
        int pos = random.nextInt(configurations.size());
        Configuration configuration = configurations.get(pos);

        Game game = new Game(50, configuration.getId(), false,
                LocalDateTime.now(), alias, 0);
        gameRepository.add(game);

        round = 1;
        List<Boolean> visited = new ArrayList<>();
        visited.add(false);
        visited.add(false);
        visited.add(false);
        visited.add(false);
        visited.add(false);
        return new GameDTO(game, configuration, visited);
    }

    @Override
    //public synchronized void updateGame(UpdateObject updateObject) {
    public synchronized void updateGame(Game game) {
        round++;
        if (game.getIsFinished()) {
            notifyPlayers();
        }
        gameRepository.update(game, game);
    }

    private void notifyPlayers() {
        int defaultThreadsNo = 5;
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for (Observer observer: loggedUsers.values()) {
            executor.execute(() -> {
                observer.updateResults(getFinishedGames());
            });
        }
        executor.shutdown();
    }

    @Override
    public synchronized List<Game> getFinishedGames() {
        List<Game> games = gameRepository.getAll();
        List<Game> finishedGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getIsFinished())
                finishedGames.add(game);
        }
        return finishedGames;
    }
}
