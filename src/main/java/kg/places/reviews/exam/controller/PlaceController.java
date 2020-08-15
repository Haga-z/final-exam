package kg.places.reviews.exam.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceController {

    @GetMapping("/add_new_place")
    public String addNewPlace(Model model){
        return "add_new_place";
    }
    @PostMapping("/add_new_place")
    public String addNewPlace(@RequestParam("title")String title,
                              @RequestParam("description")String description,
                              @RequestParam("photo")MultipartFile photo){
        return "redirect:/"
    }
}
