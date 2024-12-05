package org.dcistudent.maps;

import org.dcistudent.entities.Book;
import org.dcistudent.interfaces.BookMapInterface;

import java.util.HashMap;

public class BookMap extends HashMap implements BookMapInterface {
    private int id = 0;

    public BookMap() {
        super();
    }

    public void add(Book book) {
        this.put(id++, book);
    }
}
