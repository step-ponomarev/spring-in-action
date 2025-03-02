package tacos.queue;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import tacos.order.data.TacoOrder;

@Component
public class KafkaOrderReceiver implements OrderReceiver {

    @KafkaListener(topics = "taco.orders.topic", groupId = "taco.group")
    public void receiveOrder(TacoOrder order) {
        System.out.println("Got: " + order);
    }

    @Override
    public TacoOrder receiveOrder() {
        return null;
    }
}
