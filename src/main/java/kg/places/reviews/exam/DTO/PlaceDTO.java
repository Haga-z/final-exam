package kg.places.reviews.exam.DTO;

import kg.places.reviews.exam.model.Place;
import kg.places.reviews.exam.model.User;
import lombok.*;

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

    public static PlaceDTO from(Place place){
        return builder()
                .id(place.getId())
                .description(place.getDescription())
                .title(place.getTitle())
                .main_photo(place.getMain_photo())
                .user(UserDTO.from(place.getUser()))
                .build();
    }
}
