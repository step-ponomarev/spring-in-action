package tacos.repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tacos.data.Ingredient;
import tacos.data.Taco;
import tacos.data.TacoOrder;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private static final String CREATE_ORDER_SQL = "insert into Taco_Order (dilivery_Name, delivery_Street, delivery_City, " +
        "delivery_State, delivery_Zip, cc_number, " +
        "cc_expiration, cc_cvv, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String CREATE_TACO_SQL = "insert into Taco (name, taco_order, taco_order_key, created_at) values (?, ?, ?, ?)";
    private static final String CREATE_INGREDIENT_REF_SQL = "insert into Ingredient_Ref (ingredient, taco, taco_key) values (?, ?, ?)";

    private final JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        final PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                CREATE_ORDER_SQL,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );

        pscf.setReturnGeneratedKeys(true);

        order.setCreatedAt(new Date());

        final PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                List.of(
                        order.getDeliveryName(),
                        order.getDeliveryStreet(),
                        order.getDeliveryCity(),
                        order.getDeliveryState(),
                        order.getDeliveryZip(),
                        order.getCcNumber(),
                        order.getCcExpiration(),
                        order.getCcCVV(),
                        order.getCreatedAt()
                )
        );

        final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, generatedKeyHolder);
        final long id = generatedKeyHolder.getKey().longValue();
        order.setId(id);

        List<Taco> tacos = order.getTacos();
        for (int i = 0; i < tacos.size(); i++) {
            saveTaco(id, i, tacos.get(i));
        }

        return order;
    }

    private void saveTaco(long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        final PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(CREATE_TACO_SQL, Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);

        final PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                List.of(taco.getName(), orderId, orderKey, taco.getCreatedAt())
        );

        final GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, generatedKeyHolder);

        long id = generatedKeyHolder.getKey().longValue();
        taco.setId(id);

        saveIngredientsRef(id, taco.getIngredients());
    }

    private void saveIngredientsRef(long tacoId, List<Ingredient> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            jdbcOperations.update(CREATE_INGREDIENT_REF_SQL, ingredients.get(i).getId(), tacoId, i);
        }
    }
}
