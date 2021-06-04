package sample;

import javafx.application.Application;

import java.io.*;
import java.util.Scanner;

public class ConsoleUI {

    private final static MyGymManager manager = new MyGymManager();
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        //-----------------------------------------------displays message---------------------------------------------//
        System.out.println("**************************************************************************************************");
        System.out.print("*                                       W E L C O M E                                            *\n");
        System.out.print("*                                            T O                                                 *\n");
        System.out.print("*                                     GYM MANAGEMENT SYSTEM                                      *\n");
        System.out.println("**************************************************************************************************");
        System.out.println();
        int count;
        for (count = 0; count <= 100; count++) {
            //--------------displays message------------------//
            System.out.println("******************************************* M E N U **********************************************\n");
            System.out.println("Enter \"A\" to add a member:  ");
            System.out.println("Enter \"D\" to delete a member: ");
            System.out.println("Enter \"P\" to print the list of members ");
            System.out.println("Enter \"O\" to Sort the members name in ascending order: ");
            System.out.println("Enter \"S\" to Save the details of member: ");
            System.out.println("Enter \"N\" to view and search member by name ");
            System.out.println("Enter \"Q\" to quit: ");
            System.out.print("Enter option: ");

            //--creating a new scanner variable--//
            Scanner input = new Scanner(System.in);
            String enteredOption = input.next();

            //If user enter UpperCase letter change it to LowerCase
            switch (enteredOption.toLowerCase()) {
                case "a":                                                                     //checks if the user has entered "a" or "A" and proceed
                    addMember();                                                              //calls the addMember method
                    break;
                case "d":                                                                     //checks if the user has entered "d" or "D" and proceed
                    deleteMember();                                                           //calls the deleteMember method
                    break;
                case "p":                                                                     //checks if the user has entered "p" or "P" and proceed
                    manager.printListOfMember();                                              //calls the printListMember method
                    break;
                case "o":                                                                     //checks if the user has entered "o" or "O" and proceed
                    manager.sortMember();                                                     //calls the sortMember method
                    break;
                case "s":                                                                     //checks if the user has entered "s" or "S" and proceed
                    manager.saveMembers();                                                    //calls the saveMember method
                    break;
                case "n":                                                                     //checks if the user has entered "n" or "N" and proceed
                    manager.saveForSearch();
                    Application.launch(DataViewTable.class, args);                                   //calls the searchMember method
                    break;
                case "q":                                                                     //checks if the user has entered "q" or "Q" and proceed
                    System.out.println("Program Ending");                                     //exits the program
                    System.exit(0);
                    break;
                default:
                    System.out.print("You have entered an incorrect option...\nPlease enter a correct option from (A,D,P,O,S,L,N,M,Q) only...\n\n ");
            }
        }
    }


    //Implementing a method to to delete the membership details by getting their membershipId
    private static void deleteMember() throws IOException {
        //-------------------creating file----------------------------//
        File memberDetails = new File("Gym Management System Details.txt");
        File temDb = new File("database.txt");
        BufferedReader br = new BufferedReader(new FileReader(memberDetails));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temDb));
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the membership number: ");
        String membershipNumber = input.next();

        String result;
        manager.deleteMember(Integer.parseInt(membershipNumber));

        while ((result=br.readLine())!=null) {
            if (result.contains(membershipNumber))
                continue;
            bw.write(result);
            bw.flush();
            bw.newLine();

        }
        br.close();
        bw.close();
        memberDetails.delete();
        temDb.renameTo(memberDetails);
    }

    //Implementing a method to to add the membership details
    private static void addMember() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Gym Management System Details.txt",true));
        Scanner input = new Scanner(System.in);
        if (count <= 100) {

            int membershipNumber=count;
            System.out.println("Membership Number: "+ membershipNumber);
            System.out.print("First Name: ");
            String firstName = input.nextLine();
            while(!firstName.matches("[a-zA-Z]+")){
                System.out.println("Please enter a valid first name!");
                System.out.print("First Name: ");
                firstName = input.nextLine();
            }
            System.out.print("Last Name: ");
            String lastName = input.nextLine();
            while(!lastName.matches("[a-zA-Z]+")){
                System.out.println("Please enter a valid last name!");
                System.out.print("Last Name: ");
                lastName = input.nextLine();
            }

            System.out.println("Start Membership Date in the following format DD/MM/YYYY");
           //String startMembershipDate =input.nextLine();
            System.out.print("Date:");
            String date = input.nextLine();
            System.out.print("Month:");
            String month = input.nextLine();
            System.out.print("Year:");
            String year = input.nextLine();
            String startMembershipDate=date+"/"+month+"/"+year;

            System.out.print("Contact Number: ");
            String contactNumber = input.nextLine();
            while(!contactNumber.matches("[0-9]{10}$+")){
                System.out.println("Please enter a valid contact number!");
                System.out.print("Contact Number: ");
                contactNumber = input.nextLine();
            }
            System.out.print("Enter the type of membership (D - Default Member/ S - Student Member/ O - Over60 Member): ");
            String type = input.nextLine();
            while (!(type.equals("d")||type.equals("s")||type.equals("o")||type.equals("D")||type.equals("S")||type.equals("O"))){
                System.out.println("Please enter a valid type of membership!");
                System.out.print("Enter the type of membership (D - Default Member/ S - Student Member/ O - Over60 Member):");
                type = input.nextLine();
            }
            DefaultMember member = null;


            switch (type) {
                case "D":
                case "d":
                    member = new DefaultMember(membershipNumber,firstName,lastName,startMembershipDate,contactNumber);
                    break;
                case "S":
                case "s":
                    System.out.print("School Name: ");
                    String schoolName = input.next();
                    while(!schoolName.matches("[a-zA-Z]+")){
                        System.out.println("Please enter a valid last name!");
                        System.out.print("School Name: ");
                        schoolName = input.nextLine();
                    }
                    member = new StudentMember(membershipNumber,firstName,lastName,startMembershipDate,contactNumber,type,schoolName);
                    break;
                case "O":
                case "o":
                    System.out.print("Age: ");
                    int age = input.nextInt();
                    while(!(age>60 && age<100)){
                        System.out.println("Please enter a valid age!");
                        System.out.print("Age: ");
                        age = input.nextInt();
                    }
                    member = new Over60Member(membershipNumber,firstName,lastName,startMembershipDate,contactNumber,type,age);
                    break;
                default:
                    System.out.println("Invalid input");
            }

            manager.addMember(member);
            count++;
            bw.write("Membership Number : "+membershipNumber+"    First Name : "+ firstName+"    Last Name : "+lastName+"    Start Membership Date:"+startMembershipDate+"    Contact Number : "+contactNumber+"    Membership Type :"+ type);
            bw.flush();
            bw.newLine();
            bw.close();
        } else {
            System.out.println("No free slots");
        }
    }
}
