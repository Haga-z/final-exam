package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.DTO.PlaceDTO;
import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.repository.UserRepository;
import kg.places.reviews.exam.service.PropertiesService;
import kg.places.reviews.exam.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PropertiesService propertiesService;

    @GetMapping("/")
    public String getIndex(Authentication authentication, Model model, HttpServletRequest uriBuilder, Pageable pageable){
            var user = userRepository.findByEmail(authentication.getName());
            Page<PlaceDTO> places = placeRepository.findAllByOrderByDateDesc(pageable).map(PlaceDTO::from);
            model.addAttribute("user", user);
            var uri = uriBuilder.getRequestURI();
        constructPageable(places,propertiesService.getDefaultPageSize(),model,uri);
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
    @GetMapping("/error")
    public String error(Model model, Principal principal){
        var user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "error";
    }
}
