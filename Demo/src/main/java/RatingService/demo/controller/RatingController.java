package RatingService.demo.controller;

import RatingService.demo.entity.Rating;
import RatingService.demo.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        return ResponseEntity.ok(ratingService.create(rating));
    }


    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating()
    {
        return ResponseEntity.ok(ratingService.getAllRating());
    }


    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId)
    {
        return ResponseEntity.ok(ratingService.getRatingByhotelId(hotelId));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public  ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId)
    {
        System.out.println("controller");
        return ResponseEntity.ok(ratingService.getRatingByuserId(userId));
    }
}
