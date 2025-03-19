package mission.h_collection.service;


import mission.h_collection.model.Book;
import mission.h_collection.model.LibraryMember;
import org.junit.jupiter.api.*;

public class LibraryServiceTests {
    private LibraryMember libraryMember;
    private RentalManager rentalManager;
    private String membershipId;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        libraryMember = new LibraryMember("Yujin", "LM001");
        rentalManager = new RentalManager();
        membershipId = libraryMember.getMembershipId();
        rentalManager.registerMember(membershipId);
        book1 = new Book("Clean code", "Robert C. Martin", 45.0);
        book2 = new Book("Effective Java", "Joshua", 55.0);
        membershipId = libraryMember.getMembershipId();
        rentalManager.rentBook(membershipId, book1);
        rentalManager.rentBook(membershipId, book2);
    }

    @Test
    @DisplayName("회원의 대여 목록에 도서를 추가")
    public void testRentBook() {
        boolean isAdded = rentalManager.rentBook(membershipId, book1);
        Assertions.assertTrue(isAdded);
    }

    @Test
    @DisplayName("특정 회원의 대여 도서 목록을 문자열로 반환")
    public void testRentalList() {
        String list = rentalManager.getRentalList(membershipId);
        String expected = "회원ID: LM001 -> 대여 도서: [Clean code]; [Effective Java]";
        Assertions.assertTrue(list.contains(expected));
    }

    @AfterEach
    public void tearDown() {
        libraryMember = null;
        rentalManager = null;
        membershipId = null;
        book1 = null;
        book2 = null;
    }
}
