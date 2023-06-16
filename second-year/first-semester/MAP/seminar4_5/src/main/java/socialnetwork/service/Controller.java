package socialnetwork.service;

import socialnetwork.domain.Utilizator;

public class Controller {
    public UtilizatorService0 utilizatorService;
    public FriendshipService friendshipService;

    public Controller(UtilizatorService0 utilizatorService, FriendshipService friendshipService) {
        this.utilizatorService = utilizatorService;
        this.friendshipService = friendshipService;
    }

    public Utilizator addUser(String firstName, String lastName) {
        Utilizator user = utilizatorService.addUser(firstName, lastName);
        return user;
    }

    void addFriendship(Long id1, Long id2) {
        // aici trebuie sa ma uit daca am intre cine si cine sa fac prietenia
        // prietenia daca exista deja nu stiu inca unde o verific


        //friendshipService.addFriendship();
    }
}
