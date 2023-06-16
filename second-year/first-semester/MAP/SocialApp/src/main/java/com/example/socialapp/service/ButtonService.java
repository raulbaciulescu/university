package com.example.socialapp.service;

import com.example.socialapp.controller.NavController;
import com.example.socialapp.domain.FriendshipRequest;
import com.example.socialapp.domain.Tuple;
import com.example.socialapp.domain.User;
import com.example.socialapp.utils.Constants;
import com.example.socialapp.utils.CurrentUser;
import com.example.socialapp.utils.Resources;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class ButtonService {
    private final FriendshipService friendshipService = Resources.getInstance().friendshipService;
    private final FriendshipSuperService friendshipSuperService = Resources.getInstance().friendshipSuperService;
    private final FriendshipRequestService friendshipRequestService = Resources.getInstance().requestService;
    private final User currentUser = CurrentUser.getInstance().getUser().get();
    public ButtonService() throws SQLException {
    }

    public Button getButton(User user) throws SQLException {
        Button button = new Button();
        if (Objects.equals(currentUser.getId(), user.getId())) {
            setButtonMe(button);
            return button;
        }
        Optional<Tuple<Long, Long>> optional = friendshipSuperService.areFriends(user, currentUser);
        if (optional.isPresent()) {
            setButtonRemove(button, optional.get(), user);
            return button;
        }

        Optional<FriendshipRequest> friendshipRequest = friendshipRequestService.getFriendRequest(user, currentUser);
        if (friendshipRequest.isPresent()) {
            setButtonAccept(button, user, friendshipRequest.get());
            return button;
        }
        Optional<FriendshipRequest> friendshipRequestReverse = friendshipRequestService.getFriendRequest(currentUser, user);
        if (friendshipRequestReverse.isPresent()) {
            setButtonCancel(button, user);
            return button;
        }
        setButtonAdd(button, user);
        return button;
    }

    private void setButtonCancel(Button button, User user) {
        button.setText("cancel");
        button.setOnAction(event -> {
            setButtonAdd(button, user);
        });
    }

    private void setButtonAccept(Button button, User user, FriendshipRequest friendshipRequest) {
        button.setText("accept");
        button.setOnAction(event -> {
            try {
                if (friendshipRequest.getId() != null && user.getId() != null && currentUser.getId() != null) {
                    friendshipRequestService.update(friendshipRequest.getId(), FriendshipRequest.Status.ACCEPTED);
                    friendshipSuperService.add(user.getId(), currentUser.getId());
                    setButtonRemove(button,
                            friendshipSuperService.areFriends(user, currentUser).get(), user);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    private void setButtonAdd(Button button, User user) {
        button.setText("add");
        button.setOnAction(event -> {
            try {
                if (currentUser.getId() != null && user.getId() != null &&
                        friendshipRequestService.getPendingFriendRequest(user, currentUser).isEmpty()) {
                    friendshipRequestService.add(currentUser.getId(), user.getId());
                    setButtonCancel(button, user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void setButtonMe(Button button) {
        button.setText("me");
        button.setOnAction(event -> {
            try {
                NavController.navigate(Constants.Scene.REQUESTS, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setButtonRemove(Button button, Tuple<Long, Long> tuple, User user) {
        button.setText("remove");
        button.setOnAction(event -> {
            try {
                friendshipSuperService.delete(tuple);
                setButtonAdd(button, user);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
