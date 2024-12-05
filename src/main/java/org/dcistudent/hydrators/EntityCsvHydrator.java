package org.dcistudent.hydrators;

import org.dcistudent.maps.BookMap;
import org.dcistudent.entities.Book;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.Map;

public final class EntityCsvHydrator {
    private EntityCsvHydrator() {}

    public static String hydrate(Map<Integer, Book> books) {
        return books
                .values()
                .stream()
                .map(Book::toString)
                .reduce((a, b) -> a + "\n" + b)
                .orElseThrow()
        ;
    }

    public static Map<Integer, Book> hydrate(BufferedReader reader) {
        BookMap books = new BookMap();

        reader.lines().forEach(line -> {
            String[] fields = line.split(",");
            books.add(
                    new Book(
                            fields[0],
                            fields[1],
                            Integer.parseInt(fields[2]),
                            Boolean.parseBoolean(fields[3]),
                            Boolean.parseBoolean(fields[4]),
                            nullOrLocalDate(fields[5]),
                            nullOrLocalDate(fields[6]),
                            nullOrLocalDate(fields[7])
                    )
            );
        });

        return books;
    }

    public static LocalDate nullOrLocalDate(String string) {
        return string.equals("null") ? null : LocalDate.parse(string);
    }
}
