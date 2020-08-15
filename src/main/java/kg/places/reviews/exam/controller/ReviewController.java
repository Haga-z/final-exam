package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.exception.UserAlreadyRegisteredException;
import kg.places.reviews.exam.model.Review;
import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.repository.ReviewRepository;
import kg.places.reviews.exam.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.Timestamp;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    @PostMapping("/add_review")
    public String addReview(@RequestParam("mark")int mark,
                            @RequestParam("text")String text,
                            @RequestParam("place_id")Integer place_id,
                            Principal principal){
        var place = placeRepository.findById(place_id).get();
        var user = userRepository.findByEmail(principal.getName());
        var review = Review.builder()
                .date(new Timestamp(System.currentTimeMillis()))
                .mark(mark)
                .text(text)
                .place(place)
                .user(user)
                .build();
        reviewRepository.save(review);
        return "redirect:/single_place/"+ place_id;
    }
}
