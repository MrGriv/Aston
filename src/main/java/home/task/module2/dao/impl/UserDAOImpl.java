package home.task.module2.dao.impl;

import home.task.module2.User;
import home.task.module2.dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Properties;

public class UserDAOImpl implements UserDAO {
    /**
     * Создаем SessionFactory
     */
    private static final SessionFactory sessionFactory;

    /**
     *  Создание сеансов (Session) работы с базой данных
     */
    static {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Добавление пользователя
     * @param user - пользователь для добавления
     */
    @Override
    public void add(User user) {
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
    }

    /**
     * Обновление пользователя
     * @param user - пользователь для обновления
     */
    @Override
    public void update(User user) {
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
    }

    /**
     * Получение пользователя
     * @param id - id пользователя
     */
    @Override
    public User get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            System.out.println(user);

            return user;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении пользователя", e);
        }
    }

    /**
     * Получение всех пользователей
     */
    @Override
    public void getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            System.out.println(query.list());
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении пользователей", e);
        }
    }

    /**
     * Удаление пользователя
     * @param id - id пользователя
     */
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
