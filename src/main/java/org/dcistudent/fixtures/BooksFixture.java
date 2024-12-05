package org.dcistudent.fixtures;

import org.dcistudent.entities.Book;

import java.time.LocalDate;
import java.util.Map;

public final class BooksFixture {
    public static final Map<Integer, Book> BOOK_LIST;

    static {
        BOOK_LIST = Map.of(
                0,
                new Book(
                        "The Alchemist",
                        "Paulo Coelho",
                        208,
                        true,
                        true,
                        null,
                        LocalDate.of(2021, 10, 1),
                        null
                ),
                1,
                new Book(
                        "The Little Prince",
                        "Antoine de Saint-Exupéry",
                        96,
                        true,
                        true,
                        LocalDate.of(2021, 10, 2),
                        null,
                        null
                ),
                2,
                new Book(
                        "The Da Vinci Code",
                        "Dan Brown",
                        489,
                        true,
                        true,
                        null,
                        null,
                        LocalDate.of(2021, 10, 3)
                ),
                3,
                new Book(
                        "The Catcher in the Rye",
                        "J.D. Salinger",
                        277,
                        true,
                        true,
                        null,
                        null,
                        null
                ),
                4,
                new Book(
                        "The Great Gatsby",
                        "F. Scott Fitzgerald",
                        180,
                        true,
                        true,
                        null,
                        null,
                        null
                )
        );
    }

    private BooksFixture() {}
}
