package org.dcistudent.managers;

import org.dcistudent.entities.Book;
import org.dcistudent.fixtures.BooksFixture;
import org.dcistudent.hydrators.EntityCsvHydrator;

import java.io.*;
import java.util.Map;

public class BookManager {
    private static final String FILE_PATH;

    static {
        FILE_PATH = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "books.csv";
    }

    public BookManager() {
        if (this.getReader().lines().count() == 0) {
            this.migrate();
        }
    }

    private void migrate() {
        this.persist(BooksFixture.BOOK_LIST);
    }

    public Map<Integer, Book> findAll() {
        return EntityCsvHydrator.hydrate(this.getReader());
    }

    public Book findByTitle(Map<Integer, Book> books, String title) {
        return books
                .values()
                .stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow()
        ;
    }

    public void persist(Map<Integer, Book> books) {
        BufferedWriter writer;

        try(FileWriter writerFile = new FileWriter(FILE_PATH)) {
            writer = new BufferedWriter(writerFile);
            writer.write(EntityCsvHydrator.hydrate(books));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persist(Book book) {
        Map<Integer, Book> books = this.findAll();
        books.forEach((k, v) -> {
            if (v.getTitle().equals(book.getTitle())) {
                books.put(k, book);
            }
        });
        this.persist(books);
    }

    private void createFile() {
        try {
            File file = new File(FILE_PATH);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader getReader() {
        try {
            return new BufferedReader(new FileReader(FILE_PATH));
        } catch (FileNotFoundException e) {
            this.createFile();
            return this.getReader();
        }
    }
}
