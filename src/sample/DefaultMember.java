package sample;

import java.io.Serializable;
import java.util.Objects;

public class DefaultMember extends Object implements Serializable,Comparable <DefaultMember>{

    //-----Instance Variables-----//
    private Integer membershipNumber;
    private String firstName;
    private String lastName;
    private String startMembershipDate;
    private String contactNumber;
    private String type;

    //------------------------------------------------------Constructor----------------------------------------//
    public DefaultMember(Integer membershipNumber, String firstName, String lastName, String startMembershipDate, String contactNumber){
        super();
        this.membershipNumber = membershipNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startMembershipDate = startMembershipDate;
        this.contactNumber = contactNumber;
    }


    //-----------------------Getter-----------------------//
    public Integer getMembershipNumber() {
        return membershipNumber;
    }

    //-----------------------Setter-----------------------//
    public void setMembershipNumber(Integer membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    //-----------------------Getter-----------------------//
    public String getFirstName() {
        return firstName;
    }

    //-----------------------Setter-----------------------//
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //-----------------------Getter-----------------------//
    public String getLastName() {
        return lastName;
    }

    //-----------------------Setter-----------------------//
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //-----------------------Getter-----------------------//
    public String getStartMembershipDate() {
        return startMembershipDate;
    }

    //-----------------------Setter-----------------------//
    public void setStartMembershipDate(String startMembershipDate) {
        this.startMembershipDate = startMembershipDate;
    }

    //-----------------------Getter-----------------------//
    public String getContactNumber() {
        return contactNumber;
    }

    //-----------------------Setter-----------------------//
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    //compareTo() method is used to compare the given string with current string
    @Override
    public int compareTo(DefaultMember o) {
        return this.firstName.compareTo(o.firstName);
    }

    @Override
    public String toString() {
        return "Member{" +
                "membershipNumber=" + membershipNumber +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", startMembershipDate='" + startMembershipDate + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    public String getMemberType() {
        return type;
    }

    public void setMemberType(String type) {
        this.type = type;
    }

    public DefaultMember(int membershipNumber, String firstName, String lastName, String startMembershipDate, String contactNumber,String type){
        super();
        this.membershipNumber = membershipNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startMembershipDate = startMembershipDate;
        this.contactNumber = contactNumber;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultMember)) return false;
        DefaultMember member = (DefaultMember) o;
        return membershipNumber.equals(member.membershipNumber) &&
                firstName.equals(member.firstName) &&
                lastName.equals(member.lastName) &&
                startMembershipDate.equals(member.startMembershipDate) &&
                contactNumber.equals(member.contactNumber) &&
                Objects.equals(type, member.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipNumber, firstName,lastName, startMembershipDate, contactNumber, type);
    }
}



