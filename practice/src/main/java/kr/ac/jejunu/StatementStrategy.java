package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by admin on 2016-04-08.
 */
public interface StatementStrategy {

    public PreparedStatement makeStatement(Connection connection) throws SQLException;


}
