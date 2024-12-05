package library;

import java.time.LocalDate;

public class Book {
    private String title;
    private int numberOfPages;
    private String author;
    private boolean isBestSeller;
    private boolean isAvailable;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Book(String title, String author, int numberOfPages, boolean isBestSeller, boolean isAvailable) {
       setTitle(title);
       setAuthor(author);
       setNumberOfPages(numberOfPages);
       setAvailable(isAvailable);
       setBestSeller(isBestSeller);
        this.dueDate = null;
        this.borrowDate = null;
        this.returnDate = null;
    }

    public Book(String title, String author, int numberOfPages, boolean isBestSeller, boolean isAvailable, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {
        setTitle(title);
        setAuthor(author);
        setNumberOfPages(numberOfPages);
        setAvailable(isAvailable);
        setBestSeller(isBestSeller);
        this.dueDate = dueDate;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isBlank()) {
            System.out.println("Blank Title. Please input a valid title.");
            this.title = "Unknown";
            return;
        }
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;

    }

    public void setNumberOfPages(int numberOfPages) {
        if (numberOfPages <= 0) {
            System.out.println("Invalid number of pages!");
            this.numberOfPages = 100;  // default value
            return;
        }
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author.isBlank()) {
            System.out.println("Author is balnk! Please input a valid author name!");
            this.author = "Unknown";
            return;
        }
        this.author = author;
    }

    public boolean isBestSeller() {
        return isBestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        if (borrowDate != null && borrowDate.isAfter(LocalDate.now())) {
            System.out.println("Invalid borrow date!");
            this.borrowDate = LocalDate.now();
            return;
        }
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        if (returnDate != null && returnDate.isAfter(LocalDate.now())){
            System.out.println("Invalid return date!");
            this.returnDate = LocalDate.now();
            return;
        }
        this.returnDate = returnDate;
    }

    public String serialize() {
        return title + "," + author + "," + numberOfPages + "," +
                isBestSeller + "," + isAvailable + "," + borrowDate + "," + dueDate + "," + returnDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", author='" + author + '\'' +
                ", isBestSeller=" + isBestSeller +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
