package home.task.module2.service.impl;

import home.task.module2.dto.UserDto;
import home.task.module2.dto.UserNew;
import home.task.module2.dto.UserUpdate;
import home.task.module2.exception.NotFoundException;
import home.task.module2.mapper.UserMapper;
import home.task.module2.model.User;
import home.task.module2.repository.UserRepository;
import home.task.module2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto add(UserNew userNew) {
        User user = userMapper.ofUserNew(userNew);
        user.setCreatedAt(LocalDate.now());
        log.info("User with name = " + user.getName() + ", email = " +
                user.getEmail() + ", age = " + user.getAge() + " created");

        return userMapper.ofEntity(userRepository.save(user));
    }

    @Override
    public UserDto update(Long userId, UserUpdate userUpdate) {
        User user = findUserForUpdate(userId);

        userMapper.userOfUpdateUser(user, userUpdate);
        User updatedUser = userRepository.save(user);
        log.info("User with ID = " + updatedUser.getId() + " updated.");

        return userMapper.ofEntity(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto get(Long id) {
        User user = findUserById(id);
        log.info("User with ID = " + id + " returned.");

        return userMapper.ofEntity(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        log.info("All users returned.");

        return userMapper.ofListEntities(userRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        findUserById(id);
        log.info("User with ID = " + id + " deleted.");
        userRepository.deleteById(id);
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User с ID = %s не найден", id)));
    }

    private User findUserForUpdate(Long id) {
        return userRepository.findByIdForUpdate(id)
                .orElseThrow(() -> new NotFoundException(String.format("User с ID = %s не найден для обновления", id)));
    }
}
