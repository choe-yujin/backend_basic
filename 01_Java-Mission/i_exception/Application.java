package mission.i_exception;

import mission.i_exception.exceptions.EmptyInputException;
import mission.i_exception.exceptions.InputTooLongException;
import mission.i_exception.model.Book;
import mission.i_exception.model.Member;
import mission.i_exception.util.FileStorageManager;
import mission.i_exception.util.LogManager;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final String BOOKS_FILE = "books.txt";
    private static final String MEMBERS_FILE = "members.txt";
    private static final String TRANSACTION_FILE = "transaction.log";
    private static final int MAX_INPUT_LENGTH = 100;

    public static void main(String[] args) {
        // 초기 데이터 생성 (처음 실행할 때만)
        initializeData();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                displayMainMenu();
                int choice = getIntInput(scanner);

                switch (choice) {
                    case 1:
                        processBorrow(scanner);
                        break;
                    case 2:
                        processReturn(scanner);
                        break;
                    case 3:
                        running = false;
                        System.out.println("프로그램을 종료합니다.");
                        break;
                    default:
                        System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                }
            } catch (Exception e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void initializeData() {
        File booksFile = new File(BOOKS_FILE);
        File membersFile = new File(MEMBERS_FILE);
        File transactionFile = new File(TRANSACTION_FILE);

        // 모든 파일이 존재하면 초기화 생략
        if (booksFile.exists() && membersFile.exists() && transactionFile.exists()) {
            return;
        }

        // 도서 정보를 담은 Book 객체 리스트
        List<Book> books = List.of(
                new Book("clean code", "Robert C. Martin", 45.0),
                new Book("Effective Java", "Joshua Bloch", 55.0)
        );

        // 회원 정보를 담은 Member 객체 리스트
        List<Member> members = List.of(
                new Member("Alice", "LM001"),
                new Member("Bob", "LM002")
        );

        FileStorageManager.saveBooksToFile(books, BOOKS_FILE);
        FileStorageManager.saveMembersToFile(members, MEMBERS_FILE);

        // 트랜잭션 파일이 없으면 빈 파일 생성
        if (!transactionFile.exists()) {
            try {
                transactionFile.createNewFile();
            } catch (Exception e) {
                System.err.println("트랜잭션 파일 생성 중 오류 발생: " + e.getMessage());
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== 도서 대여 시스템 =====");
        System.out.println("1번. 대여하기");
        System.out.println("2번. 반납하기");
        System.out.println("3번. 종료");
        System.out.print("선택: ");
    }

    private static int getIntInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return -1;
        }
    }

    private static String getStringInput(Scanner scanner, String prompt, int maxLength)
            throws EmptyInputException, InputTooLongException {
        System.out.println(prompt + " (종료하려면 'exit' 입력)");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
            System.out.println("이전 메뉴로 돌아갑니다.");
            return null;
        }

        if (input.isEmpty()) {
            throw new EmptyInputException("입력이 비어있습니다.");
        }

        if (input.length() > maxLength) {
            throw new InputTooLongException("입력이 너무 깁니다. 최대 " + maxLength + "자까지 입력 가능합니다.");
        }

        return input;
    }

    private static void processBorrow(Scanner scanner) {
        try {
            // 회원 ID 입력
            String membershipId = getStringInput(scanner, "회원 아이디를 입력해주세요.", MAX_INPUT_LENGTH);
            if (membershipId == null) {
                return; // 사용자가 종료 명령어 입력
            }

            // 회원 확인 또는 신규 등록
            if (!FileStorageManager.memberExists(membershipId, MEMBERS_FILE)) {
                String name = getStringInput(scanner, "등록되지 않은 회원입니다. 이름을 입력해주세요.", MAX_INPUT_LENGTH);
                if (name == null) {
                    return; // 사용자가 종료 명령어 입력
                }

                Member newMember = new Member(name, membershipId);
                FileStorageManager.addMemberToFile(newMember, MEMBERS_FILE);
                System.out.println("회원 등록이 완료되었습니다.");
            }

            // 책 제목 입력 및 확인
            boolean validBook = false;
            String bookTitle = "";

            while (!validBook) {
                bookTitle = getStringInput(scanner, "대출할 책 title을 입력해주세요.", MAX_INPUT_LENGTH);
                if (bookTitle == null) {
                    return; // 사용자가 종료 명령어 입력
                }

                if (FileStorageManager.bookExists(bookTitle, BOOKS_FILE)) {
                    validBook = true;
                } else {
                    System.out.println("해당하는 book이 없습니다. 다시 입력해주세요.");
                }
            }

            // 대출 처리
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = sdf.format(now);

            String record = String.format("회원ID: %s, 책제목: %s, 대출시간: %s",
                    membershipId, bookTitle, timestamp);

            LogManager.logTransaction(record, TRANSACTION_FILE);
            System.out.println("대출이 완료되었습니다.");

        } catch (EmptyInputException | InputTooLongException e) {
            System.out.println("입력 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("대여 처리 중 오류 발생: " + e.getMessage());
        }
    }

    private static void processReturn(Scanner scanner) {
        try {
            // 회원 ID 입력
            boolean validMember = false;
            String membershipId = "";

            while (!validMember) {
                membershipId = getStringInput(scanner, "회원 아이디를 입력해주세요.", MAX_INPUT_LENGTH);
                if (membershipId == null) {
                    return; // 사용자가 종료 명령어 입력
                }

                if (LogManager.hasMemberTransactions(membershipId, TRANSACTION_FILE)) {
                    validMember = true;
                } else {
                    System.out.println("해당 ID로 대출한 내역이 없습니다. 다시 입력해주세요.");
                }
            }

            // 책 제목 입력 및 확인
            boolean validBook = false;
            String bookTitle = "";

            while (!validBook) {
                bookTitle = getStringInput(scanner, "반납할 책 title을 입력해주세요.", MAX_INPUT_LENGTH);
                if (bookTitle == null) {
                    return; // 사용자가 종료 명령어 입력
                }

                if (LogManager.hasBookTransaction(membershipId, bookTitle, TRANSACTION_FILE)) {
                    validBook = true;
                } else {
                    System.out.println("해당 책 title의 대출 이력이 없습니다. 다시 입력해주세요.");
                }
            }

            // 반납 처리
            if (LogManager.removeTransaction(membershipId, bookTitle, TRANSACTION_FILE)) {
                System.out.println("반납이 완료되었습니다.");
            } else {
                System.out.println("반납 처리 중 오류가 발생했습니다.");
            }

        } catch (EmptyInputException | InputTooLongException e) {
            System.out.println("입력 오류: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("반납 처리 중 오류 발생: " + e.getMessage());
        }
    }
}