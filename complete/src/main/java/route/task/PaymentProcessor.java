package route.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component("real")
public class PaymentProcessor {

    private final WebClient webClient;

    @Autowired
    public PaymentProcessor(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder != null ? webClientBuilder.baseUrl("http://localhost:8080").build() : null;
    }

    public String tryPay() {
        return this.webClient.get().uri("/pay").retrieve().bodyToMono(String.class).block();
    }

}
