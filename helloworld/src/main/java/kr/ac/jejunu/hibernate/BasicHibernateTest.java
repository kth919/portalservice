package kr.ac.jejunu.hibernate;

import kr.ac.jejunu.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by admin on 2016-06-05.
 */
 public class BasicHibernateTest {

        private Logger logger = (Logger) LoggerFactory.getLogger(BasicHibernateTest.class);

    SessionFactory sessionFactory;

                 @Before
         public void setup() {
                 Configuration configuration = new Configuration().configure("jejunu.cfg.xml");
                 configuration.addResource("User.hbm.xml");
                 StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
                 sb.applySettings(configuration.getProperties());
                 StandardServiceRegistry standardServiceRegistry = sb.build();
                 sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
             }

                 @After
         public void finish() {
                 sessionFactory.close();
             }

                 @Test
         public void getUserTest() {
                 Session session = sessionFactory.openSession();
                 User user = session.get(User.class, 1);
                 assertThat(user.getName(), is("허윤호"));
                 assertThat(user.getPassword(), is("1234"));
                     session.close();
             }

    @Test
    public void saveUserTest(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setName("testuser");
        user.setPassword("1111");
        session.save(user);

        User savedUser = session.get(User.class , user.getId());
                 assertThat(savedUser.getName(), is(user.getName()));
                 assertThat(savedUser.getPassword(), is(user.getPassword()));

                         session.getTransaction().commit();
                 session.close();

    }

            }

