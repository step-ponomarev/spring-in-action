package tacos.queue.converter;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import tacos.order.data.TacoOrder;

@Component
public class OrderMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object object, Session session) throws MessageConversionException, JMSException {
        TacoOrder order = (TacoOrder) object;
        return session.createObjectMessage(order);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException, JMSException {
        return message.getBody(TacoOrder.class);
    }
}
