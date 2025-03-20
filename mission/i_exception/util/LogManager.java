package mission.i_exception.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogManager {
    /**
     * 트랜잭션 기록을 파일에 저장
     *
     * @param record   저장할 트랜잭션 기록 문자열
     * @param filePath 저장할 파일 경로
     */
    public static void logTransaction(String record, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(record);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("트랜잭션 기록 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 파일에서 모든 트랜잭션 기록을 읽어옴
     *
     * @param filePath 읽을 파일 경로
     * @return 트랜잭션 기록 리스트
     */
    public static List<String> readTransactions(String filePath) {
        List<String> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.err.println("트랜잭션 읽기 중 오류 발생: " + e.getMessage());
        }

        return transactions;
    }

    /**
     * 파일에서 특정 회원과 책 제목에 해당하는 트랜잭션 기록을 삭제
     *
     * @param membershipId 회원 ID
     * @param bookTitle    책 제목
     * @param filePath     파일 경로
     * @return 성공적으로 삭제했으면 true, 아니면 false
     */
    public static boolean removeTransaction(String membershipId, String bookTitle, String filePath) {
        List<String> transactions = readTransactions(filePath);
        boolean removed = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String transaction : transactions) {
                // 해당 회원ID와 책 제목이 있는 트랜잭션은 건너뛰고 나머지만 다시 작성
                if (!transaction.contains(membershipId) || !transaction.contains(bookTitle)) {
                    writer.write(transaction);
                    writer.newLine();
                } else {
                    removed = true;
                }
            }
        } catch (IOException e) {
            System.err.println("트랜잭션 삭제 중 오류 발생: " + e.getMessage());
            return false;
        }

        return removed;
    }

    /**
     * 해당 회원 ID의 트랜잭션이 파일에 존재하는지 확인
     *
     * @param membershipId 검색할 회원 ID
     * @param filePath     파일 경로
     * @return 존재하면 true, 아니면 false
     */
    public static boolean hasMemberTransactions(String membershipId, String filePath) {
        List<String> transactions = readTransactions(filePath);

        for (String transaction : transactions) {
            if (transaction.contains(membershipId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 해당 회원 ID와 책 제목에 해당하는 트랜잭션이 존재하는지 확인
     *
     * @param membershipId 회원 ID
     * @param bookTitle    책 제목
     * @param filePath     파일 경로
     * @return 존재하면 true, 아니면 false
     */
    public static boolean hasBookTransaction(String membershipId, String bookTitle, String filePath) {
        List<String> transactions = readTransactions(filePath);

        for (String transaction : transactions) {
            if (transaction.contains(membershipId) && transaction.contains(bookTitle)) {
                return true;
            }
        }

        return false;
    }
}