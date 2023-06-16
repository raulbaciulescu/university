package com.example.socnet.domain.data;

import org.jetbrains.annotations.NotNull;

public interface Database extends AutoCloseable {

    enum Query {
        ADD,
        ADD2,
        FIND_BY_ID,
        UPDATE,
        DELETE,
        GET_ALL,
        FIND
    }

    @NotNull
    Table<?, ?> table(@NotNull final String tableName);
}
