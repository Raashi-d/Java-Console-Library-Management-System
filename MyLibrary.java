import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;


//class book

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public Book() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter book title: ");
        this.title = scanner.nextLine();

        System.out.print("Enter author name: ");
        this.author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        this.isbn = scanner.nextLine();

        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + available;
    }
}

//library class

class Library {
    private Map<String, List<Book>> bookCategories;

    public Library() {
        this.bookCategories = new HashMap<>();
        initializeCategories();
    }

    public Map<String, List<Book>> getBookCategories() {
        return bookCategories;
    }

    private void initializeCategories() {
        bookCategories.put("Adventure", new ArrayList<>());
        bookCategories.put("Classics", new ArrayList<>());
        bookCategories.put("Crime", new ArrayList<>());
        bookCategories.put("Fantasy", new ArrayList<>());
        bookCategories.put("Horror", new ArrayList<>());
    }

    public void addBook(Book book, String category) {
        List<Book> books = bookCategories.get(category);
        if (books != null) {
            books.add(book);
        } else {
			System.out.println();
            System.out.println("Invalid category!");
        }
    }

    public void displayAvailableBooksByCategory(String category) {
        System.out.println("\n" + category + " Books:");
        displayBooksByCategory(bookCategories.get(category));
    }

    private void displayBooksByCategory(List<Book> bookList) {
        for (Book book : bookList) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public Book borrowBook(String isbn, String category) {
        List<Book> books = bookCategories.get(category);
        if (books != null) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                    book.setAvailable(false);
                    return book;
                }
            }
        }
        return null;
    }

    public void returnBook(Book book) {
        book.setAvailable(true);
    }
}

class User {
    private static int userCount = 0;
    private int userId;
    private List<Book> borrowedBooks;

