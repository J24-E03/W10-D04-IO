package library;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private final static Path booksFilePath = Path.of("library_books.txt");
    private static Library library;

    public static void main(String[] args) {
        initializeBooks();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = false;
        do {
            System.out.println("Welcome to our Library Management System!");
            System.out.println("If you and working with system as a user enter 1, if an admin enter 2: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        displayMenuToUser(scanner);
                        isRunning = false;
                        break;
                    case 2:
                        displayMenuToAdmin(scanner);
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please choose 1 or 2!");
                        isRunning = true;
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid choice! Please choose 1 or 2!");
                isRunning = true;
            }
        } while (isRunning);

        scanner.close();
        library.saveBooksToFile();

    }

    private static void initializeBooks() {
        library = new Library(booksFilePath);
        library.loadLibrary();
    }

    private static void displayMenuToAdmin(Scanner scanner) {
        boolean isRunning = true;
        System.out.println("\nWelcome Admin:");
        while (isRunning) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Check for Overdue Books");
            System.out.println("2. Filter Books by Borrow Date Range");
            System.out.println("3. Notify About Due Books");
            System.out.println("4. Extend Due Date of a Book");
            System.out.println("5. Exit");
            System.out.print("Please Enter Your Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        library.checkOverdueBooks();
                        break;
                    case 2:
                        System.out.println("Please Enter a Start Date in this format: (yyyy-MM-dd): ");
                        LocalDate startDate, endDate;
                        String startDateString = scanner.nextLine();
                        try {
                            startDate = LocalDate.parse(startDateString);
                        } catch (Exception ex) {
                            System.out.println("Wrong date format! Please try again later!");
                            break;
                        }
                        System.out.println("Please Enter an End Date in this format: (yyyy-MM-dd): ");
                        String endDateString = scanner.nextLine();
                        try {
                            endDate = LocalDate.parse(endDateString);
                        } catch (Exception ex) {
                            System.out.println("Wrong date format! Please try again later!");
                            break;
                        }
                        library.filterBooksByBorrowDate(startDate, endDate);
                        break;
                    case 3:
                        library.notifyDueBooks();
                        break;

                    case 4:
                        System.out.println("Which book do you want to extend the due date? ");
                        String title = scanner.nextLine();
                        int additionalDays;
                        System.out.println("How many days do you want it to be extended? ");
                        try{
                            additionalDays = scanner.nextInt();
                            scanner.nextLine();
                        }
                        catch (Exception ex){
                            System.out.println("Invalid input for days. Please try again later!");
                            scanner.nextLine();
                            break;
                        }
                        library.extendDueDate(title, additionalDays);
                        break;

                    case 5:
                        isRunning = false;
                        System.out.println("Thank you and Goodbye!");
                        break;
                    default:
                        System.out.println("Please enter a valid choice (1–5).");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.nextLine();
            }

        }
        scanner.close();
    }

    private static void displayMenuToUser(Scanner scanner) {
        boolean isRunning = true;
        System.out.println("\nWelcome User:");
        while (isRunning) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. View All Books");
            System.out.println("2. View Available Books");
            System.out.println("3. View Bestseller Books");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return a Book");
            System.out.println("6. Search Books by Author");
            System.out.println("7. Exit");
            System.out.print("Please Enter Your Choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        library.viewAllBooks();
                        break;
                    case 2:
                        library.viewAvailableBooks();
                        break;
                    case 3:
                        library.viewBestSellerBooks();
                        break;

                    case 4:
                        System.out.print("Enter the title of the book you want to borrow: ");
                        library.borrowBookByTitle(scanner.nextLine().trim());
                        break;

                    case 5:
                        System.out.print("Enter the title of the book you want to return: ");
                        library.returnBook(scanner.nextLine().trim());
                        break;
                    case 6:
                        System.out.print("Enter the author's name: ");
                        library.searchBookByAuthor(scanner.nextLine().trim());
                        break;
                    case 7:
                        isRunning = false;
                        System.out.println("Thank you and Goodbye!");
                        break;
                    default:
                        System.out.println("Please enter a valid choice (1–7).");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                scanner.nextLine();
            }

        }
    }


}