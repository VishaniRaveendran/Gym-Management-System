package sample;

public class StudentMember extends DefaultMember{
    //---Instance Variables---//
    private String schoolName;

    //------------------------------------------------------Constructor Declaration of Class------------------------------------------------------//
    public StudentMember(int membershipNumber, String firstName, String lastName, String startMembershipDate, String contactNumber,String type, String schoolName) {
        super(membershipNumber, firstName, lastName, startMembershipDate, contactNumber,type);
        this.schoolName = schoolName;
    }

    //-----------------------Getter-----------------------//
    public String getSchoolName() {
        return schoolName;
    }

    //-----------------------Setter-----------------------//
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
