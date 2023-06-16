package com.example.socnet.controller_fx;

import com.example.socnet.domain.model.Event;
import com.example.socnet.domain.model.SubscribedEvent;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.domain.util.Observer;
import com.example.socnet.resources.Resources;
import com.example.socnet.service.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class EventController implements Initializable, Observer {

    @FXML public ListView<Event> lvEvents;
    @FXML public ListView<SubscribedEvent> lvNextEvents;
    @FXML public ListView<SubscribedEvent> lvPastEvents;
    @FXML public Button btnGoBack;
    @FXML public Button btnAddEvent;
    @FXML public Button btnUnsubscribe;
    @FXML public Button btnSubscribe;
    @FXML public Button btnRefresh;

    private final EventService eventService;

    private final ObservableList<Event> events;
    private final ObservableList<SubscribedEvent> nextEvents;
    private final ObservableList<SubscribedEvent> pastEvents;

    public EventController() throws SQLException {
        eventService = Resources.getInstance().getEventService();
        events = FXCollections.observableArrayList();
        nextEvents = FXCollections.observableArrayList();
        pastEvents = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(final URL location,
                           final ResourceBundle resources) {
        this.update();
        try {
            Resources.getInstance().getEventService().addObserver(this);
        } catch (final @NotNull Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack(@NotNull final ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.MAIN_FEED, event);
    }

    @FXML
    public void addEvent(@NotNull final ActionEvent event) {
        NavController.navigate(Constants.UI.Scene.CREATE_EVENT, event);
    }

    @FXML
    public void subscribe() {
        final Event event = lvEvents.getSelectionModel()
                .getSelectedItem();
        if (event != null) {
            try {
                eventService.subscribe(event.getId());
            } catch (@NotNull final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void unsubscribe() {
        final SubscribedEvent event = lvNextEvents.getSelectionModel()
                .getSelectedItem();
        if (event != null) {
            try {
                eventService.unsubscribe(event.getSubscriptionId());
            } catch (@NotNull final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        try {
            lvEvents.setItems(events);
            lvNextEvents.setItems(nextEvents);
            lvPastEvents.setItems(pastEvents);
            events.setAll(eventService.getAll());

            final List<SubscribedEvent> subscribedEvents =
                    eventService.getAllSubscribedEvents();
            nextEvents.setAll(subscribedEvents
                    .stream()
                    .filter(subscribedEvent -> subscribedEvent
                            .getEvent()
                            .getTimestamp()
                            .isAfter(LocalDateTime.now()))
                    .toList());
            pastEvents.setAll(subscribedEvents
                    .stream()
                    .filter(subscribedEvent -> subscribedEvent
                            .getEvent()
                            .getTimestamp()
                            .isBefore(LocalDateTime.now()))
                    .toList());
        } catch (@NotNull final Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        this.update();
    }
}
