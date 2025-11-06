package ku.restaurant.repository;

import ku.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ku.restaurant.entity.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    boolean existsByName(String name);
    Optional<Restaurant> findByName(String name);
    List<Restaurant> findByLocation(String location);
}
