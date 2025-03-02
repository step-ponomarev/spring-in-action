package tacos.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import tacos.order.data.TacoOrder;

@Component
public class RabbitMqOrderReceiver implements OrderReceiver {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqOrderReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "taco.order.queue")
    public void receiveOrder(TacoOrder order) {
        System.out.println("Got: " + order);
    }

    @Override
    public TacoOrder receiveOrder() {
        return (TacoOrder) rabbitTemplate.receiveAndConvert("taco.order.queue");
    }
}
