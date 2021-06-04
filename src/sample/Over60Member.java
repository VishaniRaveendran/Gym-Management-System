package sample;

public class Over60Member extends DefaultMember{
    //---Instance Variables---//
    private int age;

    //------------------------------------------------------Constructor Declaration of Class------------------------------------------------------//
    public Over60Member(int membershipNumber, String firstName, String lastName, String startMembershipDate, String contactNumber,String type, int age) {
        super(membershipNumber, firstName, lastName, startMembershipDate, contactNumber, type);
        setAge(age);
    }
    //-----------------------Getter-----------------------//
    public int getAge(){
        return age;
    }

    //-----------------------Setter-----------------------//
    public void setAge(int age) {
        if(age >=60 ) {
            this.age = age;
        }else{
            throw new IllegalArgumentException("Invalid age for a over 60 member");
        }
    }

}
