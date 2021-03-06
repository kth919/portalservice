import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by admin on 2016-03-25.
 */
public class UserDao {

    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {this.connectionMaker = connectionMaker; } //생성자에게 dependency던짐


    public User get(Long id) throws ClassNotFoundException, SQLException {

        Connection connection = connectionMaker.getConnection();

     //쿼리만들고
        String sql = "select *from userinfo where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
     //실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
     //결과를 오브젝트에 잘 매핑하고
     User user = new User();
     user.setId(resultSet.getLong("id"));
     user.setName(resultSet.getString("name"));
     user.setPassword(resultSet.getString("password"));

      //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();
     //매핑된결과를 리턴
    return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        Connection connection = connectionMaker.getConnection();

        String sql = "insert into userinfo (name, password) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        //자원해지

        Long id = getLastInsertId(connection);
        preparedStatement.close();
        connection.close();
        //매핑된결과를 리턴
        return id;

    }

    /*private Connection getConnection() throws ClassNotFoundException, SQLException { //db연결
        //db는? mysql
        // 위치는? 로컬
        //table은 userinfo
        //Load Driver
        Class.forName("com.mysql.jdbc.Driver");
        //Connection맺고
        Connection connection = DriverManager.getConnection("jdbc:mysql://db.skyserv.kr/jejunu?characterEncoding=utf-8", "jeju", "jejupw");
        //쿼리만들고
        return connection;
    }*/

    public Connection getConnection() throws ClassNotFoundException, SQLException{
        //db는? mysql
        // 위치는? 로컬
        //table은 userinfo
        //Load Driver
        //Connection맺고
        //쿼리만들고
        return connectionMaker.getConnection();
    }

    private Long getLastInsertId(Connection connection) throws SQLException { //최근의 id획득

        String sql2 = "select last_insert_id()";
        PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
        ResultSet resultSet = preparedStatement2.executeQuery();
        resultSet.next();

        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement2.close();
        return id;

    }
}
