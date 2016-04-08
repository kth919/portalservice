package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by admin on 2016-04-08.
 */
public class AddUserStatementStrategey implements StatementStrategy {
    private User user;
    public AddUserStatementStrategey(User user){this.user = user;}

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {

        String sql = "insert into userinfo (name, password) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();

        return preparedStatement;
    }
}
