package tacos.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {
    private final RestTemplate restClient;

    @Autowired
    public OrderSubmitMessageHandler(RestTemplate restTemplate) {
        this.restClient = restTemplate;
    }

    @Override
    public Object handle(EmailOrder payload, MessageHeaders headers) {
        restClient.postForObject("example.com", payload, String.class);
        return null;
    }
}
