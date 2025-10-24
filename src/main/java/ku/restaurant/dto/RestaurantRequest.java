package ku.restaurant.dto;

import lombok.Data;

@Data
public class RestaurantRequest {
    private String name;
    private String location;
    private double rating;
}
