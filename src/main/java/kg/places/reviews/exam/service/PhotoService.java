package kg.places.reviews.exam.service;

import kg.places.reviews.exam.exception.FileStorageException;
import kg.places.reviews.exam.model.Photo;
import kg.places.reviews.exam.model.Place;
import kg.places.reviews.exam.repository.PhotoRepository;
import kg.places.reviews.exam.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class PhotoService {
    private final PlaceRepository placeRepository;
    private final PhotoRepository photoRepository;

    public void storeFile(MultipartFile file,Integer place_id) {
        var place = placeRepository.findById(place_id).get();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            var photo = Photo.builder()
                    .photo(file.getBytes())
                    .name(file.getOriginalFilename())
                    .place(place)
                    .build();

            photoRepository.save(photo);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
}
