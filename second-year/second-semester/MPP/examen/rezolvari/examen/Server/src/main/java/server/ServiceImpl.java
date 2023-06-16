package server;

import api.ConfigurationRepository;
import api.GameRepository;
import api.PlayerRepository;
import api.ProposalRepository;
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
    private final ConfigurationRepository configurationRepository;

    private final Map<Integer, Observer> loggedUsers;
    private Configuration configuration;

    public ServiceImpl(PlayerRepository playerRepository, GameRepository gameRepository,
                       ProposalRepository proposalRepository, ConfigurationRepository configurationRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.proposalRepository = proposalRepository;
        this.configurationRepository = configurationRepository;
        this.loggedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void login(Player player, Observer client) throws LoginException {
        Optional<Player> playerOptional = playerRepository.findPlayer(player.getAlias());
        if (playerOptional.isPresent()) {
            loggedUsers.put(playerOptional.get().getId(), client);
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
        configuration = configurations.get(pos);

        Game game = new Game(0, configuration.getId(), false,
                LocalDateTime.now(), alias);
        gameRepository.add(game);

        return new GameDTO(game, configuration);
    }

    @Override
    public synchronized void updateGame(Game game) {
        Player player = playerRepository.findPlayer(game.getAlias()).get();
        Proposal proposal;
        if (Objects.equals(game.getWord(), configuration.getWord1()))
            proposal = new Proposal(game.getId(), game.getWord(), player.getId(),
                    true);
        else if (Objects.equals(game.getWord(), configuration.getWord2()))
            proposal = new Proposal(game.getId(), game.getWord(), player.getId(),
                    true);
        else if (Objects.equals(game.getWord(), configuration.getWord3()))
            proposal = new Proposal(game.getId(), game.getWord(), player.getId(),
                    true);
        else
            proposal = new Proposal(game.getId(), game.getWord(), player.getId(),
                    false);

        if (game.getFinish()) {
            notifyPlayers();
        }
        proposalRepository.add(proposal);
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

//    @Override
    public synchronized List<Game> getFinishedGames() {
        List<Game> games = gameRepository.getAll();
        List<Game> finishedGames = new ArrayList<>();
        for (Game game : games) {
            if (game.getFinish())
                finishedGames.add(game);
        }
        return finishedGames;
    }
}
