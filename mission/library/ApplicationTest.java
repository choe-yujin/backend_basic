package mission.library;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {
    private LibraryMember[] members;
    MemberManager manager;

    /*
     * @BeforeEach란
     * - 각 테스트 메서드 실행 전에 호출되는 설정 메서드
     * - 역할 : 테스트 환경 초기화 (예 : 객체 생성, 데이터 준비)
     * - 비유 : 계산기 테스트 전에 새 계산기를 꺼내는 것과 같다.
     * */
    @BeforeEach
    void setUp() {
        members = new LibraryMember[] {
                new LibraryMember("Alice", "LM001"),
                new StudentMember("Bob", "LM002")
        };
        manager = new MemberManager(members);
        System.out.println("테스트를 위한 LibrayMember와 MemberManager가 준비 되었습니다.");
    }

    @Test
    public void processMembers() {
        String expectedResult = "회원: Alice, ID: LM001, 대출 한도: 3; 회원: Bob, ID: LM002, 대출 한도: 5";
        String result = manager.processMembers();
        Assertions.assertEquals(expectedResult, result);
    }

    /*
     * @AfterEach
     * - 각 테스트 메서드 실행 후에 호출되는 정리 메서드
     * - 역할 : 테스트 후 자원 해제나 상태 초기화
     * - 비유 : 계산기 테스트 끝나면 버튼 초기화하거나 치우는 것
     */
    @AfterEach
    void tearDown(){
        this.manager = null;
        this.members = null;
        System.out.println("테스트가 종료되어 manager와 members를 정리합니다.");
    }
}