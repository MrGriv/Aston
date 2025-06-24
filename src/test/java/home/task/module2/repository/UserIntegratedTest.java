package home.task.module2.repository;

import home.task.module2.dto.UserDto;
import home.task.module2.dto.UserNew;
import home.task.module2.dto.UserUpdate;
import home.task.module2.exception.NotFoundException;
import home.task.module2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@Testcontainers
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserIntegratedTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");

    @Autowired
    private UserService userService;

    private UserNew newUser;

    @BeforeEach
    void setUp() {
        newUser = new UserNew("Иван", "k@mail.ru", 30);
    }

    @Test
    @DisplayName("Успешно найденный пользователь")
    void whenAddUserThenUserCanBeRetrieved() {
        UserNew user = newUser;

        UserDto addedUser = userService.add(user);
        UserDto retrievedUser = userService.get(addedUser.id());

        assertNotNull(retrievedUser, "Добавленный пользователь должен быть найден в БД");
        assertEquals("Иван", retrievedUser.name(),
                "Имя должно соответствовать установленному значению");
        assertEquals("k@mail.ru", retrievedUser.email(),
                "Почта должна соответствовать установленному значению");
        assertEquals(30, retrievedUser.age(),
                "Возраст должен соответствовать установленному значению");
        assertEquals(LocalDate.now(), retrievedUser.createdAt(),
                "Время создания должно соответствовать установленному значению");
    }

    @Test
    @DisplayName("Успешно обновленный пользователь")
    void whenUpdateUserThenUserCanBeUpdated() {
        UserUpdate updatedUser = new UserUpdate("Иван", "k@mail.ru", 777);

        UserDto addedUser = userService.add(newUser);
        userService.update(addedUser.id(), updatedUser);
        UserDto retrievedUser = userService.get(addedUser.id());

        assertNotNull(retrievedUser, "Обновленный пользователь должен быть найден в БД");
        assertEquals(777, retrievedUser.age(), "Возраст должен измениться");
    }

    @Test
    @DisplayName("Успешно получены все пользователи")
    void whenGetAllUsersThenUsersCanBeRetrieved() {
        UserNew newUserSecond = new UserNew("Петя", "р@mail.ru", 18);

        userService.add(newUser);
        userService.add(newUserSecond);
        List<UserDto> users = userService.getAll();

        assertNotNull(users, "Пользователи должны быть найдены в БД");
        assertEquals(2, users.size(), "Должно быть найдено 2 пользователя");
    }

    @Test
    @DisplayName("Пользователь успешно удален")
    void whenGetDeleteUserThenUsersCantBeRetrieved() {
        UserNew user = newUser;

        UserDto addedUser = userService.add(user);

        assertFalse(userService.getAll().isEmpty(), "Пользователи должны быть найдены в БД");

        userService.delete(addedUser.id());

        assertTrue(userService.getAll().isEmpty(), "Пользователи не должны быть найдены в БД");
    }

    @Test
    @DisplayName("Должно быть выброшено NotFoundException для несуществующего пользователя")
    void whenUpdateFakeUserByIdThenShouldThrowNotFoundException() {
        Long id = 99999999L;

        assertThrows(NotFoundException.class, () -> userService.update(id, new UserUpdate(null, null, null)),
                "Должно быть выброшено NotFoundException для несуществующего пользователя");
    }

    @Test
    @DisplayName("Должно быть выброшено NotFoundException для несуществующего пользователя")
    void whenDeleteFakeUserByIdThenShouldThrowNotFoundException() {
        Long id = 99999999L;

        assertThrows(NotFoundException.class, () -> userService.delete(id),
                "Должно быть выброшено NotFoundException для несуществующего пользователя");
    }

    @Test
    @DisplayName("Должно быть выброшено NotFoundException для несуществующего пользователя")
    void whenGetFakeUserByIdThenShouldThrowNotFoundException() {
        Long id = 99999999L;

        assertThrows(NotFoundException.class, () -> userService.get(id),
                "Должно быть выброшено NotFoundException для несуществующего пользователя");
    }
}