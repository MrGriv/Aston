package home.task.module2.service.impl;

import home.task.module2.User;
import home.task.module2.dao.UserDAO;
import home.task.module2.dao.impl.UserDAOImpl;
import home.task.module2.service.UserService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    /**
     * Сканер дя ввода данных
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Интерфейс UserDao
     */
    private final UserDAO userDAO = new UserDAOImpl();
    /**
     * Инструмент для логирования
     */
    private final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    /**
     * Метод для ввода данных и последующего добавления пользователя
     */
    @Override
    public void add() {
        System.out.println("Добавление нового пользователя");

        System.out.println("Введите имя:");
        String name;
        while (true) {
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Имя не может быть пустым");
                continue;
            }
            break;
        }

        System.out.println("Введите почту:");
        String email;
        while (true) {
            email = scanner.nextLine();
            if (!email.matches("\\S+@\\S+\\.\\S+")) {
                System.out.println("Почта не может быть пустой и должна соответствовать формату email@mail.com" +
                        ". Введите снова");
                continue;
            }
            break;
        }

        System.out.println("Введите возраст:");
        String newAge;
        Integer age = null;
        while (true) {
            newAge = scanner.nextLine();
            if (!newAge.isEmpty()) {
                if (!newAge.matches("^\\d+$")) {
                    System.out.println("Возраст может быть только целым числом. Введите снова");
                    continue;
                }
                age = Integer.parseInt(newAge);
            }
            break;
        }

        log.info("User with name = " + name + ", email = " + email + ", age = " + age + " created");
        userDAO.add(User.builder().age(age).name(name).email(email).createdAt(LocalDate.now()).build());
    }

    /**
     * Метод для ввода данных и последующего обновления пользователя
     */
    @Override
    public void update() {
        System.out.println("Обновление пользователя");
        Long id = checkId();
        User user = userDAO.get(id);
        if (user == null) {
            System.out.println("Нет такого пользователя");

            return;
        }

        System.out.println("Введите имя:");
        String name = scanner.nextLine();

        if (!name.isEmpty()) {
            user.setName(name);
        }

        System.out.println("Введите почту:");
        String email;
        while (true) {
            email = scanner.nextLine();
            if (email.isEmpty()) break;
            if (!email.matches("\\S+@\\S+\\.\\S+")) {
                System.out.println("Почта должна соответствовать формату email@mail.com" +
                        ". Введите снова");
                continue;
            }
            break;
        }

        if (!email.isEmpty()) {
            user.setEmail(email);
        }

        System.out.println("Введите возраст:");
        String newAge;
        Integer age;
        while (true) {
            newAge = scanner.nextLine();
            if (!newAge.isEmpty()) {
                if (!newAge.matches("^\\d+$")) {
                    System.out.println("Возраст может быть только целым положительным числом. Введите снова");
                    continue;
                }
            }
            break;
        }

        if (!newAge.isEmpty()) {
            age = Integer.parseInt(newAge);
            user.setAge(age);
        }

        log.info("User with ID = " + user.getId() + " updated.");
        userDAO.update(user);
    }

    /**
     * Метод для получения пользователя по id
     */
    @Override
    public void get() {
        Long id = checkId();

        log.info("User with ID = " + id + " returned.");
        userDAO.get(id);
    }

    /**
     * Метод для получения всех пользователей
     */
    @Override
    public void getAll() {
        log.info("All users returned.");
        userDAO.getAll();
    }

    /**
     * Метод для удаления пользователя по id
     */
    @Override
    public void delete() {
        Long id = checkId();

        log.info("User with ID = " + id + " deleted.");
        userDAO.delete(id);
    }

    /**
     * Метод для проверки ввода id
     */
    private Long checkId() {
        System.out.println("Введите id пользователя");

        String id;
        while (true) {
            id = scanner.nextLine();
            if (!id.matches("^\\d+$")) {
                System.out.println("id может быть только целым числом. Введите снова");
                continue;
            }
            break;
        }

        return Long.parseLong(id);
    }
}
