package com.example.socnet.resources;

import com.example.socnet.domain.model.*;
import com.example.socnet.domain.validation.FriendshipValidator;
import com.example.socnet.domain.validation.UserValidator;
import com.example.socnet.repository.Repository;
import com.example.socnet.repository.persistent.*;
import com.example.socnet.service.*;
import com.example.socnet.super_service.ChatSuperService;
import com.example.socnet.super_service.FriendshipSuperService;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public final class Resources {

    private final UserService userService;
    private final RequestService requestService;
    private final FriendshipSuperService friendshipSuperService;
    private final ChatSuperService chatSuperService;
    private final EventService eventService;

    private static volatile Resources resources;

    private Resources() throws SQLException {
        final UserValidator userValidator = new UserValidator();
        final Repository<Long, User> userRepository = new UserRepository();
        final LoginRepository loginRepository = new LoginRepository();
        final Repository<Integer, Event> eventRepository = new EventRepository();
        final Repository<Integer, EventSubscription> eventSubscriptionRepository =
                new EventSubscriptionRepository();

        final FriendshipValidator friendshipValidator
                = new FriendshipValidator();
        final Repository<Long, Friendship> friendshipRepository
                = new FriendshipRepository(userRepository::getAll);

        final Repository<Integer, FriendshipRequest> requestRepository
                = new RequestRepository();

        final Repository<Integer, Chat> chatRepository = new ChatRepository();

        final Repository<Integer, Message> messageRepository = new MessageRepository();

        final FriendshipService friendshipService
                = new FriendshipService(friendshipValidator, friendshipRepository);

        final ChatService chatService = new ChatService(chatRepository);

        final MessageService messageService = new MessageService(messageRepository);

        this.userService = new UserService(userValidator, userRepository, loginRepository);
        this.requestService = new RequestService(requestRepository);
        this.friendshipSuperService = new FriendshipSuperService(userService,friendshipService);
        this.chatSuperService = new ChatSuperService(chatService, messageService);
        this.eventService = new EventService(eventRepository, eventSubscriptionRepository);
    }

    public static Resources getInstance()
            throws SQLException {
        if (resources == null) {
            synchronized (Resources.class) {
                if (resources == null) {
                    synchronized (Resources.class) {
                        resources = new Resources();
                    }
                }
            }
        }
        return resources;
    }

    public UserService getUserService() {
        return userService;
    }

    @NotNull
    public EventService getEventService() {
        return eventService;
    }

    @NotNull
    public RequestService getRequestService() {
        return requestService;
    }

    @NotNull
    public FriendshipSuperService getFriendshipSuperService() {
        return friendshipSuperService;
    }

    @NotNull
    public ChatSuperService getChatSuperService() {
        return chatSuperService;
    }
}
