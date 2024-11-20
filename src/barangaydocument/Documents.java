
package barangaydocument;

import java.util.Scanner;


public class Documents {
    
   public void addDocuments(){
        
         
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();
        
        System.out.print("Enter name of Document: ");
        String docu = sc.nextLine();
        System.out.print("Validity Period: ");
        String val = sc.nextLine();
        System.out.print("Fees: ");
        double fees = sc.nextDouble();
        sc.nextLine();
        System.out.print("Purposes: ");
        String pur = sc.nextLine();
      
        String sqldocu = "INSERT INTO Documents (Document_Type, Validity, Fees, Purposes) VALUES (?,?,?,?)";
        
        cfg.addCitizen(sqldocu, docu, val, fees, pur);
    }
    public void viewDocuments(){
        
          
         String qry = "SELECT * FROM Documents";
        String[] hdrs = {"Document ID", "Type", "Validity", "Fees", "Purposes"};
        String[] clmns = {"Documents_ID", "Document_Type", "Validity", "Fees", "Purposes"};
        
        Config cfg = new Config();
        cfg.viewCitizen(qry, hdrs, clmns);
        
        
    }
    
    public void mainDocuments(){
        
        Documents dt = new Documents();       
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();
        
        
        String res;
        do{
       System.out.println("=====================================");
        System.out.println("         Document Management        ");
        System.out.println("=====================================");
        System.out.println("1. Add Document");
        System.out.println("2. View Documents");
        System.out.println("3. Update Document");
        System.out.println("4. Delete Document");
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
                dt.addDocuments();
                break;
            case 2:
                dt.viewDocuments();
                break;
            case 3:
                 dt.viewDocuments();
                 String dels = "UPDATE Documents SET Validity = ?, Fees = ?, Purposes = ? WHERE Documents_ID = ?";
                
                   int dels1;
                while (true) {
                System.out.print("Enter Document  ID to update : ");
                if (sc.hasNextInt()) {
                    dels1 = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Documents_ID  FROM Documents  WHERE Documents_ID = ?", dels1) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Document doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Document ID.");
                    sc.next(); 
                }
            }
                 System.out.print("Enter new Validity: ");
                 String newval = sc.next();
                 sc.nextLine();
                 System.out.print("Enter new Fees: ");
                 double newfees = sc.nextDouble();
                 System.out.print("Enter new Purposes: ");
                 String newpur = sc.next();
                 
                 cfg.updateCitizen(dels, newval, newfees, newpur,dels1);
                 
                  break;
            case 4:
                 dt.viewDocuments();
            
                 String dlete = "DELETE FROM Documents WHERE Documents_ID = ?";
                 
                 
                   int delete;
                while (true) {
                System.out.print("Enter Document  ID to delete : ");
                if (sc.hasNextInt()) {
                    delete = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Documents_ID  FROM Documents  WHERE Documents_ID = ?", delete) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Document doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Document ID.");
                    sc.next(); 
                }
            }
                 cfg.deleteCitizen(dlete, delete);
                
                break;
            case 5:
                System.out.println("Exiting.......");
            break;
                
        }
        System.out.println("");
        System.out.print("Do you want to continue? Yes or No: ");
        res = sc.next();
    }while(res.equalsIgnoreCase("yes"));
        
        
        
        
    }
    
    
    
}
     
    
    

