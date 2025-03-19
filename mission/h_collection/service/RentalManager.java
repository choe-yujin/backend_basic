package mission.h_collection.service;

import mission.h_collection.model.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalManager {
    Map<String, List<Book>> rentalList = new HashMap<>();

    // 1. 매개변수 memberId로 등록된 LibraryMember가 있는가?
    // 1-1. 해당 멤버가 있다면, 해당 memberId로 생성된 대여 List가 있는가?
    // 1-1-1. 대여 목록이 있다면 해당 대여 목록에 이어서 Book 요소 추가
    // 1-1-2. 대여 목록이 없다면 대여 List를 생성한 후에 Book 요소 추가
    // 2. 매개변수 memberId로 등록된 LibraryMember가 없는 경우
    //    해당 멤버가 없다는 문구 출력
    public boolean rentBook(String membershipId, Book book) {
        // 1. 회원이 존재하는지 확인
        if (!rentalList.containsKey(membershipId)) {
            System.out.println("회원 ID: " + membershipId + " 가 존재하지 않습니다.");
            return false;
        }

        // 1-1. 해당 회원의 대여 목록이 있다면 가져오고, 없으면 새 리스트를 생성하여 추가
        rentalList.computeIfAbsent(membershipId, k -> new ArrayList<>()).add(book); // Key가 존재할 경우: 기존에 존재하는 Key의 Value를 리턴, Key가 존재하지 않는 경우: 람다식을 적용한 값을 해당 key에 저장한 후 newValue를 반환
        return true;
    }

    // 대여 가능하도록 회원을 추가하는 메서드
    public void registerMember(String memberId) {
        rentalList.putIfAbsent(memberId, new ArrayList<>()); // Key가 존재할 경우: Map의 Value의 값을 반환하고, Key가 존재하지 않는 경우: Key와 Value를 Map에 저장하고 Null을 반환
    }

    // 출력 예) 회원ID: LM001 -> 대여 도서: [Title1]; [Title2]; [Title3];
    public String getRentalList(String membershipId) {
        // 1. 회원이 존재하는지 확인
        if (!rentalList.containsKey(membershipId)) {
            System.out.println("회원 ID: " + membershipId + " 가 존재하지 않습니다.");
            return "회원 ID: " + membershipId + " 가 존재하지 않습니다.";
        }

        List<Book> books = rentalList.get(membershipId);
        StringBuilder result = new StringBuilder("회원ID: " + membershipId + " -> 대여 도서: ");

        for (int i = 0; i < books.size(); i++) {
            result.append(books.get(i).toString());
            if (i < books.size() - 1) { // 마지막 요소에는 ; 안 붙음
                result.append("; ");
            }
        }

        return result.toString();
    }
}
