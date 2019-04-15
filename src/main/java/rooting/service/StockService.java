package rooting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rooting.mapper.StockMapper;
import rooting.model.Stock;

import java.util.List;

/**
 * @author agavrikov
 * @date 26.01.19
 */
@Component
public class StockService {

    private static final String SEQUENCE = "stock_seq";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Stock getStock(long id) {
        String sql =
            "SELECT id, creation_time, latitude, longitude " +
            "  FROM stock " +
            " WHERE id = ?";
        return jdbcTemplate.query(sql, new StockMapper(), id).stream().findFirst().orElse(null);
    };

    public List<Stock> getStocks() {
        String sql =
            "SELECT id, creation_time, latitude, longitude " +
            "  FROM stock";
        return jdbcTemplate.query(sql, new StockMapper());
    };

    public void saveStock(double latitude, double longitude) {
        String sql =
            "INSERT " +
            "  INTO stock (id, latitude, longitude) " +
            "VALUES (nextval('" + SEQUENCE + "'), ?, ?)";
        jdbcTemplate.update(sql, latitude, longitude);
    }
}
