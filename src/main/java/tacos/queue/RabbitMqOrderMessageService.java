package tacos.queue;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tacos.order.data.TacoOrder;

@Service
public class RabbitMqOrderMessageService implements OrderMessageService {
    private final RabbitTemplate rabbit;

    @Autowired
    public RabbitMqOrderMessageService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        final MessageConverter messageConverter = rabbit.getMessageConverter();

        rabbit.convertAndSend(
                "taco.order.queue",
                order
        );
    }
}
