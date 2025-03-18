package mission.bookstore;

import mission.bookstore.model.Book;
import org.junit.jupiter.api.*;

class LibrarySearchTest {
    private Book[] books;
    private LibrarySearch librarySearch;

    @BeforeEach
    void setUp() {
        books = new Book[] {
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 8.99),
                new Book("To Kill a Great Mockingbird", "Harper Lee", 8.95),
                new Book("Moby Dick", "Herman Melville", 8.94)
        };
        librarySearch = new LibrarySearch();
        System.out.println("테스트를 위한 Books와 LibrarySearch가 준비 되었습니다.");
    }

    @Test
    @DisplayName("Title에 keyword가 포함된 Book을 반환 - 매칭값 있음")
    void searchBooks() {
        String text1 = "[title=The Great Gatsby, author=F. Scott Fitzgerald, price=8.99]";
        String text2 = "[title=To Kill a Great Mockingbird, author=Harper Lee, price=8.95]";
        String keyword = "Great";
        String result = librarySearch.searchBooks(books, keyword);

        Assertions.assertTrue(result.contains(text1));
        Assertions.assertTrue(result.contains(text2));
    }

    @Test
    @DisplayName("Title에 keyword가 포함된 Book을 반환 - 매칭값 없음")
    void searchBooksWithNoMatch() {
        String keyword = "ABC";
        String expected = "";
        String result = librarySearch.searchBooks(books, keyword);

        Assertions.assertEquals(expected, result);
    }

    @AfterEach
    void tearDown() {
        this.books = null;
        this.librarySearch = null;
        System.out.println("테스트가 종료되어 Books와 LibrarySearch를 정리합니다.");
    }
}