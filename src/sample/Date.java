package sample;

public class Date extends DefaultMember{
    private int month; //1-12
    private int day;   //1-31 based on month
    private int year;  //any year


    public Date(int membershipNumber, String firstName, String lastName, String startMembershipDate, String contactNumber,String type,int month, int day, int year) {
        super(membershipNumber, firstName, lastName, startMembershipDate, contactNumber,type);
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth(int month){
        return month;
    }

    public int setMonth(int testMonth){
        if (testMonth>0 && testMonth<=12)
            return testMonth;
        else{
            System.out.printf("Invalid month (%d)set to 1.",testMonth);
            return 1;
        }
    }

    public int getDay(int testDay){
        return day;
    }

    public int setDay(int testDay) {
        int daysPerMonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (testDay > 0 && testDay <= daysPerMonth[month])
            return testDay;
        if (month == 2 && testDay == 29 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))
            return testDay;
        System.out.printf("Invalid day (%d)set to 1.",testDay);
        return 1;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }


    public String toString(){
        return String.format("%d%d%d",month,day,year);
    }
}