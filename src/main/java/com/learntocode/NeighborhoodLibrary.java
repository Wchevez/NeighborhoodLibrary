package com.learntocode;

import java.util.ArrayList;
import java.util.Scanner;
public class NeighborhoodLibrary {
    private ArrayList<Book> inventory;

    public NeighborhoodLibrary() {
        inventory = new ArrayList<>();
        inventory.add(new Book(1, "1234567890", "The Catcher in the Rye"));
        inventory.add(new Book(2, "0987654321", "To Kill a Mockingbird"));
        inventory.add(new Book(3, "1111111111", "1984"));
        inventory.add(new Book(4, "2222222222", "Animal Farm"));
        inventory.add(new Book(5, "3333333333", "The Great Gatsby"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Welcome to the Neighborhood Library!");
            System.out.println("Select an option:");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    showAvailableBooks(scanner);
                    break;
                case "2":
                    showCheckedOutBooks(scanner);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private void showAvailableBooks(Scanner scanner) {
        System.out.println("Available Books:");
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getId() + ". " + book.getIsbn() + " - " + book.getTitle());
            }
        }
        System.out.println("Enter the ID of the book you want to check out, or enter 'X' to go back to the home screen:");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("X")) {
            return;
        }
        try {
            int bookId = Integer.parseInt(input);
            Book book = getBookById(bookId);
            if (book == null) {
                System.out.println("Invalid book ID");
                return;
            }
            System.out.println("Enter your name:");
            String name = scanner.nextLine();
            book.checkOut(name);
            System.out.println(book.getTitle() + " has been checked out to " + name);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    private void showCheckedOutBooks(Scanner scanner) {
        System.out.println("Checked Out Books:");
        for (Book book : inventory) {
            if (book.isCheckedOut()) {
                System.out.println(book.getId() + ". " + book.getIsbn() + " - " + book.getTitle() + " (checked out to " + book.getCheckedOutTo() + ")");
            }
        }
        System.out.println("Enter 'C' to check in a book, or enter 'X' to go back to the home screen:");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("X")) {
            return;
        }
        if (input.equalsIgnoreCase("C")) {
            System.out.println("Enter the ID of the book you want to check in:");
            input = scanner.nextLine();
            try {
                int bookId = Integer.parseInt(input);
                Book book = getBookById(bookId);
                if (book == null) {
                    System.out.println("Invalid book ID");
                    return;
                }
                book.checkIn();
                System.out.println(book.getTitle() + " has been checked in");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    private Book getBookById(int id) {
        for (Book book : inventory) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        NeighborhoodLibrary library = new NeighborhoodLibrary();
        library.run();
    }
}
