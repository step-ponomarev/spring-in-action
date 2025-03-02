package tacos.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import tacos.order.data.TacoOrder;
import tacos.queue.converter.OrderMessageConverter;

@Service
public class JmsOrderMessageService implements OrderMessageService {
    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    @Autowired
    public JmsOrderMessageService(JmsTemplate jmsTemplate, OrderMessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(
                "tacocloud.order.queue", session -> messageConverter.toMessage(order, session)
        );
    }
}
