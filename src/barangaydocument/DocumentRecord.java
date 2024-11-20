
package barangaydocument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class DocumentRecord {
    
    
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();


        System.out.println(" - Citizen List - ");
        Citizen ct = new Citizen();
        ct.viewCitizen();
        System.out.println(" - Document List - ");
        Documents dt = new Documents();
        dt.viewDocuments();

    
        int citizenId;
        while (true) {
            System.out.print("Enter Citizen ID: ");
            if (sc.hasNextInt()) {
                citizenId = sc.nextInt();
                if (cfg.getSingleValues("SELECT Citizen_ID FROM Citizens WHERE Citizen_ID = ?", citizenId) != 0) {
                    break;
                } else {
                    System.out.println("Selected Citizen doesn't exist.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid Citizen ID.");
                sc.next();
            }
        }
        int documentId;
        while (true) {
            System.out.print("Enter Document ID: ");
            if (sc.hasNextInt()) {
                documentId = sc.nextInt();
                if (cfg.getSingleValues("SELECT Documents_ID FROM Documents WHERE Documents_ID = ?", documentId) != 0) {
                    break;
                } else {
                    System.out.println("Selected Document doesn't exist.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid Document ID.");
                sc.next();
            }
        }


        String feesQuery = "SELECT Fees FROM Documents WHERE Documents_ID = ?";
        double fee = cfg.getSingleValues(feesQuery, documentId);

     
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        double totalFees = fee * quantity;

   
        System.out.println("----------------------------------------------");
        System.out.printf("Total Fees: %.2f\n", totalFees);
        System.out.println("----------------------------------------------");

        int cash;
        while (true) {
            System.out.print("Enter Cash: ");
            cash = sc.nextInt();
            if (cash >= totalFees) {
                break;
            } else {
                System.out.println("Cash is not enough. Please try again.");
            }
        }
        double change = cash - totalFees;
        System.out.printf("Change: %.2f\n", change);

    
        String validityQuery = "SELECT Validity FROM Documents WHERE Documents_ID = ?";
        String validity = cfg.getSingleVal(validityQuery, documentId);

      
        sc.nextLine(); 
        System.out.print("Enter Purpose: ");
        String purpose = sc.nextLine();

     
        String addQuery = "INSERT INTO DocumentRecord (Citizen_ID, Documents_ID, Date, Quantity, Fees, Cash, Change, Validity, Purpose) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        cfg.addCitizen(addQuery, citizenId, documentId, date, quantity, totalFees, cash, change, validity, purpose);
    }

    public void viewRecord() {
        String query = "SELECT DocumentRecord.Record_ID, Citizens.First_Name, Citizens.Last_Name, Citizens.Address, " +
                       "Documents.Document_Type, Documents.Validity, DocumentRecord.Date, DocumentRecord.Quantity, DocumentRecord.Fees, " +
                       "DocumentRecord.Purpose " +
                       "FROM DocumentRecord " +
                       "LEFT JOIN Citizens ON Citizens.Citizen_ID = DocumentRecord.Citizen_ID " +
                       "LEFT JOIN Documents ON Documents.Documents_ID = DocumentRecord.Documents_ID";

        String[] headers = {"Record ID", "First Name", "Last Name", "Address", "Document", "Validity", "Date", "Quantity", "Fees", "Purpose"};
        String[] columns = {"Record_ID", "First_Name", "Last_Name", "Address", "Document_Type", "Validity", "Date", "Quantity", "Fees", "Purpose"};

        Config cfg = new Config();
        cfg.viewCitizen(query, headers, columns);
    }

    public void mainRecord() {
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("- - - - - - - - - - - - - - - - - - - -");
            System.out.println("           Record Document           ");
            System.out.println("- - - - - - - - - - - - - - - - - - - -");
            System.out.println("1. Add Record");
            System.out.println("2. View Record");
            System.out.println("3. Update Purpose");
            System.out.println("4. Delete Record");
            System.out.println("5. Exit");
            System.out.println("- - - - - - - - - - - - - - - - - - - -");
            System.out.print("Please select an option (1-5): ");

            int choice;
            while (true) {
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

            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    viewRecord();
                    break;
                case 3:
                    viewRecord();
                    System.out.print("Enter Record ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine(); 
                    if (cfg.getSingleValues("SELECT Record_ID FROM DocumentRecord WHERE Record_ID = ?", updateId) != 0) {
                        System.out.print("Enter new Purpose: ");
                        String newPurpose = sc.nextLine();
                        String updateQuery = "UPDATE DocumentRecord SET Purpose = ? WHERE Record_ID = ?";
                        cfg.updateCitizen(updateQuery, newPurpose, updateId);
                    } else {
                        System.out.println("Record ID not found.");
                    }
                    break;
                case 4:
                    viewRecord();
                    System.out.print("Enter Record ID to delete: ");
                    int deleteId = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Record_ID FROM DocumentRecord WHERE Record_ID = ?", deleteId) != 0) {
                        String deleteQuery = "DELETE FROM DocumentRecord WHERE Record_ID = ?";
                        cfg.deleteCitizen(deleteQuery, deleteId);
                    } else {
                        System.out.println("Record ID not found.");
                    }
                    break;
                case 5:
                    continueLoop = false;
                    break;
            }
        }
        System.out.println("Exiting Record Management...");
    }
}
