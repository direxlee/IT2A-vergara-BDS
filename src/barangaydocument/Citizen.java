
package barangaydocument;

import java.util.Scanner;


public class Citizen {
    
    
    public void addCitizen() {
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();

        System.out.print("Enter Citizen First Name: ");
        String firstName = sc.next();
        System.out.print("Enter Citizen Last Name: ");
        String lastName = sc.next();
        sc.nextLine(); // Consume newline
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.next();

        String sql = "INSERT INTO Citizens (First_Name, Last_Name, Address, Phone_Number) VALUES (?, ?, ?, ?)";
        cfg.addCitizen(sql, firstName, lastName, address, phoneNumber);
    }

    public void viewCitizen() {
        String query = "SELECT * FROM Citizens";
        String[] headers = {"Citizen ID", "First Name", "Last Name", "Address", "Phone Number"};
        String[] columns = {"Citizen_ID", "First_Name", "Last_Name", "Address", "Phone_Number"};

        Config cfg = new Config();
        cfg.viewCitizen(query, headers, columns);
    }

    public void mainCitizen() {
        Scanner sc = new Scanner(System.in);
        Config cfg = new Config();
        boolean continueLoop = true;

        do {
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

            switch (choice) {
                case 1:
                    addCitizen();
                    break;
                case 2:
                    viewCitizen();
                    break;
                case 3:
                    viewCitizen();
                    System.out.print("Enter Citizen ID to update: ");
                    int updateId = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Citizen_ID FROM Citizens WHERE Citizen_ID = ?", updateId) != 0) {
                        sc.nextLine(); // Consume newline
                        System.out.print("Enter new Address: ");
                        String newAddress = sc.nextLine();
                        System.out.print("Enter new Phone Number: ");
                        String newPhoneNumber = sc.next();
                        String updateQuery = "UPDATE Citizens SET Address = ?, Phone_Number = ? WHERE Citizen_ID = ?";
                        cfg.updateCitizen(updateQuery, newAddress, newPhoneNumber, updateId);
                    } else {
                        System.out.println("Citizen ID not found.");
                    }
                    break;
                case 4:
                    viewCitizen();
                    System.out.print("Enter Citizen ID to delete: ");
                    int deleteId = sc.nextInt();
                    if (cfg.getSingleValues("SELECT Citizen_ID FROM Citizens WHERE Citizen_ID = ?", deleteId) != 0) {
                        String deleteQuery = "DELETE FROM Citizens WHERE Citizen_ID = ?";
                        cfg.deleteCitizen(deleteQuery, deleteId);
                    } else {
                        System.out.println("Citizen ID not found.");
                    }
                    break;
                case 5:
                    continueLoop = false;
                    break;
            }
        } while (continueLoop);

        System.out.println("Exiting Citizen Management...");
    }
}