import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean input = true;

        do{    
            System.out.println("Which program would you like to run?");       // Prompts the user to choose a program to run
            System.out.println("> 0 - Quit");
            System.out.println("> 1 - Assignment Program 1");
            System.out.print("> 2 - Assignment Program 2\n> ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 0){       // Exits the program if the user chose 0
                System.out.println("Goodbye!");
                scanner.close();        // Close the scanner object
                System.exit(0);
            }
            else if (choice == 1){      // Runs Assignment Program 1 if the user chose 1
                AssignmentProgram1 program1 = new AssignmentProgram1();
                program1.run(scanner);
                input = false;
            }
            else if (choice == 2){      // Runs Assignment Program 2 if the user chose 2
                AssignmentProgram2 program2 = new AssignmentProgram2();
                program2.run(scanner);
                input = false;
            }
        }while(input);
    }
}