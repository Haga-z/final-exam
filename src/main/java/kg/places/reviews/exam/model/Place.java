package kg.places.reviews.exam.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@ToString
@Table(name = "places")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Timestamp date;

    @Lob
    @Column(length=100000)
    private byte[] main_photo;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
