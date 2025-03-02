package tacos.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import tacos.order.data.TacoOrder;

@Service
public class KafkaOrderMessageService implements OrderMessageService {
    private final KafkaTemplate<String, TacoOrder> template;

    @Autowired
    public KafkaOrderMessageService(KafkaTemplate<String, TacoOrder> template) {
        this.template = template;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        template.send("taco.orders.topic", order);
    }
}
