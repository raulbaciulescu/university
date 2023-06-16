package controller;

import domain.model.Friendship;
import domain.model.Tuple;
import domain.model.User;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import service.FriendshipService;
import service.UserService;

import java.sql.SQLException;
import java.util.*;


public record FriendshipController(@NotNull UserService userService,
                                   @NotNull FriendshipService friendShipService) {

    /**
     * @param id id of user
     * @param other id of user
     *
     * @implNote find the users who have the specified ids and send them to the service
     * @implNote the ids have to be different
     */
    public void add(final long id, final long other) {
        try {
            if (id == other) {
                return;
            }

            final Optional<User> searchResult1 = this.userService.findByID(id);
            final Optional<User> searchResult2 = this.userService.findByID(other);
            if (searchResult1.isEmpty() || searchResult2.isEmpty()) {
                return;
            }
            this.friendShipService.add(searchResult1.get(), searchResult2.get());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @NotNull
    public Optional<Friendship> findByID(final long id) throws SQLException {
        return this.friendShipService.findByID(id);
    }

    /**
     * @param id the id of the friendship that will be deleted
     */
    public void delete(final long id) throws SQLException {
        this.friendShipService.delete(id);
    }

    @NotNull
    public ArrayList<Friendship> getAll() throws SQLException {
        return this.friendShipService.getAll();
    }

    /**
     * @return the number of the user's communities in Meta
     */
    public int getCommunitiesNumber() throws SQLException {
        return this.getCommunities().size();
    }

    /**
     * @return an ArrayList of Users which represents the most active community
     * (the one that has the longest path in a graph)
     */
    public ArrayList<User> getMostActiveCommunity() throws SQLException {
        final ArrayList<ArrayList<User>> communities = this.getCommunities();
        @NotNull ArrayList<User> mostActiveCommunity = new ArrayList<>();
        int longestPath = -1;
        for (@NotNull final ArrayList<User> community : communities) {
            final LinkedList<Integer>[] adjacent = this.getAdjacentList(community);
            final int path = this.longestPath(adjacent);
            if (longestPath < path) {
                longestPath = path;
                mostActiveCommunity = community;
            }
        }
        return mostActiveCommunity;
    }

    /**
     * @return an ArrayList of ArrayLists of Users
     * representing the list of communities in Meta
     */
    @NotNull
    public ArrayList<ArrayList<User>> getCommunities() throws SQLException {
        final ArrayList<User> users = this.userService.getAll();
        final ArrayList<ArrayList<User>> communities = new ArrayList<>();
        final boolean[] visited = new boolean[users.size()];
        Arrays.fill(visited, false);

        final LinkedList<Integer>[] adjacent = this.getAdjacentList(users);

        for (int i = 0; i < visited.length; ++i) {
            if (!visited[i]) {
                communities.add(new ArrayList<>());
                this.dfs(i, visited, communities.get(communities.size() - 1), users, adjacent);
            }
        }
        return communities;
    }

    /**
     * @param node the current node we are in this df search
     * @param visited vector that retains if we visited a node or not
     * @param community the current community we are creating
     * @param users all the users in Meta
     * @param adjacent the adjacent lists for the current community
     */
    private void dfs(final int node,
                     final boolean @NotNull [] visited,
                     @NotNull final ArrayList<User> community,
                     @NotNull final ArrayList<User> users,
                     @NotNull final LinkedList<Integer> @NotNull [] adjacent) {
        community.add(users.get(node));
        visited[node] = true;

        for (final int x : adjacent[node]) {
            if (!visited[x]) {
                this.dfs(x, visited, community, users, adjacent);
            }
        }
    }

    /**
     * @param users the users for who is created the adjacent list
     * @return the adjacent list for the graph represented by users
     */
    @NotNull
    private LinkedList<Integer> @NotNull [] getAdjacentList(@NotNull final ArrayList<User> users) throws SQLException {
        final LinkedList[] adjacent = new LinkedList[users.size()];
        for (int i = 0; i < adjacent.length; ++i) {
            adjacent[i] = new LinkedList<>();
        }
        for (@NotNull final Friendship friendship : this.friendShipService.getAll()) {
            final Tuple<User, User> userTuple = friendship.getUsers();
            if (users.contains(userTuple.left()) || users.contains(userTuple.right())) {
                if (users.indexOf(userTuple.left()) < adjacent.length) {
                    adjacent[users.indexOf(userTuple.left())]
                            .add(users.indexOf(userTuple.right()));
                }
            }
        }
        return adjacent;
    }

    /**
     * @param adjacent adjacent list of a community in Meta
     * @return the length of the longest path inside this community
     */
    private int longestPath(@NotNull final LinkedList<Integer> @NotNull [] adjacent) {
        final Tuple<Integer, Integer> endPoint = this.bfs(adjacent, 0);
        final Tuple<Integer, Integer> actualLongestPath = this.bfs(adjacent, endPoint.left());
        return actualLongestPath.right();
    }

    /**
     * @param adjacent adjacent list of a community in Meta
     * @param x current start node
     * @return tuple(maxIndex, distance[maxIndex])
     * which represents the endpoint and the distance to it
     */
    @NotNull
    @Contract("_, _ -> new")
    private Tuple<Integer, Integer> bfs(@NotNull final LinkedList<Integer> @NotNull [] adjacent,
                                        final int x) {
        int[] distance = new int[adjacent.length];

        Arrays.fill(distance, -1);

        final Queue<Integer> queue = new LinkedList<>();

        queue.add(x);
        distance[x] = 0;
        while (!queue.isEmpty()) {
            final int node = queue.poll();

            for (final int vertex :  adjacent[node]) {
                if (distance[vertex] == -1) {
                    queue.add(vertex);
                    distance[vertex] = distance[node] + 1;
                }
            }
        }

        int nodeIndex = 0;
        for (int i = 0; i < adjacent.length; ++i) {
            if (distance[i] > distance[nodeIndex]) {
                nodeIndex = i;
            }
        }
        return new Tuple<>(nodeIndex, distance[nodeIndex]);
    }
}
