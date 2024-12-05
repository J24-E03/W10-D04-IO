import java.time.LocalDate;

public class Book {
    private String title;
    private int numOfPages;
    private String author;
    private boolean isBestSeller;
    private boolean isAvailable;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Book(String title, int numOfPages, String author, boolean isBestSeller, boolean isAvailable) {
        this.title = title;
        this.numOfPages = numOfPages;
        this.author = author;
        this.isBestSeller = isBestSeller;
        this.isAvailable = isAvailable;
    }

    public String saveToDatabase() {
        return this.title + "," + this.numOfPages + "," + this.author + "," + this.isBestSeller + "," + this.isAvailable + "," + this.borrowDate + "," + this.dueDate + "," + this.returnDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numOfPages=" + numOfPages +
                ", author='" + author + '\'' +
                ", isBestSeller=" + isBestSeller +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
        this.returnDate = returnDate;
    }
}
