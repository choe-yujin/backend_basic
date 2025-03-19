package mission.g_collection.model;

public class Book {
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

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        // "도서: [Title1], [Author1], [Price1]; 도서: [Title2], [Author2], [Price2]"
        return "도서: [" +
                title + "], [" +
                author + "], [" +
                price + "]";
    }
}
