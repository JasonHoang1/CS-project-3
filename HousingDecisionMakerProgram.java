import java.util.*;


public class HousingDecisionMakerProgram {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int x = 0;
        int year;
        int age;
        boolean financialAid = false;
        boolean campusJob = false;
        boolean disability = false;
        boolean probation = false;
        boolean suspension = false;
        boolean example = false;

        System.out.println("enter your class year: \n1 for freshman\n2 for sophomore\n3 for junior\n4 for senior ");
        Scanner scnr = new Scanner(System.in);
        
        year = scnr.nextInt();

       while(example == false)  // while false, ask the user for their correct input.
       {
       
        if (year>=1 && year <=4)
        {

            if (year == 1)
            {
            x += 1;
            example = true;
            }
            else if (year == 2)
            {
            x+= 2;
            example = true;
            }
            else if (year == 3)
            {
            x+= 3;
            example = true;
            }
            else if (year == 4)
            {
            x+=4;
            example = true;
            }
        }
        else
        {
System.out.println("enter your corrected class year: \n1 for freshman\n2 for sophomore\n3 for junior\n4 for senior ");
         year = scnr.nextInt();
        }
    }

        System.out.println("enter your age");
        age = scnr.nextInt();
        System.out.println("Do you have financial aid? Enter true or false");
        financialAid = scnr.nextBoolean();
        System.out.println("Do you have campus job? Enter true or false");
        campusJob = scnr.nextBoolean();
        System.out.println("Do you have a disability? Enter true or false ");
        disability = scnr.nextBoolean();
        System.out.println("Are you on academic probation? Enter true or false ");
        probation = scnr.nextBoolean();
        System.out.println("Are you on academic suspension? Enter true or false ");
        suspension = scnr.nextBoolean();

        if(age < 21) {
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

        if(probation == true){
            x -=1;
        }
        if(suspension == true){
            x -= 2;
        } 

    System.out.println("You have " + x + " points");
    scnr.close();










    }


}
