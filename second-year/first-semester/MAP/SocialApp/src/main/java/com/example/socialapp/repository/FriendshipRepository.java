package com.example.socialapp.repository;
import com.example.socialapp.domain.Friendship;
import com.example.socialapp.domain.FriendshipDTO;
import com.example.socialapp.domain.Tuple;
import com.example.socialapp.domain.User;
import com.example.socialapp.database.TableFactory;
import com.example.socialapp.database.table.FriendshipTable;
import com.example.socialapp.utils.Constants;
import com.example.socialapp.utils.Resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class FriendshipRepository implements Repository<Tuple<Long, Long>, Friendship>{
    private FriendshipTable table;

    public FriendshipRepository() throws SQLException {
        table = (FriendshipTable) TableFactory.getInstance().table(Constants.Tables.FRIENDSHIPS);
    }

    @Override
    public void add(Friendship friendship) throws SQLException {
        table.insert(new FriendshipDTO(friendship.getCreationDate(), friendship.getFirstUser().getId(), friendship.getSecondUser().getId()));
    }

    @Override
    public void update(Friendship entity) throws SQLException {
    }

    @Override
    public Optional<Friendship> findByID(Tuple<Long, Long> tuple) throws SQLException {
        Optional<FriendshipDTO> friendshipDTO = table.findByID(tuple);
        if (friendshipDTO.isPresent()) {
            Optional<User> first = Resources.userService.findByID(friendshipDTO.get().firstID());
            Optional<User> second = Resources.userService.findByID(friendshipDTO.get().secondID());
            Tuple<User, User> users =  new Tuple<User, User>(first.get(), second.get());
            Friendship friendship = new Friendship(friendshipDTO.get().creationDate(),users);
            return Optional.of(friendship);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Tuple<Long, Long> tuple) throws SQLException {
        this.table.delete(tuple);
    }

    @Override
    public ArrayList<Friendship> getAll() throws SQLException {
        ArrayList<FriendshipDTO> friendshipDTOS = table.getAll();
        ArrayList<Friendship> friendships = friendshipDTOS.stream().map(friendshipDTO -> {
            Optional<User> first = null;
            try {
                first = Resources.userService.findByID(friendshipDTO.firstID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Optional<User> second = null;
            try {
                second = Resources.userService.findByID(friendshipDTO.secondID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Tuple<User, User> users =  new Tuple<User, User>(first.get(), second.get());
            return new Friendship(friendshipDTO.creationDate(),users);
        }).collect(Collectors.toCollection(ArrayList::new));
        return friendships;
    }
}
