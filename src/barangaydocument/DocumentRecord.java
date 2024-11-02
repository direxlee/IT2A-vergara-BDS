
package barangaydocument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class DocumentRecord {
    
    
    public void addRecord(){
                Scanner sc = new Scanner(System.in);
                 Config cfg = new Config();
                 
        System.out.println(" - Citizen List - ");
         Citizen ct = new Citizen();
         ct.viewCitizen();
         System.out.println(" - Document List - ");
          Documents dt = new Documents();    
        dt.viewDocuments();
        
       
        
         int id;
                while (true) {
                System.out.print("Enter Citizen ID: ");
                if (sc.hasNextInt()) {
                    id = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Citizen_ID  FROM Citizens  WHERE Citizen_ID = ?", id) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Citizen doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Citizen ID.");
                    sc.next(); 
                }
            }
        
         int id2;
                while (true) {
                System.out.print("Enter Document  ID: ");
                if (sc.hasNextInt()) {
                    id2 = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Documents_ID  FROM Documents  WHERE Documents_ID = ?", id2) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Document doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Document ID.");
                    sc.next(); 
                }
            }
        
        
         sc.nextLine();
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);

                    String fees = "SELECT Fees FROM Documents WHERE Documents_ID = ?";
            Double fee = cfg.getSingleValues(fees, id2);
            if (fee == null) {
                System.out.println("Error: Could not retrieve fee for Document ID " + id2);
                return; // Exit the method or handle the error
            }

            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            double pay = fee * quantity;

            System.out.println("\n----------------------------------------------");
            System.out.printf("Total Fees: %.2f\n", pay);
            System.out.println("----------------------------------------------");

            // Input cash and calculate change
            System.out.print("Enter Cash: ");
            int cash = sc.nextInt();
            while (cash < pay) {
                System.out.print("Cash is not enough, Please try again: ");
                cash = sc.nextInt();
            }

                double change = cash - pay;
                System.out.printf("Change: %.2f\n", change);
        
        String valid = "SELECT Validity FROM Documents WHERE Documents_ID = ?";
        String validity = cfg.getSingleVal(valid, id2);
       sc.nextLine();
        System.out.print("Purpose: ");
        String purpose  = sc.next();

        String records  = "INSERT INTO DocumentRecord (Citizen_ID, Documents_ID, Date, Quantity, Fees, Cash, Change, Validity, Purpose) VALUES (?,?,?,?,?,?,?,?,?)";
        
        cfg.addCitizen(records, id, id2,date,quantity,pay,cash,change,validity,purpose);
              
        
    }
    
   public void viewRecord() {
   
    String view = "SELECT DocumentRecord.Record_ID, Citizens.First_Name, Citizens.Last_Name, Citizens.Address, " +
                  "Documents.Document_Type, Documents.Validity, DocumentRecord.Date, DocumentRecord.Quantity, DocumentRecord.Fees, " +
                  "DocumentRecord.Purpose " +  // Added Purpose here
                  "FROM DocumentRecord " +
                  "LEFT JOIN Citizens ON Citizens.Citizen_ID = DocumentRecord.Citizen_ID " +
                  "LEFT JOIN Documents ON Documents.Documents_ID = DocumentRecord.Documents_ID";
    
    String[] head = {"Record ID", "First Name", "Last Name", "Address", "Document", "Validity (MONTHS)", "Date", "Quantity", "Fees", "Purpose"};
    String[] col = {"Record_ID", "First_Name", "Last_Name", "Address", "Document_Type", "Validity", "Date", "Quantity", "Fees", "Purpose"};
    
    Config cfg = new Config();
    cfg.viewCitizen(view, head, col);
}

     
    
    public void mainRecord(){
        
        DocumentRecord dr = new DocumentRecord();
        
           Scanner sc = new Scanner(System.in);
                 Config cfg = new Config();

        String res;
        do{
        System.out.println("=====================================");
        System.out.println("           Record Management        ");
        System.out.println("=====================================");
        System.out.println("1. Add Record");
        System.out.println("2. View Record");
        System.out.println("3. Update Record");
        System.out.println("4. Delete Record");
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
                dr.addRecord();
                break;
            case 2:
                dr.viewRecord();
                break;
            case 3:
                  dr.viewRecord();
                String up = "UPDATE DocumentRecord SET Purpose  = ? WHERE Record_ID = ?";
                
               
                int up1;
                while (true) {
                System.out.print("Enter  Document Record ID to update: ");
                if (sc.hasNextInt()) {
                    up1 = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Record_ID  FROM DocumentRecord WHERE Record_ID = ?", up1) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Document Record ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Document Record ID ID.");
                    sc.next(); 
                }
            }
                
                
                
                
                
                sc.nextLine();
                
                
                
                
                System.out.print("Enter new Purposes: ");
                String newpur = sc.next();
                cfg.updateCitizen(up, newpur,up1);
                break;
            case 4:
               String del = "DELETE FROM DocumentRecord WHERE Record_ID = ? ";
               
                
                int del2;
                while (true) {
                System.out.print("Enter  Document Record ID  to delete: ");
                if (sc.hasNextInt()) {
                    del2 = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Record_ID  FROM DocumentRecord WHERE Record_ID = ?", del2) != 0) {
                        break;
                    } else {
                        System.out.println("Selected Document Record ID doesn't exist.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a integer Document Record ID ID.");
                    sc.next(); 
                }
            }
                
                cfg.deleteCitizen(del, del2);
                break;
            case 5:
                System.out.println("Exiting........");
                
                break;
        }
            System.out.println("");
            System.out.print("Do you want to continue? Yes or No: ");
            res = sc.next();
        }while(res.equalsIgnoreCase("yes"));
        
        
    }
    
    
    
    
    
    
    
    
    
    
}
