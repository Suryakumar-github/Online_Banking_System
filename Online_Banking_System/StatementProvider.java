import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementProvider {

    public static PreparedStatement getPreparedStatement(String query) {
        PreparedStatement statement = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}

