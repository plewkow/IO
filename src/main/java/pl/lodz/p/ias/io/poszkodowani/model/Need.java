package pl.lodz.p.ias.io.poszkodowani.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.lodz.p.ias.io.mapy.model.MapPoint;
import pl.lodz.p.ias.io.uwierzytelnianie.model.Account;

import java.util.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "need")
public abstract class Need {
    public enum Status {
        CANCELLED, PENDING, IN_PROGRESS, COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "need_seq")
    @SequenceGenerator(name = "need_seq", sequenceName = "need_sequence", allocationSize = 1)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account user;

    @ManyToOne
    @JoinColumn(name = "map_point_id")
    private MapPoint mapPoint;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date", nullable = true)
    private Date expirationDate;

    @Column(name = "status", length = 50)
    private Status status;

    @Column(name = "priority", nullable = false)
    private int priority;
}