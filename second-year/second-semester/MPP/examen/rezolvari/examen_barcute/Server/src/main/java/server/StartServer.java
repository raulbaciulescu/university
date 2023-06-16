package server;
import api.GameRepository;
import api.PlayerRepository;
import api.ProposalRepository;

import impl.GameRepositoryImpl;
import impl.PlayerRepositoryImpl;
import impl.ProposalRepositoryImpl;

import services.Service;
import utils.AbstractServer;
import utils.RpcConcurrentServer;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Properties;

public class StartServer {
    private static final int defaultPort = 55555;
    public static void main(String[] args) throws SQLException {
        Properties serverProps = new Properties();
        try {
            serverProps.load(StartServer.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find server.properties " + e);
            return;
        }
        PlayerRepository playerRepository = new PlayerRepositoryImpl();
        GameRepository gameRepository = new GameRepositoryImpl();
        ProposalRepository proposalRepository = new ProposalRepositoryImpl();

        Service service = new ServiceImpl(playerRepository, gameRepository, proposalRepository);

        int serverPort = defaultPort;
        try {
            serverPort = Integer.parseInt(serverProps.getProperty("server.port"));
        } catch (NumberFormatException nef) {
            System.err.println("Wrong  Port Number" + nef.getMessage());
            System.err.println("Using default port " + defaultPort);
        }
        System.out.println("Starting server on port: " + serverPort);
        AbstractServer server = new RpcConcurrentServer(serverPort, service);

        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        } finally {
            try {
                server.stop();
            } catch (ServerException e) {
                System.err.println("Error stopping server " + e.getMessage());
            }
        }
    }
}
