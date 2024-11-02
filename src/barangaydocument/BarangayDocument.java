
package barangaydocument;

import java.util.Scanner;


public class BarangayDocument {

   
    public static void main(String[] args) {
       
         Scanner sc = new Scanner(System.in);
         
         
         
         boolean exit = true;
         do{
        System.out.println("=====================================");
        System.out.println("          Barangay Document         ");
        System.out.println("=====================================");
        System.out.println("1. Citizen Management");
        System.out.println("2. Document Management");
        System.out.println("3. Document Record Management");
        System.out.println("4. Exit");
        System.out.println("=====================================");
        System.out.print("Please select an option (1-4): ");
        
         int choice;
         while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 5.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }
        switch(choice){
            case 1:
                Citizen ct = new Citizen();
             ct.mainCitizen();
             break;
            case 2:
               Documents dt = new Documents();  
               dt.mainDocuments();
                break;
            case 3:
             
                   System.out.print("Are you a Secretary ? Yes or No: ");
                        String resp = sc.next();

                        if (resp.equalsIgnoreCase("yes")) {

                        System.out.print("Enter secretary password: ");
                        String hrPassword = sc.next();

                        final String HR_PASSWORD = "sec2021";


                        if (hrPassword.equals(HR_PASSWORD)) {
                            DocumentRecord dr1 = new DocumentRecord();
                                  dr1.mainRecord();
                        } else {
                            System.out.println("Invalid Secretary password. Access denied.");
                        }
                    } else {
                        System.out.println("You do not have permission to access Secretary-only features.");
                    }
              
                break;
            case 4:
                System.out.print("Are you sure you want to Exit? Yes or No: ");
                String response = sc.next();
                if(response.equalsIgnoreCase("yes")){
                    exit = false;
                }
                break;
        }
         }while(exit);
        System.out.println("Thank You ! ");
    }
    
}
