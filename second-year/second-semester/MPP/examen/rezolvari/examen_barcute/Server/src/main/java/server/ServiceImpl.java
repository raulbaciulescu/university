package server;

import api.GameRepository;
import api.PlayerRepository;
import api.ProposalRepository;
import domain.Game;
import domain.GameDTO;
import domain.Player;
import domain.Proposal;
import services.LoginException;
import services.Observer;
import services.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private int players = 0;

    public ServiceImpl(PlayerRepository playerRepository,
                       GameRepository gameRepository, ProposalRepository proposalRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.proposalRepository = proposalRepository;
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

//    @Override
//    public synchronized void startGame(ProposedWord proposedWord, Observer mainCtrl) {
//        if (noOfPlayers == 0) {
//            currentGame = new Game("joc");
//            gameRepository.add(currentGame);
//            currentGame = gameRepository.findLastGame().get();
//        }
//        proposedWord.setGameId(currentGame.getId());
//        proposedWordRepository.add(proposedWord);
//
//        noOfPlayers++;
//        if (noOfPlayers == 3) {
//            round = 1;
//            Collections.sort(playersId);
//            notifyStart();
//        }
//    }
//
//    @Override
//    public synchronized void move(Proposal proposal) {
//        playersPerRound++;
//        proposalRepository.add(proposal);
//        updatePositions(proposal);
//        if (playersPerRound == 3) { // trec la runda urmatoare
//            notifyNextRound();
//            round++;
//            playersPerRound = 0;
//        }
//    }
//
//    public synchronized void updatePositions(Proposal proposal) {
//        Optional<ProposedWord> proposedWordOptional = proposedWordRepository.findByID(proposal.getWordId());
//        if (proposedWordOptional.isPresent()) {
//            String word = proposedWordOptional.get().getWord();
//            StringBuilder newPosition = new StringBuilder();
//            String position = proposedWordOptional.get().getPositions();
//            char letter = proposal.getLetter();
//            int score = 0;
//
//            for (int i = 0; i < word.length(); i++) {
//                if (word.charAt(i) == letter && position.charAt(i) == '_') {
//                    score++;
//                    newPosition.append(letter);
//                }
//                else
//                    newPosition.append(position.charAt(i));
//            }
//
//            ProposedWord proposedWordUpdated = proposedWordOptional.get();
//            proposedWordUpdated.setPositions(newPosition.toString());
//            proposedWordRepository.update(proposedWordUpdated, proposedWordUpdated);
//            updateScore(score, proposal.getProposerId());
//        }
//    }
//
//    public synchronized void updateScore(Integer score, Integer playerId) {
//
//
//        if (Objects.equals(playerId, playersId.get(0)))
//            currentGame.setScore1(currentGame.getScore1() + score);
//
//        if (Objects.equals(playerId, playersId.get(1)))
//            currentGame.setScore2(currentGame.getScore2() + score);
//
//        if (Objects.equals(playerId, playersId.get(2)))
//            currentGame.setScore3(currentGame.getScore3() + score);
//        gameRepository.update(currentGame, currentGame);
//    }
//
    public synchronized void notifyStart() {
        int defaultThreadsNo = 5;
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        Player player1 = playerRepository.findByID(currentGame.getPlayerId1()).get();
        Player player2 = playerRepository.findByID(currentGame.getPlayerId2()).get();
        GameDTO gameDTO = new GameDTO(currentGame, player1, player2, null);
        for (Observer observer: loggedUsers.values()) {
            executor.execute(() -> {
                observer.startGameUpdate(gameDTO);
            });
        }
        executor.shutdown();
    }
//
//    public synchronized void notifyNextRound() {
//        int defaultThreadsNo = 5;
//        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
//        for (Observer observer: loggedUsers.values()) {
//            executor.execute(() -> {
//                observer.startGameUpdate(proposedWordRepository.findByGameId(currentGame.getId()), currentGame);
//                System.out.println(proposedWordRepository.findByGameId(currentGame.getId()));
//            });
//        }
//        executor.shutdown();
//    }

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
    public void startGame(Game game, Observer mainController) {
        players++;
        if (players == 1) {
            currentGame = game;
        }
        else {
            currentGame.setX3(game.getX1());
            currentGame.setY3(game.getY1());
            currentGame.setX4(game.getX2());
            currentGame.setY4(game.getY2());
            currentGame.setPlayerId2(game.getPlayerId1());
            notifyStart();
        }
    }
}
