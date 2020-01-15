package route.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import route.data.Trip;
import route.data.TripRepository;

import java.util.Date;

@RestController
public class CreationController {

    private final TripRepository tripRepository;

    @Autowired
    public CreationController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @RequestMapping("/create")
    public short create(@RequestParam(value = "route") short route, @RequestParam(value = "time") @DateTimeFormat(pattern = "yyyy-MM-dd-hh-mm") Date time) {
        Trip trip = new Trip();
        trip.setRoute(route);
        trip.setTime(time);
        tripRepository.save(trip);
        return trip.getId();
    }

}
