import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker implements ConnectionMaker {
    public SimpleConnectionMaker() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf-8", "jeju", "jejupw");
        return connection;
    }
}