package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.repository.UserRepository;
import kg.places.reviews.exam.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private final UserRepository userRepository;

    @GetMapping("/")
    public String getIndex(Authentication authentication, Model model){
            var user = userRepository.findByEmail(authentication.getName());
            model.addAttribute("user", user);
        return "index";
    }
}
