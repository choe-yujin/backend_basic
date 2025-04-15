package mission.i_exception.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogManagerTest {

    private static final String TEST_LOG_FILE = "test_transaction.log";

    @BeforeEach
    void setUp() throws IOException {
        // 테스트 전에 기존 파일 제거
        Files.deleteIfExists(Paths.get(TEST_LOG_FILE));

        // 테스트용 트랜잭션 로그 파일 생성 및 데이터 추가
        LogManager.logTransaction("회원ID: LM001, 책제목: clean code, 대출시간: 2023-01-01 10:00:00", TEST_LOG_FILE);
        LogManager.logTransaction("회원ID: LM002, 책제목: Effective Java, 대출시간: 2023-01-02 11:00:00", TEST_LOG_FILE);
    }

    @Test
    @DisplayName("대출 기록 추가 확인")
    void testLogTransaction() {
        // 새 트랜잭션 추가
        LogManager.logTransaction("회원ID: LM003, 책제목: Design Patterns, 대출시간: 2023-01-03 12:00:00", TEST_LOG_FILE);

        // 파일에서 트랜잭션 읽기
        List<String> transactions = LogManager.readTransactions(TEST_LOG_FILE);

        // 트랜잭션 수 확인
        assertEquals(3, transactions.size());

        // 마지막 트랜잭션 내용 확인
        assertTrue(transactions.get(2).contains("LM003"));
        assertTrue(transactions.get(2).contains("Design Patterns"));
    }

    @Test
    void testReadTransactions() {
        // 파일에서 트랜잭션 읽기
        List<String> transactions = LogManager.readTransactions(TEST_LOG_FILE);

        // 트랜잭션 수 확인
        assertEquals(2, transactions.size());

        // 트랜잭션 내용 확인
        assertTrue(transactions.get(0).contains("LM001"));
        assertTrue(transactions.get(0).contains("clean code"));
        assertTrue(transactions.get(1).contains("LM002"));
        assertTrue(transactions.get(1).contains("Effective Java"));
    }

    @Test
    void testRemoveTransaction() {
        // 트랜잭션 삭제
        boolean removed = LogManager.removeTransaction("LM001", "clean code", TEST_LOG_FILE);

        // 삭제 성공 확인
        assertTrue(removed);

        // 파일에서 트랜잭션 읽기
        List<String> transactions = LogManager.readTransactions(TEST_LOG_FILE);

        // 트랜잭션 수 확인
        assertEquals(1, transactions.size());

        // 남은 트랜잭션 내용 확인
        assertTrue(transactions.get(0).contains("LM002"));
        assertTrue(transactions.get(0).contains("Effective Java"));
    }

    @Test
    void testRemoveNonExistentTransaction() {
        // 존재하지 않는 트랜잭션 삭제 시도
        boolean removed = LogManager.removeTransaction("LM999", "Non-existent Book", TEST_LOG_FILE);

        // 삭제 실패 확인
        assertFalse(removed);

        // 파일에서 트랜잭션 읽기
        List<String> transactions = LogManager.readTransactions(TEST_LOG_FILE);

        // 트랜잭션 수는 그대로
        assertEquals(2, transactions.size());
    }

    @Test
    void testHasMemberTransactions() {
        // 존재하는 회원 ID 확인
        assertTrue(LogManager.hasMemberTransactions("LM001", TEST_LOG_FILE));
        assertTrue(LogManager.hasMemberTransactions("LM002", TEST_LOG_FILE));

        // 존재하지 않는 회원 ID 확인
        assertFalse(LogManager.hasMemberTransactions("LM999", TEST_LOG_FILE));
    }

    @Test
    void testHasBookTransaction() {
        // 존재하는 회원 ID와 책 제목 확인
        assertTrue(LogManager.hasBookTransaction("LM001", "clean code", TEST_LOG_FILE));
        assertTrue(LogManager.hasBookTransaction("LM002", "Effective Java", TEST_LOG_FILE));

        // 존재하지 않는 조합 확인
        assertFalse(LogManager.hasBookTransaction("LM001", "Effective Java", TEST_LOG_FILE));
        assertFalse(LogManager.hasBookTransaction("LM002", "clean code", TEST_LOG_FILE));
        assertFalse(LogManager.hasBookTransaction("LM999", "Non-existent Book", TEST_LOG_FILE));
    }

    @Test
    void testReadTransactionsFromNonExistentFile() {
        // 존재하지 않는 파일에서 읽기 시도
        List<String> transactions = LogManager.readTransactions("non_existent_file.log");

        // 빈 리스트 반환 확인
        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());
    }

    @AfterEach
    void tearDown() throws IOException {
        // 테스트 후 파일 삭제
        Files.deleteIfExists(Paths.get(TEST_LOG_FILE));
    }
}