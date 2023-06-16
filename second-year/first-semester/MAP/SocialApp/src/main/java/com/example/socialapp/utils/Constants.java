package com.example.socialapp.utils;

public class Constants {
    public final static class Db {
        public static final String DB_USER = "postgres";
        public static final String DB_PASSWORD = "266259";
        public static final String DB_URL = "jdbc:postgresql://localhost:5432/meta";
    }

    public enum Tables {
        USERS,
        FRIENDSHIPS,
    }
}
