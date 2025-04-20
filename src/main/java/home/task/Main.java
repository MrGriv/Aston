package home.task;

import home.task.task2.CustomArrayList;
import home.task.task2.CustomHashSet;
import home.task.task2.student.Book;
import home.task.task2.student.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест работы CustomHashSet");
        CustomHashSet<Integer> customHashSet = new CustomHashSet<>();
        System.out.println(customHashSet.add(1));
        System.out.println(customHashSet.add(1));
        System.out.println(customHashSet.add(2));
        System.out.println(customHashSet.size());
        System.out.println(customHashSet.remove(1));
        System.out.println(customHashSet.size());
        System.out.println();

        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();

        System.out.println("Тест работы CustomArrayList");
        System.out.println(customArrayList.add(1));
        System.out.println(customArrayList.get(0));
        System.out.println(customArrayList.size());
        System.out.println(customArrayList.addAll(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        System.out.println(customArrayList.remove(11));
        for (int i = 0; i < customArrayList.size(); i++) {
            System.out.print(customArrayList.get(i) + " ");
        }
        System.out.println();
        System.out.println(customArrayList.size());
        System.out.println();

        List<Book> books1 = new ArrayList<>();
        books1.add(new Book("A", 10, 2000));
        books1.add(new Book("A", 10, 2000));
        books1.add(new Book("B", 7, 1999));
        books1.add(new Book("B", 7, 1999));
        books1.add(new Book("C", 5, 1977));

        List<Book> books2 = new ArrayList<>();
        books2.add(new Book("A", 10, 2000));
        books2.add(new Book("A", 10, 2000));
        books2.add(new Book("D", 55, 2012));
        books2.add(new Book("E", 88, 2007));
        books2.add(new Book("F", 93, 2005));

        List<Book> books3 = new ArrayList<>();
        books3.add(new Book("A", 10, 2000));
        books3.add(new Book("G", 11, 2011));
        books3.add(new Book("G", 11, 2011));
        books3.add(new Book("H", 100, 1997));
        books3.add(new Book("I", 666, 2006));

        List<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", books1));
        students.add(new Student("Kate", books2));
        students.add(new Student("Liza", books3));

        System.out.println("Тест работы stream");
        students.stream().peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Book::getPages))
                .distinct()
                .filter(book -> book.getYearOfPublication() > 2000)
                .limit(3)
                .map(Book::getYearOfPublication)
                .findFirst()
                .ifPresentOrElse(System.out::println, null);
    }
}
