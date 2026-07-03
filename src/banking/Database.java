package banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database{
    private static final String url = "jdbc:sqlite:card.s3db";

    public static Connection connect() throws SQLException{
        return DriverManager.getConnection(url);
    }

    public static void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS card(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                number TEXT,
                pin TEXT,
                balance INTEGER DEFAULT 0
                );
                """;

        try(Connection connection = Database.connect();
            Statement stmt = connection.createStatement();
        ){
            stmt.execute(sql);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}