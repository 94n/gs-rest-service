package api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AcceptanceController {

    @RequestMapping("/accept")
    public void accept(@RequestParam(value = "route") int route, @RequestParam(value = "departureTime") LocalDateTime departureTime) {

    }

}
