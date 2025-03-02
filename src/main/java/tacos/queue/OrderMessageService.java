package tacos.queue;

import tacos.order.data.TacoOrder;

public interface OrderMessageService {
    void sendOrder(TacoOrder order);
}
