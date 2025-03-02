package tacos.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.order.data.TacoOrder;

@Service
public class JmsOrderMessageService implements OrderMessageService {
    private JmsTemplate jmsTemplate;

    @Autowired
    public JmsOrderMessageService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jmsTemplate.send(
                "tacocloud.order.queue", session -> session.createObjectMessage(order)
        );
    }
}
