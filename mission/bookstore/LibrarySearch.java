package mission.bookstore;

import mission.bookstore.model.Book;

/*
메서드 String searchBooks(Book[] books, String keyword) 구현
for문을 사용해 배열 순회 및 검색 결과 문자열 생성
main() 메서드를 작성하여, 샘플 Book 배열과 검색 키워드를 사용해 결과를 출력한다.
*/
public class LibrarySearch {
    public static void main(String[] args) {
        // 샘플 Book 배열과 검색 키워드를 사용해 결과를 출력한다.
        Book[] books = {
                new Book("The Great Gatsby", "F. Scott Fitzgerald", 8.99),
                new Book("To Kill a Great Mockingbird", "Harper Lee", 8.95),
                new Book("Moby Dick", "Herman Melville", 8.94)
        };

        String keyword = "Great";
        String result = searchBooks(books, keyword);
        System.out.println(result);
    }

    /*메서드 String searchBooks(Book[] books, String keyword)를 구현하여, for문을 사용해 배열을 순회하며 각 Book의 matches() 메서드를 호출한다.
    일치하는 도서들의 정보를 세미콜론(;)으로 구분하여 연결한 결과 문자열을 반환한다.*/
    public static String searchBooks(Book[] books, String keyword) {
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            Boolean isMatched = book.matches(keyword);
            if (isMatched) {
                result.append(book.toString());
                result.append("; ");
            }
        }
        return result.toString();
    }
}