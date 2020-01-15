package route.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import route.data.Trip;
import route.data.TripRepository;

import java.time.LocalDateTime;

@Component
public class PaymentTask {

    private final TripRepository tripRepository;

    @Autowired
    public PaymentTask(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void tryPayment() {
        Trip trip = tripRepository.fi
        System.out.println(LocalDateTime.now());
    }

}
