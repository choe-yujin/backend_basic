package mission.bookstore.model;

import mission.bookstore.service.Searchable;

public class Book implements Searchable {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[title=" + title +
                ", author=" + author +
                ", price=" + price +
                "]";
    }

    @Override
    public Boolean matches(String keyword) {
        Boolean result = false;
        if (title.contains(keyword)) return result = true;
        return result;
    }
}
