package kg.places.reviews.exam.DTO;

import kg.places.reviews.exam.model.User;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class UserDTO {
    private Integer id;
    private String email;
    private String fullname;
    private String role;

    public static UserDTO from(User user){
        return builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .role(user.getRole())
                .build();
    }

}
