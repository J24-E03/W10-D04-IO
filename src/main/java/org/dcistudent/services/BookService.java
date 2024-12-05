package org.dcistudent.services;

import org.dcistudent.entities.Book;
import org.dcistudent.managers.BookManager;

import java.util.Map;

public class BookService {
    private final BookManager manager;

    public BookService() {
        this.manager = new BookManager();
    }

    public Map<Integer, Book> getAll() {
        return this.manager.findAll();
    }

    public Book getByTitle(String title) {
        return this.manager.findByTitle(
                this.getAll(),
                title
        );
    }

    public void saveBook(Book book) {
        this.manager.persist(book);
    }
}
