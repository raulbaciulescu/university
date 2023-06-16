package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.FriendshipRequest;
import com.example.socnet.domain.model.User;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.CurrentUser;
import com.example.socnet.service.RequestService;
import com.example.socnet.super_service.FriendshipSuperService;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ButtonService {
    private final FriendshipSuperService friendshipSuperService = Resources.getInstance().getFriendshipSuperService();
    private final RequestService requestService = Resources.getInstance().getRequestService();
    private final User currentUser = CurrentUser.getInstance().getUser();
    public ButtonService() throws SQLException {
    }

    public Button getButton(User user) throws SQLException {
        Button button = new Button();
        if (Objects.equals(currentUser.getId(), user.getId())) {
            setButtonMe(button);
            return button;
        }
        long id = friendshipSuperService.areFriends(user, currentUser);
        if (id != 0L) {
            setButtonRemove(button, id, user);
            return button;
        }

        Optional<FriendshipRequest> friendshipRequest = requestService.getFriendRequest(user, currentUser);
        if (friendshipRequest.isPresent()) {
            setButtonAccept(button, user, friendshipRequest.get());
            return button;
        }
        Optional<FriendshipRequest> friendshipRequestReverse = requestService.getFriendRequest(currentUser, user);
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
                    requestService.update(friendshipRequest.getId(), FriendshipRequest.Status.ACCEPTED);
                    friendshipSuperService.add(user.getId(), currentUser.getId());
                    setButtonRemove(button,
                            friendshipSuperService.areFriends(user, currentUser), user);
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
                        requestService.getPendingFriendRequest(user, currentUser).isEmpty()) {
                    requestService.add(currentUser.getId(), user.getId());
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
            NavController.navigate(Constants.UI.Scene.REQUESTS, event);
        });
    }

    public void setButtonRemove(Button button, Long id, User user) {
        button.setText("remove");
        button.setOnAction(event -> {
            try {
                friendshipSuperService.delete(id);
                setButtonAdd(button, user);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
