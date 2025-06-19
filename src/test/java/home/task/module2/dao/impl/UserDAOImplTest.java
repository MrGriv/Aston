package home.task.module2.dao.impl;

import home.task.module2.User;
import home.task.module2.dao.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class UserDAOImplTest {
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");
    private static final String CLEAR_TABLE = "TRUNCATE TABLE users RESTART IDENTITY";

    private UserDAO userDAO;
    private User newUser;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAOImpl(postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword());
        newUser = new User(null, "Иван", "k@mail.ru", 30, LocalDate.now());
    }

    @AfterEach
    void tearDown() throws Exception {
        Connection connection = DriverManager.getConnection(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword()
        );

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(CLEAR_TABLE);
        }
    }

    @Test
    @DisplayName("Успешно найденный пользователь")
    void whenAddUserThenUserCanBeRetrieved() {
        User user = newUser;

        userDAO.add(user);
        User retrievedUser = userDAO.get(1L);

        assertNotNull(retrievedUser, "Добавленный пользователь должен быть найден в БД");
        assertEquals("Иван", retrievedUser.getName(),
                "Имя должно соответствовать установленному значению");
        assertEquals("k@mail.ru", retrievedUser.getEmail(),
                "Почта должна соответствовать установленному значению");
        assertEquals(30, retrievedUser.getAge(),
                "Возраст должен соответствовать установленному значению");
        assertEquals(LocalDate.now(), retrievedUser.getCreatedAt(),
                "Время создания должно соответствовать установленному значению");
    }

    @Test
    @DisplayName("Успешно обновленный пользователь")
    void whenUpdateUserThenUserCanBeUpdated() {
        User updatedUser = new User(1L, "Иван", "k@mail.ru", 777, LocalDate.now());

        userDAO.add(newUser);
        userDAO.update(updatedUser);
        User retrievedUser = userDAO.get(1L);

        assertNotNull(retrievedUser, "Обновленный пользователь должен быть найден в БД");
        assertEquals(777, retrievedUser.getAge(), "Возраст должен измениться");
    }

    @Test
    @DisplayName("Должен вернуться null вместо user")
    void whenGetFakeUserByIdThenShouldReturnNull() {
        Long id = 99999999L;

        User user = userDAO.get(id);

        assertNull(user, "Пользователь должен отсутствовать в БД");
    }

    @Test
    @DisplayName("Успешно получены все пользователи")
    void whenGetAllUsersThenUsersCanBeRetrieved() {
        User newUserSecond = new User(null, "Петя", "р@mail.ru", 18, LocalDate.now());

        userDAO.add(newUser);
        userDAO.add(newUserSecond);
        List<User> users = userDAO.getAll();

        assertNotNull(users, "Пользователи должны быть найдены в БД");
        assertEquals(2, users.size(), "Должно быть найдено 2 пользователя");
    }

    @Test
    @DisplayName("Пользователь успешно удален")
    void whenGetDeleteUserThenUsersCantBeRetrieved() {
        User user = newUser;

        userDAO.add(user);

        assertFalse(userDAO.getAll().isEmpty(), "Пользователи должны быть найдены в БД");

        userDAO.delete(1L);

        assertTrue(userDAO.getAll().isEmpty(), "Пользователи не должны быть найдены в БД");
    }
}