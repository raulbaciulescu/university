package server;

import api.GameRepository;
import api.PlayerRepository;
import api.ProposalRepository;
import api.ProposedWordRepository;
import domain.Game;
import domain.Player;
import domain.Proposal;
import domain.ProposedWord;
import services.LoginException;
import services.Observer;
import services.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements Service {
    private final PlayerRepository playerRepository;
    private final ProposedWordRepository proposedWordRepository;
    private final GameRepository gameRepository;
    private final ProposalRepository proposalRepository;

    private final Map<Integer, Observer> loggedUsers;
    private final List<Integer> playersId;

    private static Integer noOfPlayers = 0;
    private static Integer round = 0;
    private static Integer playersPerRound = 0;

    private static Game currentGame;

    public ServiceImpl(PlayerRepository playerRepository, ProposedWordRepository proposedWordRepository,
                       GameRepository gameRepository, ProposalRepository proposalRepository) {
        this.playerRepository = playerRepository;
        this.proposedWordRepository = proposedWordRepository;
        this.gameRepository = gameRepository;
        this.proposalRepository = proposalRepository;
        this.loggedUsers = new ConcurrentHashMap<>();
        playersId = new ArrayList<>();
    }

    @Override
    public synchronized Player login(Player player, Observer client) throws LoginException {
        Optional<Player> playerOptional = playerRepository.findPlayer(player.getUsername(), player.getPassword());
        if (playerOptional.isPresent()) {
            loggedUsers.put(playerOptional.get().getId(), client);
            playersId.add(playerOptional.get().getId());
            return playerOptional.get();
        }
        else
            throw new LoginException("Authentication failed!");
    }

    @Override
    public synchronized void startGame(ProposedWord proposedWord, Observer mainCtrl) {
        if (noOfPlayers == 0) {
            currentGame = new Game("joc");
            gameRepository.add(currentGame);
            currentGame = gameRepository.findLastGame().get();
        }
        proposedWord.setGameId(currentGame.getId());
        proposedWordRepository.add(proposedWord);

        noOfPlayers++;
        if (noOfPlayers == 3) {
            round = 1;
            Collections.sort(playersId);
            notifyStart();
        }
    }

    @Override
    public synchronized void move(Proposal proposal) {
        playersPerRound++;
        proposalRepository.add(proposal);
        updatePositions(proposal);
        if (playersPerRound == 3) { // trec la runda urmatoare
            notifyNextRound();
            round++;
            playersPerRound = 0;
        }
    }

    public synchronized void updatePositions(Proposal proposal) {
        Optional<ProposedWord> proposedWordOptional = proposedWordRepository.findByID(proposal.getWordId());
        if (proposedWordOptional.isPresent()) {
            String word = proposedWordOptional.get().getWord();
            StringBuilder newPosition = new StringBuilder();
            String position = proposedWordOptional.get().getPositions();
            char letter = proposal.getLetter();
            int score = 0;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter && position.charAt(i) == '_') {
                    score++;
                    newPosition.append(letter);
                }
                else
                    newPosition.append(position.charAt(i));
            }

            ProposedWord proposedWordUpdated = proposedWordOptional.get();
            proposedWordUpdated.setPositions(newPosition.toString());
            proposedWordRepository.update(proposedWordUpdated, proposedWordUpdated);
            updateScore(score, proposal.getProposerId());
        }
    }

    public synchronized void updateScore(Integer score, Integer playerId) {


        if (Objects.equals(playerId, playersId.get(0)))
            currentGame.setScore1(currentGame.getScore1() + score);

        if (Objects.equals(playerId, playersId.get(1)))
            currentGame.setScore2(currentGame.getScore2() + score);

        if (Objects.equals(playerId, playersId.get(2)))
            currentGame.setScore3(currentGame.getScore3() + score);
        gameRepository.update(currentGame, currentGame);
    }

    public synchronized void notifyStart() {
        int defaultThreadsNo = 5;
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for (Observer observer: loggedUsers.values()) {
            executor.execute(() -> {
                observer.startGameUpdate(proposedWordRepository.findByGameId(currentGame.getId()), currentGame);
                System.out.println(proposedWordRepository.findByGameId(currentGame.getId()));
            });
        }
        executor.shutdown();
    }

    public synchronized void notifyNextRound() {
        int defaultThreadsNo = 5;
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for (Observer observer: loggedUsers.values()) {
            executor.execute(() -> {
                observer.startGameUpdate(proposedWordRepository.findByGameId(currentGame.getId()), currentGame);
                System.out.println(proposedWordRepository.findByGameId(currentGame.getId()));
            });
        }
        executor.shutdown();
    }

    @Override
    public synchronized void logout(Player player, Observer client) throws LoginException {
        Observer client1 = loggedUsers.remove(playerRepository.findPlayer(player.getUsername(), player.getPassword()).get().getId());
        if (client1 == null) {
            throw new LoginException("User " + player.getUsername() + " is not logged in.");
        }
    }
}
