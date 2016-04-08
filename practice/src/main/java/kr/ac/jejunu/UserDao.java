package kr.ac.jejunu;

import java.sql.*;

import static java.lang.Class.forName;

/**
 * Created by admin on 2016-03-25.
 */
public class UserDao {

    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    } //생성자에게 dependency던짐


    public User get(Long id) throws ClassNotFoundException, SQLException {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        User user = null;
        try {
            connection = connectionMaker.getConnection();

            StatementStrategy statementStrategy = new GetUserStatementStrategy(id);
            preparedStatement = statementStrategy.makeStatement(connection);


            //실행
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //결과를 오브젝트에 잘 매핑하고
                user = new User();
                user.setId(resultSet.getLong("id")); // 여기서문제
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }


        //매핑된결과를 리턴
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Long id;
        try {
            connection = connectionMaker.getConnection();

            StatementStrategy statementStrategy = new AddUserStatementStrategey(user);
            preparedStatement = statementStrategy.makeStatement(connection);

            id = getLastInsertId(connection);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        //자원해지
        finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        //매핑된결과를 리턴
        return id;

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

    public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionMaker.getConnection();
            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        //자원해지
        finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new DeleteUserStatementStrategey(id);
        jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }


    public void update(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new UpdateUserStatementStrategey(user);
        jdbcContextWithStatementStrategyForUpdate(statementStrategy);

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

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //db는? mysql
        // 위치는? 로컬
        //table은 userinfo
        //Load Driver
        //Connection맺고
        //쿼리만들고
        return connectionMaker.getConnection();
    }
}
