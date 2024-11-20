
package barangaydocument;

import java.util.Scanner;


public class BarangayDocument {

   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;

        do {
            System.out.println("- - - - - - - - - - - - - - - - - - - -");
            System.out.println("    Barangay Document System         ");
            System.out.println("- - - - - - - - - - - - - - - - - - - -");
            System.out.println("1. Citizen ");
            System.out.println("2. Document ");
            System.out.println("3. Document Record ");
            System.out.println("4. Exit");
            System.out.println("- - - - - - - - - - - - - - - - - - - -");
            System.out.print("Please select an option (1-4): ");

            int choice;
            while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Please enter a number between 1 and 4.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); 
                }
            }

            if (choice == 1) {
                Citizen ct = new Citizen();
                ct.mainCitizen();
            } else if (choice == 2) {
                Documents dt = new Documents();
                dt.mainDocuments();
            } else if (choice == 3) {
                System.out.println("Accessing Document Record Management...");
                DocumentRecord dr = new DocumentRecord();
                dr.mainRecord();
            } else if (choice == 4) {
                System.out.print("Are you sure you want to exit? Yes or No: ");
                String response = sc.next();
                if (response.equalsIgnoreCase("yes")) {
                    exit = false;
                }
            }
        } while (exit);

        System.out.println("Thank you!");
    }
}