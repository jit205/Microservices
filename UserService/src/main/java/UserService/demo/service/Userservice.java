package UserService.demo.service;

import UserService.demo.entity.User;

import java.util.List;

public interface Userservice {
    //create
    User saveUser(User u);
    //delete
//    String deleteUser(String id);
    // all user
    List<User> alluser();
    //get user
    User getBYId(String id);

}
