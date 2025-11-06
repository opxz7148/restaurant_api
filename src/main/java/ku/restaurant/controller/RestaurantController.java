package ku.restaurant.controller;

import jakarta.validation.Valid;
import ku.restaurant.dto.RestaurantRequest;
import ku.restaurant.entity.Restaurant;
import ku.restaurant.repository.RestaurantRepository;
import ku.restaurant.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
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
    public Restaurant create(@Valid @RequestBody RestaurantRequest dto) {
        return restaurantService.create(dto);
    }

    @PutMapping("/restaurants")
    public Restaurant update(@Valid @RequestBody Restaurant restaurantUpdate) {
        return restaurantService.update(restaurantUpdate);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurant(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping("/restaurants/{id}")
    public Restaurant deleteRestaurant(@PathVariable UUID id) {
        return restaurantService.delete(id);
    }

    @GetMapping("/restaurants/name/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name) {
        return restaurantService.getRestaurantByName(name);
    }


    @GetMapping("/restaurants/location/{location}")
    public List<Restaurant> getRestaurantByLocation(@PathVariable String location) {
        return restaurantService.getRestaurantByLocation(location);
    }

}
