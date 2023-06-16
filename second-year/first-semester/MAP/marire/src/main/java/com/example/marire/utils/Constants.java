package com.example.marire.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public enum Orase {
        CLUJNAPOCA,
        TIMISOARA,
        BUCURESTI
    }
    public static final ArrayList<String> ORASE =
            new ArrayList<>(Arrays.asList(
                    "CLUJNAPOCA",
                    "TIMISOARA",
                    "BUCURESTI"
            ));

    public final static class UI {

    public final static class Scene {

        public static final String LOG_IN = "log-in-view.fxml";
        public static final String SIGN_UP = "register-view.fxml";
        public static final String MAIN_FEED = "main-feed-view.fxml";
        public static final String REQUESTS = "requests-view.fxml";
        public static final String SEARCH = "search-view.fxml";
        public static final String MESSAGES = "messages-view.fxml";
        public static final String REPORTS = "reports-view.fxml";
        public static final String CREATE_EVENT = "create-event-view.fxml";
        public static final String EVENTS = "events-view.fxml";
    }
}
}
