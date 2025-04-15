package mission.i_exception.util;

import mission.i_exception.model.Book;
import mission.i_exception.model.Member;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorageManager {
    // Book 객체와 Member 객체의 정보를 파일(예: "books.txt", "members.txt")에 저장한다.
    // 도서 정보를 담은 Book 객체 리스트
    public static void saveBooksToFile(List<Book> books, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("도서 파일 저장 중 오류 발생 : " + e.getMessage());
        }
    }

    // Book 객체와 Member 객체의 정보를 파일(예: "books.txt", "members.txt")에 저장한다.
    // 회원 정보를 담은 Member 객체 리스트
    public static void saveMembersToFile(List<Member> members, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Member member : members) {
                writer.write(member.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("회원 파일 저장 중 오류 발생 : " + e.getMessage());
        }
    }

    // 새 회원 추가
    public static void addMemberToFile(Member member, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(member.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("회원 파일 저장 중 오류 발생 : " + e.getMessage());
        }
    }

    // 파일에서 도서 정보 읽기
    public static List<Book> readBooksFromFile(String filePath) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Book{title='clean code', author='Robert C. Martin', price=45.0} 형식으로 저장됨
                // 파싱하여 Book 객체 생성
                try {
                    String titlePart = line.substring(line.indexOf("title='") + 7, line.indexOf("', author"));
                    String authorPart = line.substring(line.indexOf("author='") + 8, line.indexOf("', price"));
                    String pricePart = line.substring(line.indexOf("price=") + 6, line.indexOf("}"));

                    double price = Double.parseDouble(pricePart);
                    Book book = new Book(titlePart, authorPart, price);
                    books.add(book);
                } catch (Exception e) {
                    System.err.println("도서 정보 파싱 중 오류 발생: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("도서 파일 읽기 중 오류 발생 : " + e.getMessage());
        }

        return books;
    }

    // 파일에서 회원 정보 읽기
    public static List<Member> readMembersFromFile(String filePath) {
        List<Member> members = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Member{name='Alice', membershipId='LM001'} 형식으로 저장됨
                // 파싱하여 Member 객체 생성
                try {
                    String namePart = line.substring(line.indexOf("name='") + 6, line.indexOf("', membershipId"));
                    String idPart = line.substring(line.indexOf("membershipId='") + 14, line.indexOf("'}"));

                    Member member = new Member(namePart, idPart);
                    members.add(member);
                } catch (Exception e) {
                    System.err.println("회원 정보 파싱 중 오류 발생: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("회원 파일 읽기 중 오류 발생 : " + e.getMessage());
        }

        return members;
    }

    // 회원 ID가 존재하는지 확인
    public static boolean memberExists(String membershipId, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("membershipId='" + membershipId + "'")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("회원 확인 중 오류 발생 : " + e.getMessage());
        }

        return false;
    }

    // 책 제목이 존재하는지 확인
    public static boolean bookExists(String title, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("title='" + title + "'")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("도서 확인 중 오류 발생 : " + e.getMessage());
        }

        return false;
    }
}