import java.util.Scanner;
import java.time.Year;

public class App {
  static String[][] PcComponents = new String[10][4];
  static String[] employees = new String[10];
  static int pheriperalCode = (Year.now().getValue() * 10000);
  static int numberOfItems = 0;
  static int numberOfEmployee = 0;
  private static Scanner in = new Scanner(System.in);

  public static void main(String[] args) {
    main: while (true) {
      String option = selectOption();

      switch (option) {
        case "0":
          break main;
        case "1":
          AddPheriperal();
          break;
        case "2":
          AddEmployee();
          break;
        case "3": // later
          break;
        case "4":
          break;
        default:
          System.out.println("PRINT A VALID OPTION!");
          continue;
      }

    }

    for (int i = 0; i < numberOfItems; i++) {
      System.out.println(PcComponents[i][0]);
    }

    for (int i = 0; i < numberOfEmployee; i++) {
      System.out.println(employees[i]);
    }

    in.close();
  }

  static String selectOption() {
    System.out.println("""
-----------------------------------------------------
Select Your Option:

| System Options |
  0. Exit

| ADD RECORD |
  1. Add pheriperal
  2. Add employee
  3. Assign pheriperal to employee

| VIEW RECORD |
  4. View all peripherals
  5. View available peripherals
  6. View not available peripherals
  7. View all input devices
  8. View all output devices
  9. View all input/output devices
  10.View all employees

| EDIT RECORD |
  11. Edit peripheral
  12. Edit employee

| DELETE RECORD |
  13. Delete peripheral
  14. Delete employee

| REPORT RECORD |
  15. Number of available peripherals
  16. Number of assigned peripherals
  17. Number of input device
  18. Number of output device
  19. Number of employees with peripherals assigned
-----------------------------------------------------
""");

    String decision = in.next();
    return decision;
  }

  static void AddPheriperal() {
    System.out.print("Enter Pheriperal Name: ");
    String newPheriperal = in.next();

    System.out.print("Enter Pheriperal Type: ");
    String PheriperalType = in.next();

    if (!(isArrayStateFull(PcComponents.length, numberOfItems))) {
      for (int i = 0; i < PcComponents.length; i++) {
        if (PcComponents[i][0] == null) {
          PcComponents[i][0] = newPheriperal;
          PcComponents[i][1] = "" + pheriperalCode;
          PcComponents[i][2] = PheriperalType;

          numberOfItems++;
          break;
        }
      }
    }

  }

  static void AddEmployee() {
    System.out.print("Enter Employee Name: ");
    String newEmployee = in.next();

    if (!(isArrayStateFull(employees.length, numberOfEmployee))) {
      for (int i = 0; i < employees.length; i++) {
        if (employees[i] == null) {
          employees[i] = newEmployee; 

          numberOfEmployee++;
          break;
        }
      }
    }
  }

  static boolean isArrayStateFull(int arrayLength, int size) {
    /* Check if array is full */
    if (size >= arrayLength) {
      System.out.println("\nPlease delete an item before entering new ones.\n");
      return true;
    }
    return false;
  }
}
