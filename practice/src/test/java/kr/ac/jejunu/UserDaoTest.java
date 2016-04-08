package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by admin on 2016-03-25.
 */
    public class UserDaoTest {


    private UserDao userDao;

    @Before
    public void setup(){

        ApplicationContext applicationContext =
                new GenericXmlApplicationContext("daoFactory.xml");

       // ApplicationContext = applicationContext
       //         = new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = (UserDao) applicationContext.getBean("userDao");
       // userDao = new DaoFactory().getUserDao();


    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        //UserDao userDao = new UserDao(new SimpleConnectionMaker());

        //UserDao userDao = new DaoFactory().userDao(); // 의존성을 dependency를 던져줌. dependency look up 의존성 탐색
                                                        //-> daofactory에게 의존성을 찾아달라고 부탁

        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);

        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        User user = new User();

        String name= "헐크";
        String passward = "2222";

        user.setName(name);
        user.setPassword(passward);

        //UserDao userDao = new UserDao();

       // UserDao userDao = new DaoFactory().userDao(); // 의존성을 dependency를 던져줌

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);


    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException{
        User user = new User();

        String name = "헐크";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        userDao.delete(id);

        User resultUser = userDao.get(id);
        assertThat(resultUser, nullValue());

    }

    @Test
    public void update() throws SQLException, ClassNotFoundException{
        User user = new User();

        String name = "헐크";
        String password = "2222";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);


        User user1 = userDao.get(id);
        name = "rlaxogns";
        password = "1234";
        user1.setName(name);
        user1.setPassword(password);

        userDao.update(user1);

        userDao.get(user1.getId());
        User resultUser = userDao.get(id); // 여기까지
        validate(name, password, id, resultUser);

    }

    private void validate(String name, String password , Long id , User resultUser){
        assertThat(resultUser.getId(), is(id) );
        assertThat(resultUser.getName(), is(name) );
        assertThat(resultUser.getPassword(), is(password));

    }


}
