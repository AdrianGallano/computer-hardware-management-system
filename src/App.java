import java.util.Scanner;
import java.time.Year;

public class App {
  static String[][] PcComponents = new String[10][4];
  static String[] employees = new String[10];
  static int pheriperalCode = (Year.now().getValue() * 10000) + 1;
  static int numberOfPheriperal = 0;
  static int numberOfEmployee = 0;
  static int generalNumberCounter = 0;
  private static Scanner in = new Scanner(System.in);
  static String password = null;

  public static void main(String[] args) {
    setPassword();
    main: while (true) {
      String option = selectOption();

      switch (option) {
        case "0":
          break main;
        case "1":
          addPheriperal();
          break;
        case "2":
          addEmployee();
          break;
        case "3": // later
          assignPheriperalToEmployee();
          break;
        case "4":
          viewAllPheriperals();
          break;
        case "5":
          viewAvailablePheriperal();
          break;
        case "6":
          viewNotAvailablePheriperal();
          break;
        case "7":
          viewInputPheriperal();
          break;
        case "8":
          viewOutputPheriperal();
          break;
        case "9":
          viewInputAndOutputPheriperal();
          break;
        case "10":
          viewAllEmployee();
          break;
        case "11":
          editPheriperal();
          break;
        case "12":
          editEmployee();
          break;
        case "13":
          deletePheriperal();
          break;
        case "14":
          deleteEmployee();
          break;
        case "15":
          numberOfAvailablePheriperal();
          break;
        case "16":
          numberOfAssignedPheriperal();
          break;
        case "17":
          numberOfInputPheriperal();
          break;
        case "18":
          numberOfOutputPheriperal();
          break;
        case "19":
          numberOfEmployeesWithPheriperal();
          break;
        default:
          System.out.println("ENTER A VALID OPTION!");
          continue;
      }

    }

    in.close();
  }

  static void setPassword() {
    while (password == null) {
      System.out.print("Enter your password: ");
      String enterPassword = in.nextLine();

      System.out.print("Confirm your password: ");
      String confirmPassword = in.nextLine();

      if (enterPassword.compareTo(confirmPassword) == 0) {
        password = enterPassword;
      } else {
        System.out.println("Password does not match, please try again.");
      }
    }
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

    System.out.print("Enter your option: ");
    String decision = in.nextLine();
    return decision;
  }

  static void addPheriperal() {
    String[] PheriperalTypeOptions = { "Input", "Output", "Input and Output" };
    System.out.print("Enter Pheriperal Name: ");
    String newPheriperal = in.nextLine();

    System.out.println("-----------------------------------------------------");
    for (int i = 0; i < PheriperalTypeOptions.length; i++) {
      System.out.println((i + 1) + ". " + PheriperalTypeOptions[i]);
    }
    System.out.print("Enter Pheriperal Type: ");
    int PheriperalType = in.nextInt();
    in.nextLine();
    if (!(isArrayStateFull(PcComponents.length, numberOfPheriperal))) {
      for (int i = 0; i < PcComponents.length; i++) {
        if (PcComponents[i][0] == null) {
          PcComponents[i][0] = newPheriperal;
          PcComponents[i][1] = "" + pheriperalCode;
          PcComponents[i][2] = PheriperalTypeOptions[PheriperalType - 1];

          numberOfPheriperal++;
          pheriperalCode++;
          break;
        }
      }
    }
  }

