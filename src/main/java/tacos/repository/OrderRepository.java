package tacos.repository;

import tacos.data.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
