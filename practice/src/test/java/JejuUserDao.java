import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by admin on 2016-04-01.
 */
public class JejuUserDao extends UserDao {

    public Connection getConnection() throws ClassNotFoundException, SQLException { //db연결

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf-8", "jeju", "jejupw");

        return connection;
    }

}
