package mission.library;

/*LibraryMember 배열을 입력받고, for문을 사용하여 각 회원의 정보를 문자열로 결합하는 processMembers() 메서드를 구현한다.
*
* LibraryMember 배열을 입력받고, for문을 사용하여 각 회원의 정보를
"회원: [name], ID: [membershipId], 대출 한도: [borrowLimit]" 형태의 문자열로 결합한 후,
세미콜론(;)으로 구분하여 최종 문자열로 반환한다.
* */
public class MemberManager {
    private final LibraryMember[] members;

    public MemberManager(LibraryMember[] members) {
        this.members = members;
    }

    public String processMembers() {
        StringBuilder result = new StringBuilder();

        int length = members.length;
        for (int i = 0; i < length; i++) {
            LibraryMember member = members[i];
            String name = member.getName();
            String membershipId = member.getMembershipId();
            int borrowLimit = member.getBorrowLimit();

            result.append("회원: ").append(name)
                    .append(", ID: ").append(membershipId)
                    .append(", 대출 한도: ").append(borrowLimit);

            // 마지막 요소가 아니면 구분자 추가
            if (i < length - 1) {
                result.append("; ");
            }
        }

        return result.toString();
    }

}