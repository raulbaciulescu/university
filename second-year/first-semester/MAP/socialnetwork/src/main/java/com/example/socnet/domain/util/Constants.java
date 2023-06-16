package com.example.socnet.domain.util;

import java.util.ArrayList;
import java.util.Arrays;

public final class Constants {

    public static final ArrayList<String> MONTHS =
            new ArrayList<>(Arrays.asList(
                    "January",
                    "February",
                    "March",
                    "April",
                    "May",
                    "June",
                    "July",
                    "August",
                    "September",
                    "October",
                    "November",
                    "December"
            ));

    public final static class Postgres {

        public static final String MASTER_USER = "postgres";
        public static final String MASTER_PASSWORD = "266259";
        public static final String META_DATABASE_URL = "jdbc:postgresql://localhost:5432/meta";
    }

    public final static class UI {

        public final static class Scene {

            public static final String LOG_IN = "log-in-view.fxml";
            public static final String SIGN_UP = "sign-up-view.fxml";
            public static final String MAIN_FEED = "main-feed-view.fxml";
            public static final String REQUESTS = "requests-view.fxml";
            public static final String SEARCH = "search-view.fxml";
            public static final String MESSAGES = "messages-view.fxml";
            public static final String REPORTS = "reports-view.fxml";
            public static final String CREATE_EVENT = "create-event-view.fxml";
            public static final String EVENTS = "events-view.fxml";
        }
    }

    public static final class Length {

        public static final int MIN_PASSWORD = 6;
        public static final int PAGE = 5;
    }

    public static final class Other {

        public static final int PASSWORD_ENCRYPT_KEY = 6;
    }
}
