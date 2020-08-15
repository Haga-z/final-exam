package kg.places.reviews.exam.repository;

import kg.places.reviews.exam.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaceRepository extends JpaRepository<Place,Integer> {
    Place findByTitle(String title);
    @Query("SELECT p FROM Place p WHERE (p.title like concat(:name, '%')) or (p.title like concat('%',:name,'%')) or (p.title like concat('%', :name)) or " +
            "(p.description like concat(:name, '%')) or (p.description like concat('%',:name,'%')) or (p.description like concat('%', :name))")
    Page<Place> findPlaceByTitle(String name, Pageable pageable);
}
