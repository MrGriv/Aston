package home.task.module2.controller;

import home.task.module2.User;
import home.task.module2.dao.impl.UserDAOImpl;
import home.task.module2.service.UserService;
import home.task.module2.service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class UserController {
    /**
     * Сканер дя ввода данных
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Интерфейс UserService
     */
    private final UserService userService = new UserServiceImpl(new UserDAOImpl());

    /**
     * Выбор пункта меню
     */
    public void start() {
        while (true) {
            printMenu();

            String line = scanner.nextLine();
            switch (line) {
                case "1" -> add();
                case "2" -> update();
                case "3" -> get();
                case "4" -> getAll();
                case "5" -> delete();
                case "q" -> {
                    scanner.close();

                    return;
                }
                default -> System.out.println("Нет такой команды");
            }
        }
    }

    private void add() {
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
        System.out.println(userService.add(new User(null, name, email, age, LocalDate.now())));
    }

    private void update() {
        System.out.println("Обновление пользователя");
        Long id = checkId();
        User user = userService.get(id);
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

        System.out.println(userService.update(user));
    }

    private void get() {
        Long id = checkId();
        System.out.println(userService.get(id));
    }

    private void getAll() {
        System.out.println(userService.getAll());
    }

    private void delete() {
        Long id = checkId();
        userService.delete(id);
    }

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

    /**
     * Вывод меню на экран пользователя
     */
    private void printMenu() {
        System.out.println();
        System.out.println("1 - добавить User");
        System.out.println("2 - обновить User по id");
        System.out.println("3 - получить User по id");
        System.out.println("4 - получить всех User");
        System.out.println("5 - удалить User по id");
        System.out.println("q - выйти");
    }
}
