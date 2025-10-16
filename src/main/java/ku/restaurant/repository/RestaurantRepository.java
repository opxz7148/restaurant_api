package ku.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import ku.restaurant.entity.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Restaurant findByName(String name);
    List<Restaurant> findByLocation(String location);
}
