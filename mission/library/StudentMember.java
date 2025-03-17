package mission.library;

/*
 * LibraryMember를 상속받고, getBorrowLimit()를 오버라이딩하여 5권을 반환하도록 구현한다.
 * */
public class StudentMember extends LibraryMember {
    private static final int DEFAULT_BORROW_LIMIT = 5;
    public StudentMember(String name, String membershipId) {
        super(name, membershipId);
    }

    @Override
    public int getBorrowLimit() {
        return DEFAULT_BORROW_LIMIT;
    }
}