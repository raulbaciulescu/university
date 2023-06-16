package repository.persistent;

import domain.data.source.database.MetaDB;
import domain.data.source.database.table.FriendshipDTO;
import domain.data.source.database.table.FriendshipTable;
import domain.exceptions.InvalidException;
import domain.model.Friendship;
import domain.model.Tuple;
import domain.model.User;
import org.jetbrains.annotations.NotNull;
import repository.memory.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class FriendshipDBRepository extends Repository<Long, Friendship> {

    final FriendshipTable table;
    final FriendshipRepository.FriendshipSerializer.Resource resource;

    public FriendshipDBRepository(
            @NotNull final FriendshipRepository.FriendshipSerializer.Resource resource) throws SQLException {
        this.table = (FriendshipTable) MetaDB.getInstance().table("friendships");
        this.resource = resource;
        for (final Friendship friendship : this.getAll()) {
            super.add(friendship);
        }
    }

    @Override
    public @NotNull Optional<Friendship> add(@NotNull Friendship entity) throws SQLException {
        final Optional<Friendship> result =  super.add(entity);
        if (result.isPresent()) {
            this.table.insert(
                    new FriendshipDTO(
                            entity.getCreationDate(),
                            entity.getUsers().left().getId(),
                            entity.getUsers().right().getId()
                    )
            );
        }
        return result;
    }

    @Override
    public @NotNull Optional<Friendship> update(@NotNull Friendship entity) throws SQLException {
        return super.update(entity);
    }

    @Override
    public @NotNull Optional<Friendship> findByID(@NotNull Long id) throws SQLException {
        final Optional<FriendshipDTO> result = this.table.whereID(id);
        return result.isPresent()
                ? Optional.of(this.fromDTO(result.get()))
                : Optional.empty();
    }

    @Override
    public @NotNull Optional<Friendship> delete(@NotNull Long id) throws SQLException {
        final Optional<Friendship> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public @NotNull ArrayList<Friendship> getAll() throws SQLException {
        final ArrayList<FriendshipDTO> friendshipDTOs = this.table.getAll();
        final ArrayList<Friendship> friendships = new ArrayList<>();
        for (final FriendshipDTO dto : friendshipDTOs) {
            friendships.add(this.fromDTO(dto));
        }
        return friendships;
    }

    @NotNull
    private Friendship fromDTO(@NotNull final FriendshipDTO dto) throws SQLException {
        User left = null;
        User right = null;
        for (final User user : this.resource.getUsers()) {
            if (user.getId() != null &&
                    (user.getId().equals(dto.firstID()) ||
                            user.getId().equals(dto.secondID()))) {
                if (left != null) {
                    right = user;
                    break;
                } else {
                    left = user;
                }
            }
        }
        if (left == null || right == null) {
            throw new InvalidException("invalid users");
        }
        final Tuple<User, User> userTuple = new Tuple<>(left, right);
        return new Friendship(dto.creationDate(), userTuple);
    }
}
