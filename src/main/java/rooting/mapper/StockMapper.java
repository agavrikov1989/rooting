package rooting.mapper;

import org.springframework.jdbc.core.RowMapper;
import rooting.model.Point;
import rooting.model.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author agavrikov
 * @date 31.01.19
 */
public class StockMapper implements RowMapper<Stock> {
    @Override
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stock stock = new Stock();
        stock.setId(rs.getLong("id"));
        stock.setLocation(
                new Point(
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                )
        );
        return stock;
    }
}
