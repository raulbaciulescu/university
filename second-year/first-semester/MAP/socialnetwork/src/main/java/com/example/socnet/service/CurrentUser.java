package com.example.socnet.service;

import com.example.socnet.domain.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class CurrentUser implements Serializable {

    private final User user;
    private int selectedChatId;

    private static volatile CurrentUser currentUser;

    private CurrentUser(@NotNull final User user) {
        if (currentUser != null) {
            throw new IllegalStateException("somehow, there are to objects!");
        }
        this.user = user;
        this.selectedChatId = -1;
    }

    public static void initialize(@NotNull final User user) {
        if (currentUser == null) {
            synchronized (CurrentUser.class) {
                if (currentUser == null) {
                    synchronized (CurrentUser.class) {
                        currentUser = new CurrentUser(user);
                    }
                }
            }
        }
    }

    @NotNull
    public static CurrentUser getInstance() {
        if (currentUser == null) {
            throw new IllegalStateException("the user hasn't been initialized!");
        }
        return currentUser;
    }

    @NotNull
    public final User getUser() {
        return this.user.shallowCopy();
    }

    public final void setSelectedChatId(final int chatId) {
        this.selectedChatId = chatId;
    }

    public final int getSelectedChatId() {
        return this.selectedChatId;
    }
}
