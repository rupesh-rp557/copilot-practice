“Write a Python program that takes an integer as input and prints its factorial using a function.”
public class Factorial {
    static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = sc.nextInt();
        System.out.println("Factorial of " + num + " is: " + factorial(num));
    }
}