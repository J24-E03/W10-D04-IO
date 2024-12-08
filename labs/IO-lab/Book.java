import java.time.LocalDate;
import java.time.LocalDateTime;

public class Book {

    private String title;
    private int numberOfPages;
    private String author;
    private boolean isBestSeller;
    private boolean isAvaialble;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Book(String title, int numberOfPages, String author, boolean isBestSeller, boolean isAvaialble) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.isBestSeller = isBestSeller;
        this.isAvaialble = isAvaialble;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;
    }
    public String savoToDatabase(){
        return this.title + "," + this.numberOfPages + "," + this.author + "," + this.isBestSeller + "," + this.isAvaialble + "," + this.borrowDate + "," + this.dueDate + "," + this.returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(this.title.isEmpty()){
            System.out.println("Title cannot be empty");
            this.title = "NoTitle";
            return;
        }
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages){
        if(numberOfPages <=0){
            System.out.println("Please correct number of pages");
            this.numberOfPages = 50;
            return;
        }
        this.numberOfPages = numberOfPages;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if(author.isEmpty()){
            System.out.println("Please give a proper name");
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

    public boolean isAvaialble() {
        return isAvaialble;
    }

    public void setAvaialble(boolean avaialble) {
        isAvaialble = avaialble;
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

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", author='" + author + '\'' +
                ", isBestSeller=" + isBestSeller +
                ", isAvaialble=" + isAvaialble +
                '}';
    }
}
