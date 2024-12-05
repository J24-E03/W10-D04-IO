package org.dcistudent;

import org.dcistudent.entities.Book;
import org.dcistudent.services.BookService;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        BookService service = new BookService();
        Map<Integer, Book> books = service.getAll();
        books.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("=====================================");

        Book book = service.getByTitle("The Alchemist");
        System.out.println(book);
        System.out.println("=====================================");
        book.setAvailable(false);
        System.out.println(book);
        System.out.println("=====================================");
        service.saveBook(book);
        books = service.getAll();
        books.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}