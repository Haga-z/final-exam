package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.DTO.PlaceDTO;
import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.repository.UserRepository;
import kg.places.reviews.exam.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @GetMapping("/")
    public String getIndex(Authentication authentication, Model model){
            var user = userRepository.findByEmail(authentication.getName());
            List<PlaceDTO> places = placeRepository.findAll().stream().map(PlaceDTO::from).collect(Collectors.toList());
            model.addAttribute("user", user);
            model.addAttribute("places",places);
        return "index";
    }
}
