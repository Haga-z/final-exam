package kg.places.reviews.exam.repository;

import kg.places.reviews.exam.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    Place findByTitle(String title);
}
