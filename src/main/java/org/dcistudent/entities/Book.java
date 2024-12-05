package org.dcistudent.entities;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Integer numberOfPages;
    private Boolean bestseller;
    private Boolean available;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate handBackDate;

    public Book(
            String title,
            String author,
            Integer numberOfPages,
            Boolean bestseller,
            Boolean isAvailable,
            LocalDate borrowedDate,
            LocalDate dueDate,
            LocalDate handBackDate
    ) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setNumberOfPages(numberOfPages);
        this.setBestseller(bestseller);
        this.setAvailable(isAvailable);
        this.setBorrowedDate(borrowedDate);
        this.setDueDate(dueDate);
        this.setHandBackDate(handBackDate);
    }

    @Override
    public String toString() {
        return String.format(
                "%s,%s,%d,%b,%b,%s,%s,%s",
                this.getTitle(),
                this.getAuthor(),
                this.getNumberOfPages(),
                this.getBestseller(),
                this.getAvailable(),
                this.getBorrowedDate(),
                this.getDueDate(),
                this.getHandBackDate())
        ;
    }
}
