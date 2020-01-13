package api;

import data.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CreationController {

    @Autowired
    private TripRepository tripRepository;

    @RequestMapping("/create")
    public void create(@RequestParam(value = "route") int route, @RequestParam(value = "departureTime") LocalDateTime departureTime) {

    }

}
