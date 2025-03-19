package mission.g_collection;

import mission.g_collection.model.Book;
import mission.g_collection.service.BookManager;

public class Application {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.addBook(new Book("Clean code", "Robert C. Martin", 45.0));
        bookManager.addBook(new Book("Clean code", "Robert C. Martin", 45.0));
        bookManager.addBook(new Book("Clean code", "Robert C. Martin", 45.0));
        bookManager.addBook(new Book("Effective Java", "Joshua", 55.0));

        Boolean isRemoved = bookManager.removeBook("Clean code");
        String result = bookManager.listBooks();
        System.out.println("isRemoved: " + isRemoved);
        System.out.println(result);
    }
}
