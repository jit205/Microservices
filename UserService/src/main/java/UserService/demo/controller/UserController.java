package UserService.demo.controller;

import UserService.demo.entity.User;
import UserService.demo.service.Userservice;
import UserService.demo.service.impl.Userserviceimpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    Userservice userservice;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
     User user1 = userservice.saveUser(user);
     return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //get user
    @GetMapping("{userId}")
//    circuitBreaker use for fault tolrence there are three state open close halfopen state are there
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod ="ratinghotelCallback" )
//    retry we have to set two veriable max-attempt and waitduration that how how much
    // try to fetch request
//    @Retry(name="ratingHotelService", fallbackMethod = "ratinghotelCallback")
// ratelimit is how much time you have to request service in 1s mainly it is used
// for security and perfomance to to avoid dos attact so that server not get crash
   @RateLimiter(name="ratingHotelRateLimiter",fallbackMethod = "ratinghotelCallback")
    public ResponseEntity<User> getUser(@PathVariable String userId)
    {
//        logger.info("rating count {}",x);
////        System.out.println("retry count "+x);
//        x++;
       User user= userservice.getBYId(userId);
        return  ResponseEntity.ok(user);
    }
//    int x=1;
    public ResponseEntity<User> ratinghotelCallback(String id,Exception e)
    {
        e.printStackTrace();
        User user = new User();
        user.setUserId("1234");
        user.setName("dummy");
        user.setAbout("hello");
        user.setEmail("dummy@gmail.com");
        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
    }
    //all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users= userservice.alluser();
         return ResponseEntity.ok(users);
    }
}
