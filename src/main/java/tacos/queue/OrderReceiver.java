package tacos.queue;

import tacos.order.data.TacoOrder;

public interface OrderReceiver {
    TacoOrder receiveOrder();
}
