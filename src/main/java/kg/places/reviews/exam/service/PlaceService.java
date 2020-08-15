package kg.places.reviews.exam.service;

import kg.places.reviews.exam.DTO.PlaceDTO;
import kg.places.reviews.exam.exception.FileStorageException;
import kg.places.reviews.exam.model.Place;
import kg.places.reviews.exam.repository.PlaceRepository;
import kg.places.reviews.exam.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.type.BinaryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;


@Service
@AllArgsConstructor
public class PlaceService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public void storeFile(MultipartFile file, String description, String title, Principal principal) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            var place = Place.builder()
                    .description(description)
                    .main_photo(file.getBytes())
                    .title(title)
                    .date(new Timestamp(System.currentTimeMillis()))
                    .user(userRepository.findByEmail(principal.getName()))
                    .build();
            placeRepository.save(place);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Page<PlaceDTO> getPlaceSearch(String text, Pageable pageable){
        return placeRepository.findPlaceByTitle(text, pageable)
                .map(PlaceDTO::from);
    }
}
