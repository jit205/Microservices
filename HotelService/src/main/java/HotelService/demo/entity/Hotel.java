package HotelService.demo.entity;


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
@Table(name="Hotel")
public class Hotel {

    @Id
    private String hotelId;
//    private String userId;
    private String name;
    private String Location;
    private String about;

}
