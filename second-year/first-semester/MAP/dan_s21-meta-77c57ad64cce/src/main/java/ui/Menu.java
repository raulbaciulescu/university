package ui;

import controller.FriendshipController;
import domain.model.Friendship;
import domain.model.User;
import domain.util.Constants;
import domain.validation.FriendshipValidator;
import domain.validation.UserValidator;
import org.jetbrains.annotations.NotNull;
import repository.Repository;
import repository.persistent.FileRepository;
import repository.persistent.FriendshipDBRepository;
import repository.persistent.FriendshipRepository;
import repository.persistent.UserDBRepository;
import service.FriendshipService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class Menu {

    UserService userService;
    FriendshipController controller;
    BufferedReader reader;

    public Menu() {
        // repos
        try {
            final Repository<Long, User> userRepository
                    = new UserDBRepository();
            final Repository<Long, Friendship> friendShipRepository
                    = new FriendshipDBRepository(userRepository::getAll);
            // validators
            final UserValidator userValidator = new UserValidator();
            final FriendshipValidator friendshipValidator = new FriendshipValidator();

            // services
            this.userService = new UserService(userValidator, userRepository);
            final FriendshipService friendShipService
                    = new FriendshipService(friendshipValidator, friendShipRepository);

            // controllers
            this.controller = new FriendshipController(userService, friendShipService);

            // reader
            this.reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public final void start() {
        this.displayAvailableCommands();
        this.startGetUserInput();
    }

    private void displayAvailableCommands() {
        System.out.println("""
                'help' -> display this set of all available commands
                'add -u/f' -> add user/friendship
                'find -u/f' -> find user/friendship by id
                'up -u' -> update user
                'del -u/f' -> delete user/friendship
                'all -u/f/c' -> display all users/friendships/communities
                'all -cN' -> display communities number
                'comm -a' -> display the most active community
                """);
    }

    private void startGetUserInput() {
        while (true) {
            try {
                System.out.print(">>>");
                final String command = reader.readLine();
                if (command.isEmpty()) {
                    continue;
                }

                switch (command) {
                    case "exit" -> {
                        System.out.println("exiting meta-console...");
                        return;
                    }
                    case "help" -> this.displayAvailableCommands();
                    case "add -u" -> this.addUser();
                    case "add -f" -> this.addFriendship();
                    case "find -u" -> this.findUserByID();
                    case "find -f" -> this.findFriendshipByID();
                    case "up -u" -> this.updateUser();
                    case "del -u" -> this.deleteUser();
                    case "del -f" -> this.deleteFriendship();
                    case "all -u" -> this.displayUsers();
                    case "all -f" -> this.displayFriendships();
                    case "all -c" -> this.displayCommunities();
                    case "all -cN" -> this.displayCommunitiesNumber();
                    case "comm -a" -> this.displayMostActiveCommunity();
                    default -> System.out.println("unknown command <" + command + ">");
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void addUser() throws IOException, SQLException {
        System.out.print("firstname:");
        final String firstname = this.reader.readLine();
        System.out.print("lastname:");
        final String lastname = this.reader.readLine();
        this.userService.add(firstname, lastname);
    }

    private void addFriendship() throws IOException, SQLException {
        System.out.println("these are the users:");
        this.userService.getAll().forEach(System.out::println);
        System.out.print("enter first id:");
        final long first_id = Long.parseLong(this.reader.readLine());
        System.out.print("second first id:");
        final long second_id = Long.parseLong(this.reader.readLine());
        controller.add(first_id, second_id);
    }

    private void findUserByID() throws IOException, SQLException {
        System.out.print("enter id:");
        final long id = Long.parseLong(this.reader.readLine());
        final Optional<User> result = this.userService.findByID(id);
        result.ifPresent(System.out::println);
    }

    private void findFriendshipByID() throws IOException, SQLException {
        System.out.print("enter id:");
        final long id = Long.parseLong(this.reader.readLine());
        final @NotNull Optional<Friendship> result = this.controller.findByID(id);
        if (result.isPresent()) {
            System.out.println("found:" + result.get());
        } else {
            System.out.println("no user with <id: + " + id + ">");
        }
    }

    private void updateUser() throws IOException, SQLException {
        System.out.print("enter id:");
        final long id = Long.parseLong(this.reader.readLine());
        System.out.print("new firstname:");
        final String firstname = this.reader.readLine();
        System.out.print("new last name:");
        final String lastname = this.reader.readLine();
        final Optional<User> result = this.userService.update(id, firstname, lastname);
        result.ifPresent(System.out::println);
    }

    private void deleteUser() throws IOException, SQLException {
        System.out.print("enter id:");
        final long id = Long.parseLong(this.reader.readLine());
        final Optional<User> result = this.userService.delete(id);
        result.ifPresent(System.out::println);
    }

    private void deleteFriendship() throws IOException, SQLException {
        System.out.print("enter id:");
        final long id = Long.parseLong(this.reader.readLine());
        this.controller.delete(id);
    }

    private void displayUsers() throws SQLException {
        this.displayList(this.userService.getAll());
    }

    private void displayFriendships() throws SQLException {
        this.displayList(this.controller.getAll());
    }

    private void displayCommunities() throws SQLException {
        this.displayList(this.controller.getCommunities());
    }

    private void displayCommunitiesNumber() throws SQLException {
        System.out.println("there are " + this.controller.getCommunitiesNumber() +
                " within meta's network");
    }

    private void displayMostActiveCommunity() throws SQLException {
        this.displayList(this.controller.getMostActiveCommunity());
    }

    private <T> void displayList(@NotNull final ArrayList<T> objects) {
        System.out.println("\n--------------------------------------");
        objects.forEach(System.out::println);
        System.out.println("--------------------------------------\n");
    }
}
