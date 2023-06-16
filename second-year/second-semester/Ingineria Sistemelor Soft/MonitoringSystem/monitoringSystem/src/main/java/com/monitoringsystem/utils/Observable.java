package com.monitoringsystem.utils;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    List<Observer> observers = new ArrayList<>();

    default void addObserver(final Observer observer) {
        observers.add(observer);
    }

    default void notifyUpdate() {
        observers.forEach(Observer::update);
    }
}

