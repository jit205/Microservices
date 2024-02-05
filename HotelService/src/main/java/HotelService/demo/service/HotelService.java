package HotelService.demo.service;

import HotelService.demo.entity.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel create (Hotel h);
    //get
    Hotel get(String id);
    // get all
    List<Hotel> getAll();
}
