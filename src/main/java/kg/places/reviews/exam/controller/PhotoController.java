package kg.places.reviews.exam.controller;

import kg.places.reviews.exam.repository.PhotoRepository;
import kg.places.reviews.exam.service.PhotoService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PhotoController {
    private final PhotoRepository photoRepository;
    private final PhotoService photoService;

    @RequestMapping("/photo/{personId}")
    @ResponseBody
    public HttpEntity<byte[]> getPhoto(@PathVariable String personId) {
        byte[] image = photoRepository.findByName(personId).getPhoto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(image.length);
        return new HttpEntity<byte[]>(image, headers);
    }
    @PostMapping("/add_photo")
    public String addPhoto(@RequestParam("place_id")Integer place_id,
                           @RequestParam("photo")MultipartFile photo){
        photoService.storeFile(photo,place_id);
        return "redirect:/single_place/"+place_id;
    }
}
