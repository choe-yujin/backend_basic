package mission.d_library;

public class Application {
    /* main() 메서드에서 예제 데이터를 이용하여 결과 문자열을 출력한다.
    *
    * 예시 결과:
"회원: Alice, ID: LM001, 대출 한도: 3; 회원: Bob, ID: LM002, 대출 한도: 5"
    * */
    public static void main(String[] args) {
        LibraryMember[] members = new LibraryMember[] {
                new LibraryMember("Alice", "LM001"),
                new StudentMember("Bob", "LM002")
        };

        MemberManager manager = new MemberManager(members);
        System.out.println(manager.processMembers());
    }
}
