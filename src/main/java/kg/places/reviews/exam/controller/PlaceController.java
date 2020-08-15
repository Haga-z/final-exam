package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.DTO.PlaceDTO;
import kg.places.reviews.exam.DTO.ReviewDTO;
import kg.places.reviews.exam.model.Review;
import kg.places.reviews.exam.repository.PhotoRepository;
import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.repository.ReviewRepository;
import kg.places.reviews.exam.repository.UserRepository;
import kg.places.reviews.exam.service.PlaceService;
import kg.places.reviews.exam.service.PropertiesService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceController {
    private final UserRepository userRepository;
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;
    private final PropertiesService propertiesService;
    private final PhotoRepository photoRepository;

    @GetMapping("/add_new_place")
    public String addNewPlace(Model model){
        return "add_new_place";
    }
    @PostMapping("/add_new_place")
    public String addNewPlace(@RequestParam("title")String title,
                              @RequestParam("description")String description,
                              @RequestParam("photo")MultipartFile photo,
                              Principal principal){
        try {
            placeService.storeFile(photo,description,title,principal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    @RequestMapping("/image/{personId}")
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(@PathVariable String personId) {
        byte[] image = placeRepository.findByTitle(personId).getMain_photo();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
    @GetMapping("/single_place/{id}")
    public String getSinglePlace(@PathVariable("id")Integer id,
                                 Model model,Principal principal){
        var place = PlaceDTO.from(placeRepository.findById(id).get());
        var user = userRepository.findByEmail(principal.getName());
        var reviews = reviewRepository.findAllByPlaceId(place.getId()).stream().map(ReviewDTO::from).collect(Collectors.toList());
        double mark = 0;
        for (Review r : reviewRepository.findAllByPlaceId(place.getId())){
            mark += r.getMark();
        }
        model.addAttribute("photos",photoRepository.findAllByPlaceId(id));
        model.addAttribute("user", user);
        model.addAttribute("place",place);
        model.addAttribute("reviews",reviews);
        model.addAttribute("rating",mark/reviews.size());

        return "single_place";
    }
    @GetMapping("/search/{search}")
    public String search(@PathVariable("search") String search, Principal principal, Model model, Pageable pageable, HttpServletRequest uriBuilder){
        var places = placeService.getPlaceSearch(search, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(places,propertiesService.getDefaultPageSize(),model,uri);
        model.addAttribute("user", userRepository.findByEmail(principal.getName()));
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
