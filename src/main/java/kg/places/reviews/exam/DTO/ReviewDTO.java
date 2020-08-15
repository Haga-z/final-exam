package kg.places.reviews.exam.DTO;

import kg.places.reviews.exam.model.Place;
import kg.places.reviews.exam.model.Review;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PACKAGE)
@ToString
public class ReviewDTO {
    private Integer id;
    private String text;
    private double mark;
    private Timestamp date;
    private UserDTO user;
    private PlaceDTO place;

    public static ReviewDTO from(Review review){
        return builder()
                .date(review.getDate())
                .id(review.getId())
                .mark(review.getMark())
                .place(PlaceDTO.from(review.getPlace()))
                .text(review.getText())
                .user(UserDTO.from(review.getUser()))
                .build();
    }
}
