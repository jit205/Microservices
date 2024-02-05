package RatingService.demo.service;

import RatingService.demo.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating r);

    //get by hotelId
    List<Rating> getRatingByhotelId(String hotelId);
    // get by userId
    List<Rating> getRatingByuserId(String userId);

    //get by RatingId
//    Rating getBYRatingId(String id);

    //get all Rating
    List<Rating> getAllRating();
}
