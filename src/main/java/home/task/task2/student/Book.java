package home.task.task2.student;

import java.util.Objects;

public class Book {
    private String name;
    private Integer pages;
    private Integer yearOfPublication;

    public Book(String name, Integer pages, Integer yearOfPublication) {
        this.name = name;
        this.pages = pages;
        this.yearOfPublication = yearOfPublication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Book book = (Book) object;
        return Objects.equals(name, book.name)  && Objects.equals(pages, book.pages) &&
                Objects.equals(yearOfPublication, book.yearOfPublication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pages, yearOfPublication);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                ", yearOfPublication=" + yearOfPublication +
                '}';
    }
}
