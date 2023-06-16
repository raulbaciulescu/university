package domain.data.source.database;

import org.jetbrains.annotations.NotNull;

public interface Database extends AutoCloseable {

    enum Query {
        ADD,
        FIND_BY_ID,
        UPDATE,
        DELETE,
        GET_ALL
    }

    @NotNull
    Table<?, ?> table(@NotNull final String tableName);
}
