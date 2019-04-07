package rooting.service;

import rooting.model.Point;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author agavrikov
 * @date 26.01.19
 */
public class PointService {

    private Connection connect() {
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public List<Point> getStockPoint(long stockId) {
        Set<Point> points = new HashSet<>();
        String sql =
            "SELECT latitude, longitude " +
            "  FROM stock_order " +
            " WHERE stock_id = ? " +
            " GROUP BY latitude, longitude;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, stockId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                points.add(
                    new Point(
                        resultSet.getDouble(1),
                        resultSet.getDouble(2)
                    )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>(points);
    };
}
