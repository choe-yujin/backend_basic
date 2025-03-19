package mission.g_collection.service;

import mission.g_collection.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public String listBooks() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.toString()).append("; ");
        }
        return sb.toString();
    }

    public boolean removeBook(String title) {
        return books.removeIf(book -> book.getTitle().equals(title));
    }

}
