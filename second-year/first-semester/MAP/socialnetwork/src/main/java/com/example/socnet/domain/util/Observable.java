package com.example.socnet.domain.util;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface Observable {

    List<Observer> observers = new ArrayList<>();

    default void update() {
        for (final Observer observer : observers) {
            observer.update();
        }
    }

    default void addObserver(@NotNull final Observer observer) {
        observers.add(observer);
    }

    default void removeObserver(@NotNull final Observer observer) {
        observers.remove(observer);
    }
}
