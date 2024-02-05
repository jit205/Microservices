package UserService.demo.service.impl;

import UserService.demo.entity.Hotel;
import UserService.demo.entity.Rating;
import UserService.demo.entity.User;
import UserService.demo.exception.ResourceNotFoundException;
import UserService.demo.repository.UserRepository;
import UserService.demo.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class Userserviceimpl implements Userservice {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User u) {
        String randomId= UUID.randomUUID().toString();
        u.setUserId(randomId);
       return  userRepository.save(u);
    }
    @Override
    public List<User> alluser() {
        return userRepository.findAll();
    }


    @Override
    public User getBYId(String id) {


            User user =   userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user with given id is not present on server side "+id));
            //Fetching Rating of user usng microservice using RestTemplet
            //http://localhost:8083/rating/users/c1bbaaba-868c-4194-b690-075812fcb3b5
           Rating[] ratings= restTemplate.getForObject("http://RATING-SERVICE/rating/users/"+id, Rating[].class);

            List<Rating> ratings1=Arrays.stream(ratings).toList();
            //Now geting Hotel details from every rating

            List<Rating> ratingList = ratings1.stream().map(rating -> {
            //http://localhost:8082/hotels/4705efa4-c766-41de-b076-73011459db02

                Hotel hotel=  restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

                rating.setHotel(hotel);

                return rating;
        }).collect(Collectors.toList());

            user.setRating(ratings1);

            return user;
    }

}
