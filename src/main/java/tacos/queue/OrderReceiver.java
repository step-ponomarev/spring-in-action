package tacos.queue;

import jakarta.jms.JMSException;
import tacos.order.data.TacoOrder;

public interface OrderReceiver {
    TacoOrder receiveOrder() throws JMSException;
}
