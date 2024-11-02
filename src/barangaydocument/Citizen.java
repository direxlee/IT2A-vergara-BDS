
package barangaydocument;

import java.util.Scanner;


public class Citizen {
    
    
    public void addCitizen(){
        
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();
        
        System.out.print("Enter Citizen First Name: ");
        String cname = sc.next();
        System.out.print("Enter Citizen Last Name: ");
        String cname1 = sc.next();
        System.out.print("Gender: ");
        String gender = sc.next();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Address: ");
        String address = sc.nextLine();
        System.out.print("Phone Number: ");
        String num = sc.next();
        System.out.print("Email: ");
        String email = sc.next();
        
        String citizen = "INSERT INTO Citizens(First_Name, Last_Name, Gender, Age, Address, Phone_Number, Email) VALUES (?,?,?,?,?,?,?)";
        cfg.addCitizen(citizen, cname, cname1, gender, age, address,num, email);
    }
    public void viewCitizen(){
        
        
         String qry = "SELECT * FROM Citizens";
        String[] hdrs = {"Citizen ID", "First Name", "Last Name", "Gender", "Age", "Address","Phone Number", "Email"};
        String[] clmns = {"Citizen_ID", "First_Name", "Last_Name", "Gender", "Age", "Address", "Phone_Number", "Email"};
        
     Config cfg = new Config();
        cfg.viewCitizen(qry, hdrs, clmns);
    }
    
    public void mainCitizen(){
        
          Citizen ct = new Citizen();
          Scanner sc = new Scanner(System.in);
          Config cfg = new Config();
        
        String res;
        do{
        System.out.println("=====================================");
    System.out.println("          Citizen Management         ");
    System.out.println("=====================================");
    System.out.println("1. Add Citizen");
    System.out.println("2. View Citizens");
    System.out.println("3. Update Citizen");
    System.out.println("4. Delete Citizen");
    System.out.println("5. Exit");
    System.out.println("=====================================");
    System.out.print("Please select an option (1-5): ");
        
         int choice;
         while (true) {
                System.out.print("Enter choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    if (choice >= 1 && choice <= 5) {
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
                 ct.addCitizen();
                 break;
             case 2:
                 ct.viewCitizen();
                 break;
             case 3:
                                  ct.viewCitizen();

                 String dels = "UPDATE Citizens SET Age = ?, Address = ?, Phone_Number = ?, Email  = ? WHERE Citizen_ID = ?";
                 
                 
         int dels1;
                while (true) {
                System.out.print("Enter Citizen ID to update: ");
                if (sc.hasNextInt()) {
                    dels1 = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Citizen_ID  FROM Citizens  WHERE Citizen_ID = ?", dels1) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Citizen doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Citizen ID.");
                    sc.next(); 
                }
            }
                 System.out.print("Enter new Age: ");
                 int newage = sc.nextInt();
                 System.out.print("Enter new Address: ");
                 String newadds = sc.next();
                 System.out.print("Enter new Phone Number: ");
                 String newnum = sc.next();
                 System.out.print("Enter new Email: ");
                 String newem = sc.next();
                 
                 cfg.updateCitizen(dels, newage, newadds, newnum, newem,dels1);
                 break;
             case 4:
                                  ct.viewCitizen();

                 String dlete = "DELETE FROM Citizens WHERE Citizen_ID = ?";
                 
               
                 
                        
         int delete;
                while (true) {
                System.out.print("Enter Citizen ID to delete: ");
                if (sc.hasNextInt()) {
                    delete = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Citizen_ID  FROM Citizens  WHERE Citizen_ID = ?", delete) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Citizen doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Citizen ID.");
                    sc.next(); 
                }
            }
                 
                 
                 cfg.deleteCitizen(dlete, delete);
                 break;
             case 5:
                 System.out.println("Exiting......................../");
                 break;
         }
         System.out.println("");
         System.out.print("Do you want to continue? Yes or No: ");
         res = sc.next();
    }while(res.equalsIgnoreCase("yes"));
        
    }
    
    
    
}
