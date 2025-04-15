package mission.i_exception.util;

import mission.i_exception.model.Book;
import mission.i_exception.model.Member;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileStorageManagerTest {

    private static final String TEST_BOOKS_FILE = "test_books.txt";
    private static final String TEST_MEMBERS_FILE = "test_members.txt";
    private List<Book> testBooks;
    private List<Member> testMembers;

    @BeforeEach
    void setUp() throws IOException {
        // 테스트 전에 기존 파일 제거
        Files.deleteIfExists(Paths.get(TEST_BOOKS_FILE));
        Files.deleteIfExists(Paths.get(TEST_MEMBERS_FILE));

        // 테스트용 도서 및 회원 데이터 생성
        testBooks = new ArrayList<>();
        testBooks.add(new Book("Test Book 1", "Author 1", 10.0));
        testBooks.add(new Book("Test Book 2", "Author 2", 20.0));

        testMembers = new ArrayList<>();
        testMembers.add(new Member("Test Member 1", "TM001"));
        testMembers.add(new Member("Test Member 2", "TM002"));

        // 파일에 저장
        FileStorageManager.saveBooksToFile(testBooks, TEST_BOOKS_FILE);
        FileStorageManager.saveMembersToFile(testMembers, TEST_MEMBERS_FILE);
    }

    @Test
    void testSaveBooksToFile() {
        // 파일이 생성되었는지 확인
        Assertions.assertTrue(Files.exists(Paths.get(TEST_BOOKS_FILE)));

        // 저장된 도서 읽기
        List<Book> loadedBooks = FileStorageManager.readBooksFromFile(TEST_BOOKS_FILE);

        // 도서 수 확인
        Assertions.assertEquals(testBooks.size(), loadedBooks.size());

        // 도서 내용 확인
        Assertions.assertEquals(testBooks.get(0).getTitle(), loadedBooks.get(0).getTitle());
        Assertions.assertEquals(testBooks.get(0).getAuthor(), loadedBooks.get(0).getAuthor());
        Assertions.assertEquals(testBooks.get(0).getPrice(), loadedBooks.get(0).getPrice());

        Assertions.assertEquals(testBooks.get(1).getTitle(), loadedBooks.get(1).getTitle());
        Assertions.assertEquals(testBooks.get(1).getAuthor(), loadedBooks.get(1).getAuthor());
        Assertions.assertEquals(testBooks.get(1).getPrice(), loadedBooks.get(1).getPrice());
    }

    @Test
    void testSaveMembersToFile() {
        // 파일이 생성되었는지 확인
        Assertions.assertTrue(Files.exists(Paths.get(TEST_MEMBERS_FILE)));

        // 저장된 회원 읽기
        List<Member> loadedMembers = FileStorageManager.readMembersFromFile(TEST_MEMBERS_FILE);

        // 회원 수 확인
        Assertions.assertEquals(testMembers.size(), loadedMembers.size());

        // 회원 내용 확인
        Assertions.assertEquals(testMembers.get(0).getName(), loadedMembers.get(0).getName());
        Assertions.assertEquals(testMembers.get(0).getMembershipId(), loadedMembers.get(0).getMembershipId());

        Assertions.assertEquals(testMembers.get(1).getName(), loadedMembers.get(1).getName());
        Assertions.assertEquals(testMembers.get(1).getMembershipId(), loadedMembers.get(1).getMembershipId());
    }

    @Test
    void testAddMemberToFile() {
        // 새 회원 추가
        Member newMember = new Member("Test Member 3", "TM003");
        FileStorageManager.addMemberToFile(newMember, TEST_MEMBERS_FILE);

        // 저장된 회원 읽기
        List<Member> loadedMembers = FileStorageManager.readMembersFromFile(TEST_MEMBERS_FILE);

        // 회원 수 확인 (원래 2명 + 추가 1명 = 3명)
        Assertions.assertEquals(3, loadedMembers.size());

        // 추가된 회원 확인
        Assertions.assertEquals(newMember.getName(), loadedMembers.get(2).getName());
        Assertions.assertEquals(newMember.getMembershipId(), loadedMembers.get(2).getMembershipId());
    }

    @Test
    void testMemberExists() {
        // 존재하는 회원 ID 확인
        Assertions.assertTrue(FileStorageManager.memberExists("TM001", TEST_MEMBERS_FILE));
        Assertions.assertTrue(FileStorageManager.memberExists("TM002", TEST_MEMBERS_FILE));

        // 존재하지 않는 회원 ID 확인
        Assertions.assertFalse(FileStorageManager.memberExists("TM999", TEST_MEMBERS_FILE));
    }

    @Test
    void testBookExists() {
        // 존재하는 책 제목 확인
        Assertions.assertTrue(FileStorageManager.bookExists("Test Book 1", TEST_BOOKS_FILE));
        Assertions.assertTrue(FileStorageManager.bookExists("Test Book 2", TEST_BOOKS_FILE));

        // 존재하지 않는 책 제목 확인
        Assertions.assertFalse(FileStorageManager.bookExists("Non-existent Book", TEST_BOOKS_FILE));
    }

    @Test
    void testReadBooksFromNonExistentFile() {
        // 존재하지 않는 파일에서 읽기 시도
        List<Book> books = FileStorageManager.readBooksFromFile("non_existent_file.txt");

        // 빈 리스트 반환 확인
        Assertions.assertNotNull(books);
        Assertions.assertTrue(books.isEmpty());
    }

    @Test
    void testReadMembersFromNonExistentFile() {
        // 존재하지 않는 파일에서 읽기 시도
        List<Member> members = FileStorageManager.readMembersFromFile("non_existent_file.txt");

        // 빈 리스트 반환 확인
        Assertions.assertNotNull(members);
        Assertions.assertTrue(members.isEmpty());
    }

    @AfterEach
    void tearDown() throws IOException {
        // 테스트 후 파일 삭제
        Files.deleteIfExists(Paths.get(TEST_BOOKS_FILE));
        Files.deleteIfExists(Paths.get(TEST_MEMBERS_FILE));
    }
}