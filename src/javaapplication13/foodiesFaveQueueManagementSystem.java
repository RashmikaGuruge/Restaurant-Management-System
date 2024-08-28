package javaapplication13;
import java.io.FileReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.management.Query;
import javax.xml.crypto.Data;

public class foodiesFaveQueueManagementSystem {

    // Viewing all Queues method
    public static int getBurgerCount() {
        int wantBurgerCount = 0;
        System.out.print("\nEnter No of requried burgers: ");
        try {
            wantBurgerCount = Integer.parseInt(scannerObject.next());
        }catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            getBurgerCount();
        }
        return wantBurgerCount;
    }
    
    public static void viewAllQueues() {

        if (FQueues[0].Current_Count == 0 && FQueues[1].Current_Count == 0 && FQueues[2].Current_Count == 0) {
            System.out.println("All Queus are empty\n");
        }

        System.out.println("\n***********");
        System.out.println("*" + " " + "Cashier" + " " + "*");
        System.out.println("***********");

        // Checking Queues
        for (int j = 0; j < 5; j++) {

            String val1;
            if (j < FQueues[0].Fcount) {
                if (FQueues[0].customer[j].firstname.equals("X")) {
                    val1 = "X";
                } else {
                    val1 = "O";
                }
            } else {
                val1 = " ";
            }

            String val2;
            if (j < FQueues[1].Fcount) {
                if (FQueues[1].customer[j].firstname.equals("X")) {
                    val2 = "X";
                } else {
                    val2 = "O";
                }
            } else {
                val2 = " ";
            }

            String val3;
            if (j < FQueues[2].Fcount) {
                if (FQueues[2].customer[j].firstname.equals("X")) {
                    val3 = "X";
                } else {
                    val3 = "O";
                }
            } else {
                val3 = " ";
            }
            System.out.println("\n" + val1 + "   " + val2 + "   " + val3);

        }
    }

    // Method to View All Empty Queues
    public static void viewAllEmptyQueues() {

        System.out.println("\n***********");
        System.out.println("*" + " " + "Cashier" + " " + "*");
        System.out.println("***********");

        if (FQueues[0].Current_Count == 0) {
            System.out.println("Cashier 1 is Empty");
        } else {
            System.out.println("Cashier 1 is Not Empty");
        }
        if (FQueues[1].Current_Count == 0) {
            System.out.println("Cashier 2 is Empty");
        } else {
            System.out.println("Cashier 2 is Not Empty");
        }
        if (FQueues[2].Current_Count == 0) {
            System.out.println("Cashier 3 is Empty");
        } else {
            System.out.println("Cashier 3 is Not Empty");
        }
    }

    // Method to Add Customer to queue
    public static void addCustomerToTheQueues() {
        String customerFName;
        String customerSName;
        int want_Burger_Count = 0;

        System.out.print("\nEnter first name here: ");
        customerFName = scannerObject.next();

        System.out.print("\nEnter second name here: ");
        customerSName = scannerObject.next();

        want_Burger_Count = getBurgerCount();

        if (FQueues[0].Current_Count == 2 && FQueues[1].Current_Count == 3 && FQueues[2].Current_Count == 5) {
            WaitQueues.customer[waitcount].createCustomer(customerFName, customerSName,
                    want_Burger_Count);
            System.out.println(customerFName + " " + customerSName + " added to waiting queue");
            waitcount++;
        } else {
            if (FQueues[0].Current_Count <= FQueues[1].Current_Count
                    && FQueues[0].Current_Count <= FQueues[2].Current_Count && FQueues[0].Current_Count != 2) {

                FQueues[0].customer[FQueues[0].Current_Count].createCustomer(customerFName, customerSName,
                        want_Burger_Count);
                FQueues[0].Current_Count++;
            } else if (FQueues[1].Current_Count <= FQueues[0].Current_Count
                    && FQueues[1].Current_Count <= FQueues[2].Current_Count && FQueues[1].Current_Count != 3) {
                FQueues[1].customer[FQueues[1].Current_Count].createCustomer(customerFName, customerSName,
                        want_Burger_Count);
                FQueues[1].Current_Count++;
            } else {
                FQueues[2].customer[FQueues[2].Current_Count].createCustomer(customerFName, customerSName,
                        want_Burger_Count);
                FQueues[2].Current_Count++;
            }
            System.out.println(customerFName + " " + customerSName + " " + "Added Succesfully");
        }

    }

    // Method to Removecustomer from queue
    public static void removeCustomerFromQueue() {

        System.out.println("Enter Queue here: ");
        int enteredRemoveQueue = scannerObject.nextInt();

        if (enteredRemoveQueue == 1) {
            System.out.println("Enter line number here: ");
            int removeIndex = scannerObject.nextInt();
            if (removeIndex > FQueues[0].Current_Count) {
                System.out.println("Already Empty");
            } else {
                FQueues[0].customer[removeIndex - 1].firstname = "X";
                FQueues[0].customer[removeIndex - 1].lastname = "X";
                int a = removeIndex - 1;
                for (int i = a; i < 1; i++) {
                    FQueues[0].customer[i].firstname = FQueues[0].customer[i + 1].firstname;
                    FQueues[0].customer[i].lastname = FQueues[0].customer[i + 1].lastname;
                }
                FQueues[0].customer[1].firstname = "X";
                FQueues[0].customer[1].lastname = "X";
                FQueues[0].Current_Count--;
            }

        } else if (enteredRemoveQueue == 2) {
            System.out.println("Enter line number here: ");
            int removeIndex = scannerObject.nextInt();
            if (removeIndex > FQueues[1].Current_Count) {
                System.out.println("Already Empty");
            } else {
                FQueues[1].customer[removeIndex - 1].firstname = "X";
                FQueues[1].customer[removeIndex - 1].lastname = "X";
                int a = removeIndex - 1;
                for (int i = a; i < 2; i++) {
                    FQueues[1].customer[i].firstname = FQueues[1].customer[i + 1].firstname;
                    FQueues[1].customer[i].lastname = FQueues[1].customer[i + 1].lastname;
                }
                FQueues[1].customer[2].firstname = "X";
                FQueues[1].customer[2].lastname = "X";
                FQueues[1].Current_Count--;
            }

        } else {
            System.out.println("Enter line number here: ");
            int removeIndex = scannerObject.nextInt();
            if (removeIndex > FQueues[2].Current_Count) {
                System.out.println("Already Empty");
            } else {
                FQueues[2].customer[removeIndex - 1].firstname = "X";
                FQueues[2].customer[removeIndex - 1].lastname = "X";
                int a = removeIndex - 1;
                for (int i = a; i < 4; i++) {
                    FQueues[2].customer[i].firstname = FQueues[2].customer[i + 1].firstname;
                    FQueues[2].customer[i].lastname = FQueues[2].customer[i + 1].lastname;
                }
                FQueues[2].customer[4].firstname = "X";
                FQueues[2].customer[4].lastname = "X";
                FQueues[2].Current_Count--;
            }
        }
    }

    // Method to RemoveServedCustomer
    public static void removeAServedCustomer() {
        System.out.println("Enter the Served custumer Queue: ");
        int enteredRemoveQueue = scannerObject.nextInt();

        if (enteredRemoveQueue == 1) {
            if (FQueues[0].customer[0].firstname != "X") {
                FQueues[0].customer[0].firstname = "X";
                FQueues[0].customer[0].lastname = "X";
                for (int i = 0; i < 1; i++) {
                    FQueues[0].customer[i].firstname = FQueues[0].customer[i + 1].firstname;
                    FQueues[0].customer[i].lastname = FQueues[0].customer[i + 1].lastname;
                }
                FQueues[0].customer[1].firstname = "X";
                FQueues[0].customer[1].lastname = "X";

                if (waitcount != 0) {
                    FQueues[0].customer[FQueues[0].Current_Count - 1] = WaitQueues.customer[0];
                    System.out.println("Customer Added to Queue 1 from watiting Queue");
                    for (int i = 0; i < waitcount - 1; i++) {
                        WaitQueues.customer[i] = WaitQueues.customer[i + 1];
                    }
                    waitcount--;
                    FQueues[0].Current_Count++;
                }

                if (BurgerStock >= FQueues[0].customer[0].Requure_Burger_Count) {
                    FQueues[0].Current_Count--;
                    BurgerStock = BurgerStock - FQueues[0].customer[0].Requure_Burger_Count;
                    if (BurgerStock <= 10) {

                        System.out.println("Burger Stock has reached 10");
                    }
                } else {
                    FQueues[0].Current_Count--;
                    System.out.println("Stock is Empty");
                }

            } else {
                System.out.println("Queue is empty");
            }
        } else if (enteredRemoveQueue == 2) {
            if (FQueues[1].customer[0].firstname != "X") {
                FQueues[1].customer[0].firstname = "X";
                FQueues[1].customer[0].lastname = "X";
                for (int i = 0; i < 2; i++) {
                    FQueues[1].customer[i].firstname = FQueues[1].customer[i + 1].firstname;
                    FQueues[1].customer[i].lastname = FQueues[1].customer[i + 1].lastname;
                }
                FQueues[1].customer[2].firstname = "X";
                FQueues[1].customer[2].lastname = "X";

                if (waitcount != 0) {
                    FQueues[1].customer[FQueues[1].Current_Count - 1] = WaitQueues.customer[0];
                    System.out.println("Customer Added to Queue 1 from watiting Queue");
                    for (int i = 0; i < waitcount - 1; i++) {
                        WaitQueues.customer[i] = WaitQueues.customer[i + 1];
                    }
                    waitcount--;
                    FQueues[1].Current_Count++;
                }

                if (BurgerStock >= FQueues[1].customer[0].Requure_Burger_Count) {
                    FQueues[1].Current_Count--;

                    BurgerStock = BurgerStock - FQueues[1].customer[0].Requure_Burger_Count;
                    if (BurgerStock <= 10) {
                        System.out.println("Burger Stock has reached 10");
                    }
                } else {
                    System.out.println("Stock is Empty");
                    FQueues[1].Current_Count--;
                }

            } else {
                System.out.println("Queue is empty");
            }
        } else {
            if (FQueues[2].customer[0].firstname != "X") {
                FQueues[2].customer[0].firstname = "X";
                FQueues[2].customer[0].lastname = "X";
                for (int i = 0; i < 4; i++) {
                    FQueues[2].customer[i].firstname = FQueues[2].customer[i + 1].firstname;
                    FQueues[2].customer[i].lastname = FQueues[2].customer[i + 1].lastname;
                }
                FQueues[2].customer[4].firstname = "X";
                FQueues[2].customer[4].lastname = "X";

                if (waitcount != 0) {
                    FQueues[2].customer[FQueues[2].Current_Count - 1] = WaitQueues.customer[0];
                    System.out.println("Customer Added to Queue 1 from watiting Queue");
                    for (int i = 0; i < waitcount - 1; i++) {
                        WaitQueues.customer[i] = WaitQueues.customer[i + 1];
                    }
                    waitcount--;
                    FQueues[2].Current_Count++;
                }

                if (BurgerStock >= FQueues[2].customer[0].Requure_Burger_Count) {
                    FQueues[2].Current_Count--;
                    BurgerStock = BurgerStock - FQueues[2].customer[0].Requure_Burger_Count;
                    if (BurgerStock <= 10) {
                        System.out.println("Burger Stock has reached 10");
                    }
                } else {
                    FQueues[2].Current_Count--;
                    System.out.println("Stock is Empty");
                }

            } else {
                System.out.println("Queue is empty");
            }
        }
    }

    // Method to SortAlphabatical order
    public static void customerSortedInAlphabeticalOrder() {

        /*
         * if (cashier01_count == 0) {
         * System.out.println("Cashier 1 is Empty\n");
         * } else {
         * String[] names = cashier01;
         * 
         * int n = names.length;
         * for (int i = 0; i < cashier01_count - 1; i++) {
         * for (int j = 0; j < cashier01_count - i - 1; j++) {
         * if (names[j].compareTo(names[j + 1]) > 0) {
         * String temp = names[j];
         * names[j] = names[j + 1];
         * names[j + 1] = temp;
         * }
         * }
         * }
         * System.out.println("Cashier 1: ");
         * for (int i = 0; i < cashier01_count; i++) {
         * System.out.print(cashier01[i] + " ");
         * }
         * System.out.println("\n");
         * }
         * if (cashier02_count == 0) {
         * System.out.println("Cashier 2 is Empty\n");
         * } else {
         * String[] names = cashier02;
         * 
         * int n = names.length;
         * for (int i = 0; i < n - 1; i++) {
         * for (int j = 0; j < n - i - 1; j++) {
         * if (names[j].compareTo(names[j + 1]) > 0) {
         * String temp = names[j];
         * names[j] = names[j + 1];
         * names[j + 1] = temp;
         * }
         * }
         * }
         * System.out.println("Cashier 2: ");
         * for (int i = 0; i < cashier02_count; i++) {
         * System.out.print(cashier02[i] + " ");
         * }
         * System.out.println("\n");
         * }
         * if (cashier03_count == 0) {
         * System.out.println("Cashier 3 is Empty\n");
         * } else {
         * String[] names = cashier03;
         * 
         * int n = names.length;
         * for (int i = 0; i < cashier03_count - 1; i++) {
         * for (int j = 0; j < cashier03_count - i - 1; j++) {
         * if (names[j].compareTo(names[j + 1]) > 0) {
         * String temp = names[j];
         * names[j] = names[j + 1];
         * names[j + 1] = temp;
         * }
         * }
         * }
         * System.out.println("Cashier 1: ");
         * for (int i = 0; i < cashier03_count; i++) {
         * System.out.print(cashier03[i] + " ");
         * }
         * }
         */
    }

    // Method to StoreProgramDataIntoFile
    public static void storeProgramDataIntoFile() {
        try {
            FileWriter pointer0 = new FileWriter("C:\\Users\\T.P.R.N. GURUGE\\Documents\\NetBeansProjects\\JavaApplication13\\src\\javaapplication13\\Burger.txt");

            pointer0.write("Queue 1");
            pointer0.write("\n");
            for (int i = 0; i < FQueues[0].Current_Count; i++) {
                String firstname = FQueues[0].customer[i].firstname;
                String lastname = FQueues[0].customer[i].lastname;
                String b_count = Integer.toString(FQueues[0].customer[i].Requure_Burger_Count);
                pointer0.write("First name: " + firstname);
                pointer0.write("\n");
                pointer0.write("Last name: " + lastname);
                pointer0.write("\n");
                pointer0.write("Requre Burger Count: " + b_count);
                pointer0.write("\n");
            }
            pointer0.write("\n\n");
            pointer0.write("Queue 2\n");

            for (int i = 0; i < FQueues[1].Current_Count; i++) {
                String firstname = FQueues[1].customer[i].firstname;
                String lastname = FQueues[1].customer[i].lastname;
                String b_count = Integer.toString(FQueues[1].customer[i].Requure_Burger_Count);
                pointer0.write("First name: " + firstname);
                pointer0.write("\n");
                pointer0.write("Last name: " + lastname);
                pointer0.write("\n");
                pointer0.write("Requre Burger Count: " + b_count);
                pointer0.write("\n");
            }
            pointer0.write("\n\n");
            pointer0.write("Queue 3");
            pointer0.write("\n");
            for (int i = 0; i < FQueues[2].Current_Count; i++) {
                String firstname = FQueues[2].customer[i].firstname;
                String lastname = FQueues[2].customer[i].lastname;
                String b_count = Integer.toString(FQueues[2].customer[i].Requure_Burger_Count);
                pointer0.write("First name: " + firstname);
                pointer0.write("\n");
                pointer0.write("Last name: " + lastname);
                pointer0.write("\n");
                pointer0.write("Requre Burger Count: " + b_count);
                pointer0.write("\n");
            }
            pointer0.write("\n\n");
            pointer0.write("Waiting Queue");
            pointer0.write("\n");
            for (int i = 0; i < waitcount; i++) {
                String firstname = WaitQueues.customer[i].firstname;
                String lastname = WaitQueues.customer[i].lastname;
                String b_count = Integer.toString(WaitQueues.customer[i].Requure_Burger_Count);
                pointer0.write("First name: " + firstname);
                pointer0.write("\n");
                pointer0.write("Last name: " + lastname);
                pointer0.write("\n");
                pointer0.write("Requre Burger Count: " + b_count);
                pointer0.write("\n");
            }
            pointer0.write("\n");
            pointer0.write("Burger stock:" + BurgerStock);
            pointer0.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    // Method to LoadProgramDataIntoFile
    public static void loadProgramDataIntoFile() {
        try {
            FileReader pointer = new FileReader(
                    "C:\\Users\\T.P.R.N. GURUGE\\Documents\\NetBeansProjects\\JavaApplication13\\src\\javaapplication13\\Burger.txt");
            Scanner readfile = new Scanner(pointer);
            while (readfile.hasNextLine()) {
                String data = readfile.nextLine();
                System.out.println(data);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    // Method to print the remaining Burger Stock
    public static void viewRemainingBurgerStock() {
        System.out.println("Remaining Burgers: " + BurgerStock);
    }

    // Method to Manipulate Burger Stock
    public static void addBurgersToStock() {
        if (BurgerStock < 50) {
            System.out.print("Enter Value to add to the stock: ");
            int addToTheStock = scannerObject.nextInt();

            int newStock = BurgerStock + addToTheStock;
            if (newStock > 50) {
                BurgerStock = 50;
            } else {
                BurgerStock = newStock;
            }
            System.out.println("Stock Updated");

        } else {
            System.out.println("Burger stock is full");

        }

    }

    public static void printIncome() {
        if (FQueues[0].Current_Count == 2 && FQueues[1].Current_Count == 3 && FQueues[2].Current_Count == 5) {
            System.out.println("All the Queues Are Full");
        } else {
            int price = 0;
            for (int i = 0; i < FQueues[0].Current_Count; i++) {
                price = price + FQueues[0].customer[i].Requure_Burger_Count * 65;
            }
            System.out.println("Income of 1'st Queue :" + price);
            price = 0;
            for (int i = 0; i < FQueues[1].Current_Count; i++) {
                price = price + FQueues[1].customer[i].Requure_Burger_Count * 65;
            }
            System.out.println("Income of 2'st Queue :" + price);
            price = 0;
            for (int i = 0; i < FQueues[1].Current_Count; i++) {
                price = price + FQueues[1].customer[i].Requure_Burger_Count * 65;
            }
            System.out.println("Income of 3'st Queue :" + price);
        }

    }
    
    public static void openGUI() {
        NewJFrame jf = new NewJFrame();
        jf.show();
    }

    // Defining the Global Variables.
    public static int waitcount = 0;
    public static int BurgerStock = 50;
    public static Scanner scannerObject = new Scanner(System.in);
    public static Foodqueues FQueues[] = new Foodqueues[3];
    public static Foodqueues WaitQueues = new Foodqueues(99);

    public static void main(String[] args) {

        String Option = "";
        Boolean A = true;

        FQueues[0] = new Foodqueues(2);
        FQueues[1] = new Foodqueues(3);
        FQueues[2] = new Foodqueues(5);

        // Printing out the Menu
        System.out.println("\n------------------------------------");
        System.out.println("Foodies Fave Food Center");
        System.out.println("------------------------------------\n");
        System.out.println(
                "100 or VFQ: View all Queues\n101 or VEQ: View all Empty Queues.\n102 or ACQ: Add customer to a Queue.\n103 or RCQ: Remove a customer from a Queue.\n104 or PCQ: Remove a served customer.\n105 or VCS: View Customers Sorted in alphabetical order\n106 or SPD: Store Program Data into file.\n107 or LPD: Load Program Data from file.\n108 or STK: View Remaining burgers Stock.\n109 or AFS: Add burgers to Stock.\n110 or IFQ: View the income of the queues\n112 or GUI: Open GUI\n999 or EXT: Exit the Program.");

        // Getting in multiple Inputs using a While Loop
        while (A) {
            System.out.print("\nEnter your Option: ");
            Option = scannerObject.next().toUpperCase();
            switch (Option) {
                case "100":
                case "VFQ":
                    System.out.println("\nView all Queues selected");
                    viewAllQueues();
                    break;
                case "101":
                case "VEQ":
                    System.out.println("\nView all empty selected");
                    viewAllEmptyQueues();
                    break;
                case "102":
                case "ACQ":
                    System.out.println("\nAdd customer to the Queue selected");
                    addCustomerToTheQueues();
                    break;
                case "103":
                case "RCQ":
                    System.out.println("\nRemove customer from Queue selected");
                    removeCustomerFromQueue();
                    break;
                case "104":
                case "PCQ":
                    System.out.println("\nRemove Served Customer");
                    removeAServedCustomer();
                    break;
                case "105":
                case "VCS":
                    customerSortedInAlphabeticalOrder();
                    break;
                case "106":
                case "SPD":
                    storeProgramDataIntoFile();
                    break;
                case "107":
                case "LPD":
                    loadProgramDataIntoFile();
                    break;
                case "108":
                case "STK":
                    viewRemainingBurgerStock();
                    break;
                case "109":
                case "AFS":
                    addBurgersToStock();
                    break;
                case "110":
                case "IFQ":
                    printIncome();
                    break;
                case "112":
                case "GUI":
                    openGUI();
                    break;
                case "999":
                case "EXT":
                    System.out.println("System Quiting....");
                    A = false;
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        }
    }
}

class Customer {
    String firstname = "X";
    String lastname = "X";
    int Requure_Burger_Count = 0;

    void createCustomer(String A, String B, int count) {
        firstname = A;
        lastname = B;
        Requure_Burger_Count = count;
    }
}

class Foodqueues {

    Customer customer[] = new Customer[100];
    int Fcount = 0;
    int Current_Count = 0;

    Foodqueues(int size) {
        Fcount = size;
        for (int i = 0; i < size; i++) {
            customer[i] = new Customer();
        }

    }

}
