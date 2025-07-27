package home.task.module2.service.impl;

import home.task.module2.dto.user.UserNew;
import home.task.module2.dto.user.UserUpdate;
import home.task.module2.mapper.UserMapper;
import home.task.module2.model.User;
import home.task.module2.repository.UserRepository;
import home.task.module2.service.MessageKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private MessageKafkaProducer messageKafkaProducer;

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

        when(userRepository.save(any())).thenReturn(expectedUser);
        when(userMapper.ofUserNew(any())).thenReturn(expectedUser);
        userService.add(new UserNew("Иван", "ivan@test.com", 30));

        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Успешно отработанный метод обновления user")
    void whenUpdateUserShouldReturnUpdatedUser() {
        User expectedUser = user;

        when(userRepository.save(any())).thenReturn(expectedUser);
        when(userRepository.findByIdForUpdate(anyLong())).thenReturn(Optional.of(expectedUser));
        userService.update(expectedUser.getId(), new UserUpdate("Иван", "ivan@test.com", 30));

        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Успешно отработанный метод получения user по id")
    void whenGetUserByIdShouldReturnCorrectUser() {
        User expectedUser = user;

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expectedUser));
        userService.get(anyLong());

        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Успешно отработанный метод получения всех user")
    void whenGetAllUsersShouldReturnAllUsers() {
        List<User> expectedUsers = List.of(user);

        when(userRepository.findAll()).thenReturn(expectedUsers);
        userService.getAll();

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Успешно отработанный метод удаления user")
    void whenDeleteUserShouldChangeListSize() {
        User expectedUser = user;

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expectedUser));
        userService.delete(user.getId());

        verify(userRepository, times(1)).deleteById(anyLong());
    }
}