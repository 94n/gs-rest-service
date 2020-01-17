package route.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import route.data.Trip;
import route.data.TripRepository;

@Component
public class PaymentTask {

    private final TripRepository tripRepository;

    private final WebClient webClient;

    @Autowired
    public PaymentTask(TripRepository tripRepository, WebClient.Builder webClientBuilder) {
        this.tripRepository = tripRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @Scheduled(fixedRate = 6000)
    public void tryPayment() {
        Trip trip = tripRepository.findFirstByStateOrStateNull("processing");
        if (trip != null) {
            String state = this.webClient.get().uri("/pay").retrieve().bodyToMono(String.class).block();
            trip.setState(state);
            tripRepository.save(trip);
            System.out.println(trip.getId() + " " + state);
        } else {
            System.out.println("All done");
        }
    }

}
