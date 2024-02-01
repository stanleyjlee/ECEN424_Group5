import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Calculator mycalc = new Calculator();
        mycalc.setName("Group 5");
        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");

        boolean check = true;

        while(check){
            System.out.print("Enter A to Add, S to Subtract , M to Multiply , and Q to quit. ");

            Scanner s = new Scanner(System.in);
            String letter = s.next();

            String A, B;
            Float A1 = null, B1 = null;

            switch (letter){
                case "A":
                    System.out.print("Enter argument 1 ");
                    A = s.next();
                    try {
                        A1 = Float.parseFloat(A);
                    } catch (NumberFormatException e) {
                        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
                        break;
                    }
                    System.out.print("Enter argument 2 ");
                    B = s.next();
                    try {
                        B1 = Float.parseFloat(B);
                    } catch (NumberFormatException e) {
                        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
                        break;
                    }
                    Float sum = mycalc.addition(A1, B1);
                    System.out.println("The sum of " + A1 + " and " + B1 + " is " + sum);
                    break;
                case "S":
                    System.out.print("Enter argument 1 ");
                    A = s.next();
                    try {
                        A1 = Float.parseFloat(A);
                    } catch (NumberFormatException e) {
                        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
                        break;
                    }
                    System.out.print("Enter argument 2 ");
                    B = s.next();
                    try {
                        B1 = Float.parseFloat(B);
                    } catch (NumberFormatException e) {
                        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
                        break;
                    }
                    Float diff = mycalc.subtraction(A1, B1);
                    System.out.println("The difference of " + A1 + " and " + B1 + " is " + diff);
                    break;
                case "M":
                    System.out.print("Enter argument 1 ");
                    A = s.next();
                    try {
                        A1 = Float.parseFloat(A);
                    } catch (NumberFormatException e) {
                        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
                        break;
                    }
                    System.out.print("Enter argument 2 ");
                    B = s.next();
                    try {
                        B1 = Float.parseFloat(B);
                    } catch (NumberFormatException e) {
                        System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
                        break;
                    }
                    Float prod = mycalc.multiplication(A1, B1);
                    System.out.println("The product of " + A1 + " and " + B1 + " is " + prod);
                    break;
                case "Q":
                    System.out.print("Quitting Calculator! ");
                    check = false;
                    break;
                default:
                    System.out.println("Welcome to the Calculator designed by " + mycalc.getName() + ".");
            }
        }
    }
}