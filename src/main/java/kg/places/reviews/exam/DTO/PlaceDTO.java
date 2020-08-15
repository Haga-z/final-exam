package kg.places.reviews.exam.DTO;

import kg.places.reviews.exam.model.Place;
import kg.places.reviews.exam.model.User;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class PlaceDTO {
    private Integer id;
    private String title;
    private String description;
    private byte[] main_photo;
    private UserDTO user;
    private Timestamp date;

    public static PlaceDTO from(Place place){
        return builder()
                .id(place.getId())
                .description(place.getDescription())
                .date(place.getDate())
                .title(place.getTitle())
                .main_photo(place.getMain_photo())
                .user(UserDTO.from(place.getUser()))
                .build();
    }
}
