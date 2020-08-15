package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.DTO.PlaceDTO;
import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.repository.UserRepository;
import kg.places.reviews.exam.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    @GetMapping("/")
    public String getIndex(Authentication authentication, Model model, HttpServletRequest uriBuilder, Pageable pageable){
            var user = userRepository.findByEmail(authentication.getName());
            List<PlaceDTO> places = placeRepository.findAll().stream().map(PlaceDTO::from).collect(Collectors.toList());
            model.addAttribute("user", user);
            model.addAttribute("places",places);
        return "index";
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }
}
