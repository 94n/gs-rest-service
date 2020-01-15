package route.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import route.data.Trip;
import route.data.TripRepository;

import java.util.Optional;

@RestController
public class CheckController {

    private final TripRepository tripRepository;

    @Autowired
    public CheckController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @RequestMapping("/check")
    public String check(@RequestParam(value = "id") short id) {
        Optional<Trip> trip = tripRepository.findById(id);
        return trip.map(Trip::getState).orElse(null);
    }

}
