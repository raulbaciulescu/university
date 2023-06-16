package com.example.socialapp.service;

import com.example.socialapp.domain.FriendshipRequest;
import com.example.socialapp.domain.User;
import com.example.socialapp.repository.FriendshipRepository;
import com.example.socialapp.repository.FriendshipRequestRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FriendshipRequestService {
    private FriendshipRequestRepository repository;

    public FriendshipRequestService(FriendshipRequestRepository repository) {
        this.repository = repository;
    }
    public void add(final long senderID, final long citedID) throws SQLException {
        final FriendshipRequest request = new FriendshipRequest(senderID, citedID, FriendshipRequest.Status.PENDING);
        this.repository.add(request);
        //this.update();
    }

    public void update(final int id, final FriendshipRequest.Status status) throws SQLException {
        final Optional<FriendshipRequest> result = this.repository.findByID(id);
        if (result.isPresent()) {
            final FriendshipRequest r = new FriendshipRequest(
                    result.get().getId(),
                    result.get().getSenderID(),
                    result.get().getCitedID(),
                    status
            );
            this.repository.update(r);
        }
        //this.update();
    }

    public Optional<FriendshipRequest> findByID(final int id) throws SQLException {
        return this.repository.findByID(id);
    }

    public void delete(final int id) throws SQLException {
        this.repository.delete(id);
        //this.update();
    }

    public ArrayList<FriendshipRequest> getAllSendBy(final long senderID) throws SQLException {
        return (ArrayList<FriendshipRequest>) this.repository.getAll()
                .stream().filter(request -> request.getSenderID() == senderID)
                .collect(Collectors.toList());
    }

    public ArrayList<FriendshipRequest> getAllFor(final long citedID) throws SQLException {
        return (ArrayList<FriendshipRequest>) this.repository.getAll()
                .stream().filter(request -> request.getCitedID() == citedID)
                .collect(Collectors.toList());
    }

    public Optional<FriendshipRequest> getPendingFriendRequest(User user1, User user2) throws SQLException {
        ArrayList<FriendshipRequest> allFor = getAllFor(user1.getId());
        List<FriendshipRequest> friendshipRequests = allFor.stream()
                .filter(x -> x.getSenderID() == user2.getId() && x.getStatus() == FriendshipRequest.Status.PENDING)
                .collect(Collectors.toList());
        if (friendshipRequests.size() != 0)
            return Optional.ofNullable(friendshipRequests.get(0));
        ArrayList<FriendshipRequest> allFrom = getAllSendBy(user1.getId());
        List<FriendshipRequest> friendshipRequests2 = allFrom
                .stream()
                .filter(x -> x.getSenderID() == user2.getId() && x.getStatus() == FriendshipRequest.Status.PENDING)
                .collect(Collectors.toList());
        if (friendshipRequests2.size() != 0)
            return Optional.ofNullable(friendshipRequests2.get(0));
        return Optional.empty();
    }

    public Optional<FriendshipRequest> getFriendRequest(User sender, User receiver) throws SQLException {
        ArrayList<FriendshipRequest> allFor = new ArrayList<>();
        if (receiver.getId() != null)
            allFor = getAllFor(receiver.getId());
        List<FriendshipRequest> friendshipRequests = allFor.stream()
                .filter(x -> x.getSenderID() == sender.getId() && x.getStatus() == FriendshipRequest.Status.PENDING)
                .collect(Collectors.toList());
        if (friendshipRequests.size() != 0)
            return Optional.ofNullable(friendshipRequests.get(0));
        return Optional.empty();
    }

}
