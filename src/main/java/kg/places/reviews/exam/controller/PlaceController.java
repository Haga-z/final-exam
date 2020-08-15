package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.service.PlaceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.security.Principal;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceController {
    private final PlaceService placeService;
    private final PlaceRepository placeRepository;

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
}
