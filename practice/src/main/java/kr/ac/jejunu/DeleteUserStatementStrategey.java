package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by admin on 2016-04-08.
 */
public class DeleteUserStatementStrategey implements StatementStrategy {
   private Long id;
    public DeleteUserStatementStrategey(Long id){this.id = id;}

    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {

        String sql = "delete from userinfo where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        return preparedStatement;

    }
}
