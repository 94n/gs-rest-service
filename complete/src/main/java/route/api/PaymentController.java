package route.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class PaymentController {

    @RequestMapping("/pay")
    public String pay() {
        List<String> states = Arrays.asList("processing", "error", "done");
        return states.get(new Random().nextInt(states.size()));
    }

}
