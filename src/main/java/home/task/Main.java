package home.task;

import home.task.module2.controller.UserController;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.start();
    }
}
