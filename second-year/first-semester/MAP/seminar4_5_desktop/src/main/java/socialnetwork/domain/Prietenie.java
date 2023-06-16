package socialnetwork.domain;

import java.time.LocalDateTime;


public class Prietenie extends Entity<Tuple<Long,Long>> {
    LocalDateTime date;

    public Prietenie(Long id1, Long id2) {
        super.setId(new Tuple<>(id1, id2));
        date = LocalDateTime.now();
    }

    /**
     *
     * @return the date when the friendship was created
     */
    public LocalDateTime getDate() {
        return date;
    }
}
