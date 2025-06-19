package home.task.module2.service.impl;

import home.task.module2.User;
import home.task.module2.dao.UserDAO;
import home.task.module2.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public User add(User user) {
        log.info("User with name = " + user.getName() + ", email = " +
                user.getEmail() + ", age = " + user.getAge() + " created");

        return userDAO.add(user);
    }

    @Override
    public User update(User user) {
        log.info("User with ID = " + user.getId() + " updated.");

        return userDAO.update(user);
    }

    @Override
    public User get(Long id) {
        User user = userDAO.get(id);
        log.info("User with ID = " + id + " returned.");

        return user;
    }

    @Override
    public List<User> getAll() {
        log.info("All users returned.");

        return userDAO.getAll();
    }

    @Override
    public void delete(Long id) {
        log.info("User with ID = " + id + " deleted.");
        userDAO.delete(id);
    }
}
