/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package route.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import route.task.PaymentTask;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentTask paymentTask;

    @Test
    public void checkNewUnprocessedTripShouldReturnEmptyId() throws Exception {
        short route = (short) new Random().nextInt(Short.MAX_VALUE);
        long randomTime = ThreadLocalRandom.current().nextLong(Instant.now().getEpochSecond(), LocalDateTime.of(2099, 12, 31, 23, 59).toInstant(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())).getEpochSecond());
        String routeTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(randomTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm"));
        String tripId = this.mockMvc.perform(get("/create?route=" + route + "&time=" + routeTime)).andReturn().getResponse().getContentAsString();
        this.mockMvc.perform(get("/check?id=" + tripId)).andExpect(status().isOk()).andExpect(content().string(""));
    }

    @Test
    public void createRoutePaymentShouldReturnNumber() throws Exception {
        short route = (short) new Random().nextInt(Short.MAX_VALUE);
        long randomTime = ThreadLocalRandom.current().nextLong(Instant.now().getEpochSecond(), LocalDateTime.of(2099, 12, 31, 23, 59).toInstant(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())).getEpochSecond());
        String routeTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(randomTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm"));
        this.mockMvc.perform(get("/create?route=" + route + "&time=" + routeTime)).andExpect(status().isOk()).andExpect(jsonPath("$").isNumber());
    }

    @Test
    public void payShouldReturnKnownState() throws Exception {
        List<String> states = Arrays.asList("processing", "error", "done");
        assertTrue("incorrect state after payment start", states.contains(this.mockMvc.perform(get("/pay")).andReturn().getResponse().getContentAsString()));
    }

    @Test
    public void paymentTaskShouldSetKnownState() throws Exception {
        List<String> states = Arrays.asList("processing", "error", "done");
        short route = (short) new Random().nextInt(Short.MAX_VALUE);
        long randomTime = ThreadLocalRandom.current().nextLong(Instant.now().getEpochSecond(), LocalDateTime.of(2099, 12, 31, 23, 59).toInstant(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())).getEpochSecond());
        String routeTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(randomTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm"));
        this.mockMvc.perform(get("/create?route=" + route + "&time=" + routeTime)).andReturn().getResponse().getContentAsString();
        try {
            paymentTask.tryPayment();
        } catch (Exception e) {
            assertTrue("error during task", false);
        }
    }

}
