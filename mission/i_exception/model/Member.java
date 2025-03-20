package mission.i_exception.model;

public class Member {

    private String name;
    private String membershipId;

    public Member(String name, String membershipId) {
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
        return "Member{" +
                "name='" + name + '\'' +
                ", membershipId='" + membershipId + '\'' +
                '}';
    }
}
