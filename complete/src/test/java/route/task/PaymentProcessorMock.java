package route.task;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component("mock")
public class PaymentProcessorMock extends PaymentProcessor {

    public PaymentProcessorMock() {
        super(null);
    }

    @Override
    public String tryPay() {
        List<String> states = Arrays.asList("processing", "error", "done");
        return states.get(new Random().nextInt(states.size()));
    }

}
