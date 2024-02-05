package RatingService.demo.service.impl;

import RatingService.demo.entity.Rating;
import RatingService.demo.repository.RatingRepository;
import RatingService.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating r) {
        String ratingId= UUID.randomUUID().toString();
        r.setRatingId(ratingId);
        return ratingRepository.save(r);
    }

    @Override
    public List<Rating> getRatingByhotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getRatingByuserId(String userId) {
        System.out.println("service = " + ratingRepository.findByUserId(userId));
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }
}
