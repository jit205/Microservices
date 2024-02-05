package HotelService.demo.controller;

import HotelService.demo.entity.Hotel;
import HotelService.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private  HotelService hotelService;


    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> create(@RequestBody Hotel hotel)
    {
        Hotel h=hotelService.create(hotel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(h);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>>getAllHotel()
    {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("{hotelId}")
    public ResponseEntity<Hotel> getById(@PathVariable String hotelId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }
}
