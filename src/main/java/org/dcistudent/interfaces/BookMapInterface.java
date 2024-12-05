package org.dcistudent.interfaces;

import org.dcistudent.entities.Book;

import java.util.Map;

public interface BookMapInterface extends Map {
    public void add(Book book);
}
