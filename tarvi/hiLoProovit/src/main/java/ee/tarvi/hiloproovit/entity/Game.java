package ee.tarvi.hiloproovit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int points;
    private long time;
    private Date creationDate;

    // @OneToOne ja läheb teist korda
//    org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "uk_idsm5ox8o1mqy9kahkxbfqii6"
//    Detail: Key (player_name)=(Mihkel) already exists.
    @ManyToOne
    private Player player;
}
