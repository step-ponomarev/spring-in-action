package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.data.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient;",
                JdbcIngredientRepository::extract
        );
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        final List<Ingredient> res = jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?;",
                JdbcIngredientRepository::extract,
                id
        );

        return res.isEmpty()
                ? Optional.empty()
                : Optional.of(res.get(0));
    }

    @Override
    public Iterable<Ingredient> findByType(Ingredient.Type type) {
        return jdbcTemplate.query(
                "select id, name, type from Ingredient where type=?;",
                JdbcIngredientRepository::extract,
                type.toString()
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString()
        );

        return ingredient;
    }

    private static Ingredient extract(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }
}
