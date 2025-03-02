package tacos.queue;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import tacos.order.data.TacoOrder;
import tacos.queue.converter.OrderMessageConverter;

// Компонент будет работать только для профиля листенера конфигурации приложения
@Profile("jms-listener")
@Component
public class JmsOrderReceiver implements OrderReceiver {
    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    public JmsOrderReceiver(JmsTemplate jmsTemplate, OrderMessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    // Passive model
    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order) {
        System.out.println("Got: " + order);
    }

    //Active model
    @Override
    public TacoOrder receiveOrder() throws JMSException {
        final Message receive = jmsTemplate.receive("tacocloud.order.queue");

        return (TacoOrder) messageConverter.fromMessage(receive);
    }
}
