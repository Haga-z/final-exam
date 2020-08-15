package kg.places.reviews.exam.DTO;

import kg.places.reviews.exam.model.User;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class UserResponseDTO {
    private int id;
    private String fullname;
    private String email;

    public static UserResponseDTO from(User user) {
        return builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .build();
    }
}