  static void addEmployee() {
    System.out.print("Enter Employee Name: ");
    String newEmployee = in.nextLine();

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

  static void viewAllPheriperals() {
    String[] column = { "Name", "Code", "Type", "Employee Assigned" };
    System.out.print("   ");
    for (int i = 0; i < column.length; i++) {
      System.out.print(column[i] + " ".repeat(20 - column[i].length()));
      System.out.print("|");
    }
    System.out.println("");
    for (int i = 0; i < PcComponents.length; i++) {

      System.out.print(i + 1 + ". ");
      for (int j = 0; j < PcComponents[i].length; j++) {
        if (PcComponents[i][j] != null) {
          System.out.print(PcComponents[i][j] + " ".repeat(20 - PcComponents[i][j].length()));
          System.out.print("|");
        } else {
          System.out.print(PcComponents[i][j] + " ".repeat(16));
          System.out.print("|");
        }
      }
      System.out.println("");
    }
  }

  static void assignPheriperalToEmployee() {

    viewAvailablePheriperal();
    System.out.print("Select Pheriperal:");
    int pheriperalToBeAssigned = in.nextInt();
    viewAllEmployee();
    System.out.println("Select Employee:");
    int employeeToAssign = in.nextInt();
    in.nextLine();
    PcComponents[pheriperalToBeAssigned - 1][3] = employees[employeeToAssign - 1];
  }

  static void viewAllEmployee() {
    System.out.println("---------------------Employees-----------------------");
    for (int i = 0; i < employees.length; i++) {
      System.out.println((i + 1) + ". " + employees[i]);
    }
  }

  static void viewAvailablePheriperal() {
    System.out.println("----------------Available Pheriperals----------------");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][3] == null) {
        System.out.println((i + 1) + ". " + PcComponents[i][0]);
      }
    }
  }

  static void viewNotAvailablePheriperal() {
    System.out.println("------------Not Available Pheriperals----------------");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][3] != null) {
        System.out.println((i + 1) + ". " + PcComponents[i][0]);
      }
    }
  }

  static void viewInputPheriperal() {
    System.out.println("------------Input Pheriperals----------------");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][2] == null)
        continue;
      if (PcComponents[i][2].compareTo("Input") == 0) {
        System.out.println((i + 1) + ". " + PcComponents[i][0]);
      }
    }
  }

  static void viewOutputPheriperal() {
    System.out.println("------------------Output Pheriperals----------------");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][2] == null)
        continue;
      if (PcComponents[i][2].compareTo("Output") == 0) {
        System.out.println((i + 1) + ". " + PcComponents[i][0]);
      }
    }
  }

  static void viewInputAndOutputPheriperal() {
    System.out.println("-------------Input and Output Pheriperals----------------");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][2] == null)
        continue;
      if (PcComponents[i][2].compareTo("Input and Output") == 0) {
        System.out.println((i + 1) + ". " + PcComponents[i][0]);
      }
    }
  }

  static void deletePheriperal() {
    viewAllPheriperals();
    System.out.print("Choose pheriperal to delete: ");
    int pheriperalToDelete = in.nextInt();
    in.nextLine();
    if (PcComponents[pheriperalToDelete - 1][3] == null) {
      PcComponents[pheriperalToDelete - 1][0] = null;
      PcComponents[pheriperalToDelete - 1][1] = null;
      PcComponents[pheriperalToDelete - 1][2] = null;
      numberOfPheriperal--;

    } else {
      System.out.println("Deletion failed, please unassign employee");
    }
  }

  static void deleteEmployee() {
    viewAllEmployee();
    System.out.print("Choose employee to delete: ");
    int employeeToDelete = in.nextInt();
    in.nextLine();
    for (int i = 0; i < employees.length; i++) {
      if (PcComponents[i][3] == null)
        continue;
      if (PcComponents[i][3].compareTo(employees[employeeToDelete - 1]) == 0) {
        PcComponents[i][3] = null;
      }
    }
    employees[employeeToDelete - 1] = null;
    numberOfEmployee--;
  }

  static void numberOfAvailablePheriperal() {
    generalNumberCounter = 0;
    System.out.print("Number Of Available Pheriperals: ");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][0] != null & PcComponents[i][3] == null) {
        generalNumberCounter++;
      }
    }
    System.out.println(generalNumberCounter);
  }

  static void numberOfAssignedPheriperal() {
    generalNumberCounter = 0;
    System.out.print("Number Of Assigned Pheriperals: ");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][0] != null & PcComponents[i][3] != null) {
        generalNumberCounter++;
      }
    }
    System.out.println(generalNumberCounter);
  }

  static void numberOfInputPheriperal() {
    generalNumberCounter = 0;
    System.out.print("Number Of Input Devices: ");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][2] == null)
        continue;
      if (PcComponents[i][0] != null & PcComponents[i][2].compareTo("Input") == 0) {
        generalNumberCounter++;
      }
    }
    System.out.println(generalNumberCounter);
  }

  static void numberOfOutputPheriperal() {
    generalNumberCounter = 0;
    System.out.print("Number Of Input Devices: ");
    for (int i = 0; i < PcComponents.length; i++) {
      if (PcComponents[i][2] == null)
        continue;
      if (PcComponents[i][0] != null & PcComponents[i][2].compareTo("Output") == 0) {
        generalNumberCounter++;
      }
    }
    System.out.println(generalNumberCounter);
  }

  static void numberOfEmployeesWithPheriperal() {
    generalNumberCounter = 0;
    System.out.print("Number Of Employees with Pheriperal: ");
    employeeLoop: for (int i = 0; i < employees.length; i++) {
      for (int j = 0; j < PcComponents.length; j++) {
        if (employees[i] == null || PcComponents[j][3] == null)
          continue;
        if (employees[i].compareTo(PcComponents[j][3]) == 0) {
          generalNumberCounter++;
          continue employeeLoop;
        }
      }
    }
    System.out.println(generalNumberCounter);
  }

  static void editPheriperal() {
    String[] PheriperalTypeOptions = { "Input", "Output", "Input and Output" };

    System.out.println("Password is required for modification: ");
    String promptPassword = in.nextLine();

    if (password.compareTo(promptPassword) == 0) {
      viewAllPheriperals();
      System.out.println("Choose Pheriperal To Edit: ");
      int pheriperalToEdit = in.nextInt();
      in.nextLine();
      System.out.print("Edit Pheriperal Name: ");
      String pheriperalNameEdited = in.next();

      for (int i = 0; i < PheriperalTypeOptions.length; i++) {
        System.out.println((i + 1) + ". " + PheriperalTypeOptions[i]);
      }
      System.out.println("Edit Pheriperal Type: ");
      int pheriperalTypeEdited = in.nextInt();
      in.nextLine();
      viewAllEmployee();
      System.out.println("Edit Pheriperal Employee: ");
      int pheriperalEmployeeEdited = in.nextInt();
      in.nextLine();
      PcComponents[pheriperalToEdit - 1][0] = pheriperalNameEdited;
      PcComponents[pheriperalToEdit - 1][2] = PheriperalTypeOptions[pheriperalTypeEdited - 1];
      PcComponents[pheriperalToEdit - 1][3] = employees[pheriperalEmployeeEdited - 1];
    } else {
      System.out.println("Incorrect password, modification failed");
    }
  }

  static void editEmployee() {
    System.out.println("Password is required for modification: ");
    String promptPassword = in.nextLine();

    if (password.compareTo(promptPassword) == 0) {
      viewAllEmployee();
      System.out.println("Enter Employee To Edit: ");
      int employeeToEdit = in.nextInt();
      in.nextLine();

      System.out.println("Edit Name: ");
      String employeeNameEdited = in.nextLine();

      employees[employeeToEdit - 1] = employeeNameEdited;
    } else {
      System.out.println("Incorrect password, modification failed");
    }
  }
}
