package home.task.module2.service.impl;

import home.task.module2.User;
import home.task.module2.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Иван", "ivan@test.com", 30, LocalDate.now());
    }

    @Test
    @DisplayName("Успешно отработанный метод добавления user")
    void whenAddUserShouldReturnCreatedUser() {
        User expectedUser = user;

        when(userDAO.add(any())).thenReturn(expectedUser);

        User result = userService.add(expectedUser);

        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getName(), result.getName());

        verify(userDAO, times(1)).add(any());
    }

    @Test
    @DisplayName("Успешно отработанный метод обновления user")
    void whenUpdateUserShouldReturnUpdatedUser() {
        User expectedUser = user;

        when(userDAO.update(any())).thenReturn(expectedUser);

        User result = userService.update(expectedUser);

        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getName(), result.getName());

        verify(userDAO, times(1)).update(any());
    }

    @Test
    @DisplayName("Успешно отработанный метод получения user по id")
    void whenGetUserByIdShouldReturnCorrectUser() {
        User expectedUser = user;

        when(userDAO.get(anyLong())).thenReturn(expectedUser);

        User result = userService.get(anyLong());

        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
        assertEquals(expectedUser.getName(), result.getName());

        verify(userDAO, times(1)).get(anyLong());
    }

    @Test
    @DisplayName("Успешно отработанный метод получения всех user")
    void whenGetAllUsersShouldReturnAllUsers() {
        List<User> expectedUsers = List.of(user);

        when(userDAO.getAll()).thenReturn(expectedUsers);

        List<User> result = userService.getAll();

        assertNotNull(result);
        assertEquals(expectedUsers.size(), result.size());
        assertEquals(expectedUsers.get(0).getId(), result.get(0).getId());

        verify(userDAO, times(1)).getAll();
    }

    @Test
    @DisplayName("Успешно отработанный метод удаления user")
    void whenDeleteUserShouldChangeListSize() {
        userService.delete(user.getId());

        verify(userDAO, times(1)).delete(anyLong());
    }

    @Test
    @DisplayName("Ошибка при создании пользователя")
    void whenAddUserShouldThrowRuntimeException() {
        when(userDAO.add(any())).thenThrow(new RuntimeException("Ошибка при создании пользователя"));

        assertThrows(RuntimeException.class, () -> userDAO.add(user));
    }

    @Test
    @DisplayName("Ошибка при обновлении пользователя")
    void whenUpdateUserShouldThrowRuntimeException() {
        when(userDAO.update(any())).thenThrow(new RuntimeException("Ошибка при обновлении пользователя"));

        assertThrows(RuntimeException.class, () -> userDAO.update(user));
    }

    @Test
    @DisplayName("Ошибка при получении пользователя по id")
    void whenGetUserByIdShouldThrowRuntimeException() {
        when(userDAO.get(anyLong())).thenThrow(new RuntimeException("Ошибка при получении пользователя по id"));

        assertThrows(RuntimeException.class, () -> userDAO.get(user.getId()));
    }

    @Test
    @DisplayName("Ошибка при получении всех пользователей")
    void whenGetAllUsersShouldThrowRuntimeException() {
        when(userDAO.getAll()).thenThrow(new RuntimeException("Ошибка при получении всех пользователей"));

        assertThrows(RuntimeException.class, () -> userDAO.getAll());
    }

    @Test
    @DisplayName("Ошибка при удалении пользователя по id")
    void whenDeleteUserByIdShouldThrowRuntimeException() {
        doThrow(new RuntimeException("Ошибка при удалении пользователя по id")).when(userDAO).delete(anyLong());

        assertThrows(RuntimeException.class, () -> userDAO.delete(user.getId()));
    }
}