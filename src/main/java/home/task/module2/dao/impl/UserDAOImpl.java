package home.task.module2.dao.impl;

import home.task.module2.User;
import home.task.module2.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;
    private final String url;
    private final String password;
    private final String userName;

    public UserDAOImpl(String url, String userName, String password) {
        this.url = url;
        this.password = password;
        this.userName = userName;
        setSessionFactory();
    }

    public UserDAOImpl() {
        Properties properties = new Properties();
        try {
            properties.load((Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("db.properties")));
            url = properties.getProperty("hibernate.connection.url");
            password = properties.getProperty("hibernate.connection.password");
            userName = properties.getProperty("hibernate.connection.username");
            setSessionFactory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("hibernate.properties"));
            properties.put("hibernate.connection.url", url);
            properties.put("hibernate.connection.password", password);
            properties.put("hibernate.connection.username", userName);
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при создании пользователя", e);
        }
        return user;
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении пользователя", e);
        }
        return user;
    }

    @Override
    public User get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении пользователя", e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении пользователей", e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при удалении пользователя", e);
        }
    }
}
