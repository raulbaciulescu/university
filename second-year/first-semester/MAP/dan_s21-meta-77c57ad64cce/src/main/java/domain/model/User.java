package domain.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class User extends Entity<Long> {

    private String firstName;
    private String lastName;

    public User(final long id, final String firstName, final String lastName) {
        this.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.firstName, this.lastName);
    }

    @Override
    public boolean equals(@Nullable final Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof @NotNull final User user)) {
            return false;
        }
        return Objects.equals(this.getId(), user.getId()) &&
                Objects.equals(this.firstName, user.firstName) &&
                Objects.equals(this.lastName, user.lastName);
    }

    @NotNull
    @Override
    public final String toString() {
        return "User - " + this.getId() +  " - { name: " + this.firstName + " " + this.lastName + " }";
    }
}
