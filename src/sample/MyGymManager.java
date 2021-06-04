package sample;

import javafx.scene.control.Label;
import java.io.*;
import java.util.*;

public class MyGymManager implements GymManager{
    private ArrayList<DefaultMember> memberList = new ArrayList<DefaultMember>();

    //----------------Adding a member----------------//
    @Override
    public void addMember(DefaultMember member) throws IOException {
        if(memberList.size()<100){
            memberList.add(member);
            System.out.println(member.getFirstName()+" "+member.getLastName()+" "+"is successfully added.");
            System.out.println("No of occupied slots : "+ memberList.size());
            System.out.println("No of free slots: "+(100-memberList.size()));
        }else{
            System.out.println("No free slots are available for new members");
        }
    }

    //---------------------Deleting a member-----------------//
    @Override
    public boolean deleteMember(int membershipNumber) {
        boolean flag = false;
        for(DefaultMember member:memberList){
            if (Objects.equals(member.getMembershipNumber(), membershipNumber)){
                flag = true;
                memberList.remove(member);
                System.out.println("Member with the membership number "+membershipNumber+" Successfully removed");
                System.out.println("No of occupied slots : "+memberList.size());
                System.out.println("No of free slots: "+(100-memberList.size()));
                if (member instanceof StudentMember){
                    System.out.println("Member type is: StudentMember");
                }else if(member instanceof Over60Member){
                    System.out.println("Member type is: Over60Member");
                }else{
                    System.out.println("Member type is: DefaultMember");
                }
                break;
            }
        }if (!flag){
            System.out.println(membershipNumber+"is not found");
        }
        return false;
    }

    //------------------Printing list of member details---------------------------//
    @Override
    public void printListOfMember() {
        if(memberList.size() ==0){
            System.out.println("List is Empty");
        }else {
            for (DefaultMember member : memberList) {
                System.out.println("===========================================");
                System.out.println("Membership Number : " + member.getMembershipNumber() + " ");

                if (member instanceof StudentMember) {
                    System.out.println("Member type is : StudentMember");
                    System.out.println("School Name:" + ((StudentMember) member).getSchoolName());
                } else if (member instanceof Over60Member) {
                    System.out.println("Member type is : Over60Member");
                    System.out.println("Age :" + ((Over60Member) member).getAge());
                } else {
                    System.out.println("Member type is: DefaultMember");
                }
                System.out.println("");
            }
        }
    }

    //--------------------------Sorting number list-------------------//
    @Override
    public List<DefaultMember> sortMember() {
        //creating arraylist ti get member names
        ArrayList<String> arrayList = new ArrayList<>();
        //adding names of members to the arraylist

        for(int i=0 ; i <memberList.size();i++){
            String firstName = memberList.get(i).getFirstName();
            arrayList.add(firstName);
        }

        //array to store names of members
        String[] arra = arrayList.toArray(new String[]{});

        //sorting names in ascending order
        BubbleSort.bubbleSort(arra,true);

        //match member names with objects
        List<String> sortList = Arrays.asList(arra);

        //match member names with object
        for (String element : sortList){
            for (DefaultMember o : memberList){
                if (element.equals(o.getFirstName())){
                    System.out.println(o.toString());
                }
            }
        }

        return memberList;
    }

    @Override
    public void saveMembers() {
        DefaultMember[] array = memberList.toArray(new DefaultMember[]{});
        File file =  new File("Gym Management System Details.txt");
        BufferedWriter fileOut;


        try (FileWriter fileWr = new FileWriter(file)) {
            fileOut = new BufferedWriter(fileWr);
            int v = memberList.size();
            for (int r = 0; r < memberList.size(); r++) {
                String schoolName;
                int age;
                if(memberList.get(r) instanceof StudentMember){
                    schoolName = ((StudentMember) memberList.get(r)).getSchoolName();
                    fileOut.write(
                            "Membership Number " + memberList.get(r).getMembershipNumber()
                                    + " First Name : " + memberList.get(r).getFirstName()
                                    + "Last Name : " + memberList.get(r).getLastName()
                                    + " Start MembershipDate : " + memberList.get(r).getStartMembershipDate()
                                    + " Contact Number : " + memberList.get(r).getContactNumber()
                                    + " School Name : " + ((StudentMember) memberList.get(r)).getSchoolName()
                    );
                } else if (memberList.get(r) instanceof Over60Member) {
                    age = (((Over60Member) memberList.get(r)).getAge());
                    fileOut.write("Membership Number " + memberList.get(r).getMembershipNumber()
                            + " First Name : " + memberList.get(r).getFirstName()
                            + "Last Name : " + memberList.get(r).getLastName()
                            + " Start MembershipDate : " + memberList.get(r).getStartMembershipDate()
                            + " Contact Number : " + memberList.get(r).getContactNumber()
                            + " Age : " + ((Over60Member) memberList.get(r)).getAge()
                    );
                }else {
                    fileOut.write("Membership Number " + memberList.get(r).getMembershipNumber()
                            + " First Name : " + memberList.get(r).getFirstName()
                            + "Last Name : " + memberList.get(r).getLastName()
                            + " Start MembershipDate : " + memberList.get(r).getStartMembershipDate()
                            + " Contact Number : " + memberList.get(r).getContactNumber()
                    );
                }
                    System.out.println("Saved Successfully");
                    fileOut.flush();
                    fileOut.newLine();
                }

        } catch (IOException e) {
            System.out.println("No details to save");
        }
    }

