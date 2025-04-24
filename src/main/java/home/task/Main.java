package home.task;

import home.task.task3.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/home/task/task3/resources/file.txt");
        FileManager manager = new FileManager(file);
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        System.out.println("После ввода данных введите -sr для сохранения в файл с перезаписью данных");
        System.out.println("После ввода данных введите -s для сохранения в файл с добавлением данных");
        System.out.println("Чтобы прочитать введите -r");
        System.out.println("Чтобы выйти введите -q");

        while (true) {
            String line = scanner.nextLine();

            switch (line) {
                case "-q" -> {
                    scanner.close();
                    return;
                }
                case "-r" -> manager.read();
                case "-s" -> {
                    manager.save(lines, true);
                    scanner.close();
                    return;
                }
                case "-sr" -> {
                    manager.save(lines, false);
                    scanner.close();
                    return;
                }
                default -> lines.add(line);
            }
        }

    }
}
