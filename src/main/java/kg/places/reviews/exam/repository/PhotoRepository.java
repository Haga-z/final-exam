package kg.places.reviews.exam.repository;

import kg.places.reviews.exam.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Integer> {
    Photo findByName(String name);
    List<Photo> findAllByPlaceId(Integer place_id);
}
