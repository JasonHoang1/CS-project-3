import java.util.*;

public class HousingDecisionMaker {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int x = 0;
        int year;
        int age;
        boolean financialAid = false;
        boolean campusJob = false;
        boolean disability = false;

        System.out.println("enter your class year: \n1 for freshman\n2 for sophomore\n3 for junior\n4 for senior ");
        Scanner scnr = new Scanner(System.in);
        year = scnr.nextInt();
        System.out.println("enter your age");
        age = scnr.nextInt();
        System.out.println("Do you have financial aid? Enter true or false");
        financialAid = scnr.nextBoolean();
        System.out.println("Do you have campus job? Enter true or false");
        campusJob = scnr.nextBoolean();
        System.out.println("Do you have a disability? Enter true or false ");
        disability = scnr.nextBoolean();

        switch (year) {

            case 1:
                x += 1;
                break;
            case 2:
                x += 2;
                break;
            case 3: 
                x += 3;
                break;
            case 4: 
                x += 4;
                break;
            default:
                x = 0;
                break;

        }

        if(age > 20) {
            x += 1;
        }
        if(financialAid == true) {
            x += 1;
        }


        if(campusJob == true) {
            x+= 1;
        }

        if(disability == true) {
            x+= 1;
        }

        System.out.println(x + " points");
scnr.close();










    }


}