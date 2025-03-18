package mission.d_library;

/*private 멤버 변수, 생성자, getter 및 기본 getBorrowLimit() (3권) 메서드를 구현한다.

private 멤버 변수: name(String), membershipId(String)
생성자와 getter 메서드를 구현하고, 기본 대출 한도(getBorrowLimit())는 3권을 반환한다.*/
public class LibraryMember {
    private static final int DEFAULT_BORROW_LIMIT = 3;
    private String name;
    private String membershipId;

    public int getBorrowLimit() {
        return DEFAULT_BORROW_LIMIT;
    }

    public LibraryMember(String name, String membershipId) {
        this.name = name;
        this.membershipId = membershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public String toString() {
        return "LibraryMember{name='" + name + "', membershipId='" + membershipId + "'}";
    }
}