    public User() {
        this.userId = ++userCount;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

public class MyLibrary {
	
		
		private static List<String> actions = new ArrayList<>(); // Changed to a list of strings to store all actions
		private static boolean borrowWarningDisplayed = false; // Added a boolean flag for borrow warning
		private static boolean returnWarningDisplayed = false; // Added a boolean flag for return warning
		
		
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

		String libraryName = "*** WELCOME TO E-LIBRARY SERVICE OF SARASAVI LIBRARIES ***";
		String libraryAddress = "No.2, Yatinuwara Street, Kandy.";
		String libraryContact = "Tel: +94 777 111 111";
		
		//Enter Student ID ANd NAME
		
		System.out.println();
		System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
		
		
        //header
		
        printCentered(new String(new char[45]).replace("\0", "="), 150);
		printCentered(libraryName,150);
		printCentered(new String(new char[45]).replace("\0", "="), 150);
        printCentered(libraryAddress, 150);
        printCentered(libraryContact, 150);
        int length = (int) Math.round(libraryName.length() * 2.5);
        printCentered(new String(new char[length]).replace("\0", "-"), 150);
		
		
		LocalDate todayDate = LocalDate.now();
		LocalTime nowTime = LocalTime.now();
		

        System.out.printf("Date: %-120s Student Name: %s%n", todayDate, studentName);
        System.out.printf("Time: %-120s Student ID: %s%n", nowTime, studentID);
		
		printCentered(new String(new char[length]).replace("\0", "-"), 150);
		
		//Book List

        // Adding Adventure books
        library.addBook(new Book("The Adventures of Tom Sawyer", "Mark Twain", "003456AD"), "Adventure");
        library.addBook(new Book("The Call of the Wild", "Jack London", "005632AD"), "Adventure");
        library.addBook(new Book("Robinson Crusoe", "Daniel Defoe", "003456AD"), "Adventure");
        library.addBook(new Book("Treasure Island", "Robert Louis Stevenson", "001243AD"), "Adventure");

        // Adding Classics books
        library.addBook(new Book("Pride and Prejudice", "Jane Austen", "006833CL"), "Classics");
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "005654CL"), "Classics");
        library.addBook(new Book("Jane Eyre", "Charlotte Bronte", "005673CL"), "Classics");
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "001453CL"), "Classics");

        // Adding Crime books
        library.addBook(new Book("The Silence of the Lambs", "Thomas Harris", "002950CR"), "Crime");
        library.addBook(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", "001493CR"), "Crime");
        library.addBook(new Book("The Adventures of Sherlock Holmes", "Arthur Conan Doyle", "007642CR"), "Crime");
        library.addBook(new Book("Gone Girl", "Gillian Flynn", "008643CR"), "Crime");

        // Adding Fantasy books
        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", "002345FN"), "Fantasy");
        library.addBook(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "006784FN"), "Fantasy");
        library.addBook(new Book("The Chronicles of Narnia", "C.S. Lewis", "002497FN"), "Fantasy");
        library.addBook(new Book("A Game of Thrones", "George R.R. Martin", "002356FN"), "Fantasy");

        // Adding Horror books
        library.addBook(new Book("Dracula", "Bram Stoker", "004834HR"), "Horror");
        library.addBook(new Book("Frankenstein", "Mary Shelley", "001498HR"), "Horror");
        library.addBook(new Book("The Shining", "Stephen King", "004567HR"), "Horror");
        library.addBook(new Book("The Haunting of Hill House", "Shirley Jackson", "001256HR"), "Horror");

        User user = new User();

        while (true) {
            System.out.println("\n1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Add a New Book");
            System.out.println("5. Exit");
			System.out.println();
		
			//get user input for main selection
			
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAvailableBooksByCategory(library);
                    break;
                case 2:
                    borrowBook(scanner, library, user);
                    break;
                case 3:
                    returnBook(scanner, library, user);
                    break;
                case 4:
                    addNewBook(scanner, library);
                    break;
                case 5:
				
					//EXIT ACTION METHOD CALLING
					
					if (!actions.isEmpty()) {
                        System.out.println("Actions performed:");
                        for (String action : actions) {
                            System.out.println(action);
                        }
                    }
					
					// Display warnings if they have been displayed
					
                    if (borrowWarningDisplayed) {
                        System.out.println("Keep the book safe and return it on time.");
                    }
					
					if (returnWarningDisplayed) {
                        System.out.println("Thanks for keeping the book carefully and returning it.");
                    }
					
					
					//FOOTER DISPLAY
					
					printCentered(new String(new char[length]).replace("\0", "-"), 150);
					printCentered("Exiting the program.",150);
					printCentered("Thank you!",150);
					printCentered("***Come Again***",150);
                    printCentered(new String(new char[length]).replace("\0", "-"), 150);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayAvailableBooksByCategory(Library library) {
        System.out.println("\nAvailable Books by Category:");

        for (String category : library.getBookCategories().keySet()) {
            library.displayAvailableBooksByCategory(category);
        }
    }
	
	//BORROWING A BOOK
	
    private static void borrowBook(Scanner scanner, Library library, User user) {
		
		System.out.println();
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbnToBorrow = scanner.nextLine();
        System.out.print("Enter category of the book (Adventure/Classics/Crime/Fantasy/Horror): ");
        String categoryToBorrow = scanner.nextLine();
      
        Book borrowedBook = library.borrowBook(isbnToBorrow, categoryToBorrow);

        if (borrowedBook != null) {
            user.borrowBook(borrowedBook);
			System.out.println();
            System.out.println("Book borrowed successfully.");
			
			
			//Exit massege when borrow a book
			String borrowAction = "Borrowed book: " +
                                "Title: " + borrowedBook.getTitle() +
                                ", Author: " + borrowedBook.getAuthor() +
                                ", ISBN: " + borrowedBook.getIsbn();
            actions.add(borrowAction);
			
			// Set borrow warning only if it has not been displayed yet
                    if (!borrowWarningDisplayed) {
                        borrowWarningDisplayed = true;
                        System.out.println("Keep the book safe and return it on time.");
                    }
			
			
			
        } else {
			System.out.println();
            System.out.println("Book not available or not found.");
        }
    }

	//Returning a Book
	
    private static void returnBook(Scanner scanner, Library library, User user) {
		
		System.out.println();
        System.out.print("Enter ISBN of the book to return: ");
        String isbnToReturn = scanner.nextLine();
        System.out.print("Enter category of the book(Adventure/Classics/Crime/Fantasy/Horror): ");
        String categoryToReturn = scanner.nextLine();

        Book bookToReturn = null;

        for (Book borrowed : user.getBorrowedBooks()) {
            if (borrowed.getIsbn().equals(isbnToReturn)) {
                bookToReturn = borrowed;
                break;
            }
        }

        if (bookToReturn != null) {
            library.returnBook(bookToReturn);
            user.returnBook(bookToReturn);
			System.out.println();
            System.out.println("Book returned successfully.");
			
			////Exit massege when return a book
			
			String returnAction = "Book returned successfully: " +
                                "Title: " + bookToReturn.getTitle() +
                                ", Author: " + bookToReturn.getAuthor() +
                                ", ISBN: " + bookToReturn.getIsbn();
            actions.add(returnAction);
			
			// Set return warning only if it has not been displayed yet
			
                        if (!returnWarningDisplayed) {
                            returnWarningDisplayed = true;
                            System.out.println("Thanks for keeping the book carefully and returning it.");
                        }
						
        } else {
			System.out.println();
            System.out.println("Book not found in your borrowed list.");
        }
    }
	
	//Adding new book
	
    private static void addNewBook(Scanner scanner, Library library) {
    System.out.print("Enter category of the new book (Adventure/Classics/Crime/Fantasy/Horror): ");
    String categoryToAdd = scanner.nextLine().trim();

    // Validate the category
	
    if (!isValidCategory(categoryToAdd)) {
        System.out.println("Invalid category. Please enter a valid category.");
        return;
    }

    Book newBook = new Book();
    library.addBook(newBook, categoryToAdd);
    System.out.println("New book added to the library.");
	
	////Exit massege when Add a new book
	
		String addAction = "New book added to the library: " +
                            "Title: " + newBook.getTitle() +
                            ", Author: " + newBook.getAuthor() +
                            ", ISBN: " + newBook.getIsbn();
        actions.add(addAction);
		
	
    }

    private static boolean isValidCategory(String category) {
        // Check if the entered category is one of the specified categories
        return category.equalsIgnoreCase("Adventure") ||
            category.equalsIgnoreCase("Classics") ||
            category.equalsIgnoreCase("Crime") ||
            category.equalsIgnoreCase("Fantasy") ||
            category.equalsIgnoreCase("Horror");
    }
	
	//center text alignment
	
	public static void printCentered(String s, int width) {
        int padSize = width - s.length();
        int padStart = s.length() + padSize / 2;
        s = String.format("%" + padStart + "s", s);
        s = String.format("%-" + width + "s", s);
        System.out.println(s);
    }
	
	
	//lines print
	
    private static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

}

