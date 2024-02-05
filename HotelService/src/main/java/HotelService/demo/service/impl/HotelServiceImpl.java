package HotelService.demo.service.impl;

import HotelService.demo.entity.Hotel;
import HotelService.demo.exception.ResourceNotFoundException;
import HotelService.demo.repository.HotelRepository;
import HotelService.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
   @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel h) {
        String hotelId = UUID.randomUUID().toString();
        h.setHotelId(hotelId);
        return hotelRepository.save(h);
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException());
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
