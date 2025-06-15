package home.task.module2.controller;

import home.task.module2.service.UserService;
import home.task.module2.service.impl.UserServiceImpl;

import java.util.Scanner;

public class UserController {
    /**
     * Сканер дя ввода данных
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Интерфейс UserService
     */
    private final UserService userService = new UserServiceImpl();

    /**
     * Выбор пункта меню
     */
    public void start() {
        while (true) {
            printMenu();

            String line = scanner.nextLine();
            switch (line) {
                case "1" -> userService.add();
                case "2" -> userService.update();
                case "3" -> userService.get();
                case "4" -> userService.getAll();
                case "5" -> userService.delete();
                case "q" -> {
                    scanner.close();

                    return;
                }
                default -> System.out.println("Нет такой команды");
            }
        }
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