    // adding member details to a file for tableview
    public void saveForSearch() {
        DefaultMember[] savingSearch = memberList.toArray(new DefaultMember[]{});
        File f = new File("searchSave.txt");
        BufferedWriter fileOut;

        try (FileWriter fileW = new FileWriter(f)) {
            fileOut = new BufferedWriter(fileW);

            int v = memberList.size();
            for (int r = 0; r < memberList.size(); r++) {
                String schoolName;
                int age;

                if (memberList.get(r) instanceof StudentMember){
                    schoolName = ((StudentMember) memberList.get(r)).getSchoolName();
                    fileOut.write(
                            memberList.get(r).getMembershipNumber()
                                    + " " + memberList.get(r).getFirstName()
                                    + " " + memberList.get(r).getLastName()
                                    +" " + memberList.get(r).getStartMembershipDate()
                                    +" " + memberList.get(r).getContactNumber()
                                    +" " + ((StudentMember) memberList.get(r)).getSchoolName()
                                    +" 0"
                                    +" student");

                } else if (memberList.get(r) instanceof Over60Member) {
                    age = ((Over60Member) memberList.get(r)).getAge();
                    fileOut.write(
                            memberList.get(r).getMembershipNumber()
                                    + " " + memberList.get(r).getFirstName()
                                    + " " + memberList.get(r).getLastName()
                                    +" " + memberList.get(r).getStartMembershipDate()
                                    +" " + memberList.get(r).getContactNumber()
                                    +" null"
                                    +" " + ((Over60Member) memberList.get(r)).getAge()
                                    +" over60");

                } else {
                    fileOut.write(
                            memberList.get(r).getMembershipNumber()
                                    + " " + memberList.get(r).getFirstName()
                                    + " " + memberList.get(r).getLastName()
                                    +" " + memberList.get(r).getStartMembershipDate()
                                    +" " + memberList.get(r).getContactNumber()
                                    +" null 0 default");
                }

                System.out.println("saved successfully");

                fileOut.flush();
                fileOut.newLine();
            }

        } catch (IOException e) {
            System.out.println("No data to save");
        }
    }

    //searching for the member details
    public static <T> int searchingRecord(T[] array, T searchValue){
        for (int i = 0; i<array.length;i++){
            if (array[i].equals(searchValue)){
                return i;
            }
        }return -1;
    }


    @Override
    public void search(String searchValue, Label label1) {
        DefaultMember[] memberli = memberList.toArray(new DefaultMember[]{});
        ArrayList<DefaultMember> arrayList = new ArrayList<>();
        File f = new File("searchSave.txt");
        try {
            Scanner input =  new Scanner(f);


            while (input.hasNext()){
                int membershipNumber = input.nextInt();
                String firstName = input.next();
                String lastName = input.next();
                String startMembershipdate = input.next();
                String contactNumber = input.next();
                String schoolName = input.next();
                int age = input.nextInt();
                String type = input.next();

                DefaultMember member = null;
                //inserting data into the table
                if (type.equals("student")){
                    member = new StudentMember(membershipNumber, firstName, lastName, startMembershipdate,contactNumber,type,schoolName);

                } else if (type.equals("over60")) {
                    member = new Over60Member(membershipNumber, firstName, lastName, startMembershipdate,contactNumber,type,age );

                }else {
                    member = new DefaultMember(membershipNumber, firstName, lastName, startMembershipdate,contactNumber,type);
                }
                arrayList.add(member);
            }


            String memberName ;
            int membershipNumber;

            for (DefaultMember member : arrayList){
                System.out.println(member.toString());
                if (searchValue.equals(member.getFirstName()) || searchValue == String.valueOf(member.getMembershipNumber())){
                    label1.setText(member.toString());
                    break;
                }else {
                    label1.setText("not found");
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
