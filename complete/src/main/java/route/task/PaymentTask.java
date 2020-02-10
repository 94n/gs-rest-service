package route.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import route.data.Trip;
import route.data.TripRepository;

@Component
public class PaymentTask {

    private final TripRepository tripRepository;

    private Logger logger = LoggerFactory.getLogger(PaymentTask.class);

    private PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentTask(TripRepository tripRepository, @Qualifier("real") PaymentProcessor paymentProcessor) {
        this.tripRepository = tripRepository;
        this.paymentProcessor = paymentProcessor;
    }

    @Scheduled(fixedRate = 6000)
    public short tryPayment() {
        Trip trip = tripRepository.findFirstByStateOrStateNull("processing");
        if (trip != null) {
            String state = paymentProcessor.tryPay();
            trip.setState(state);
            tripRepository.save(trip);
            logger.info(trip.getId() + " " + state);
            return trip.getId();
        } else {
            logger.info("All done");
        }
        return -1;
    }


}
