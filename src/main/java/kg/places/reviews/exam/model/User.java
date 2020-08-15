package kg.places.reviews.exam.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@ToString
@Table(name = "customers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email
    @NotBlank
    @Size(min=5, max = 35)
    @Column(length = 128)
    private String email;
    @NotBlank
    @Size(min=5, max = 35)
    @Column(length = 128)
    private String fullname;
    @NotBlank
    @Column(length = 128)
    private String password;
    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";
}
