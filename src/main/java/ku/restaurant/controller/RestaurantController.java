package ku.restaurant.controller;

import ku.restaurant.entity.Restaurant;
import ku.restaurant.repository.RestaurantRepository;
import ku.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurant() {
        return restaurantService.getAll();
    }


    @PostMapping("/restaurants")
    public Restaurant create(@RequestBody Restaurant restaurant) {
        return restaurantService.create(restaurant);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/restaurants")
    public Restaurant update(@RequestBody Restaurant restaurantUpdate) {
        return restaurantService.update(restaurantUpdate);
    }
}
