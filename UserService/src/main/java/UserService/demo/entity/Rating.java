package UserService.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String UserId;
    private String hotelId;
    private int rating ;
    private String feedBack;
    private Hotel hotel;
}

