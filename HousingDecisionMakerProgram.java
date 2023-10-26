import java.util.*;


public class HousingDecisionMakerProgram {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int x = 0;
        int year;
        int age;
        int travelingDistance;
        boolean financialAid = false;
        boolean campusJob = false;
        boolean disability = false;
        boolean yearNumber = false;

        System.out.println("enter your class year: \n1 for freshman\n2 for sophomore\n3 for junior\n4 for senior ");
        Scanner scnr = new Scanner(System.in);
        
        year = scnr.nextInt();

       while(yearNumber == false)  // while false, ask the user for their correct grade level.
       {
       
        if (year>=1 && year <=4)
        {

            if (year == 1)
            {
            x += 4;
            yearNumber = true;
            }
            else if (year == 2)
            {
            x+= 3;
            yearNumber = true;
            }
            else if (year == 3)
            {
            x+= 2;
            yearNumber = true;
            }
            else if (year == 4)
            {
            x+=1;
            yearNumber = true;
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
        System.out.println("How far away do you live from campus? Enter a number in miles ");
        travelingDistance = scnr.nextInt();
       

        if(age < 21) {     // add a point if under 21 years of age
            x += 1;
        }
        if(financialAid == true) {  // add a point if they are receiving aid
            x += 1;
        }
        if(campusJob == true) {     // add a point if they have a job on-campus
            x+= 1;
        }
        if(disability == true) {    // add a point if they have a disability
            x+= 1;
        }
        if (travelingDistance>=20 && travelingDistance <= 50) {    // if distance is between or above a range, add points
             x+=1;
        }
        else if (travelingDistance>50) {
             x+=2;
        }
    
        System.out.println("You have " + x + " points");
    scnr.close();

    }


}
