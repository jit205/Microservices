package RatingService.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Rating")
public class Rating {

    @Id
    private String ratingId;
    private String hotelId;
    private String userId;
    private  int rating;
    private String feedback;

}
