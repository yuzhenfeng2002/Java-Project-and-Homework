import java.util.Scanner;

public class Main {
    private static int walkOnStep(int numOfStep) {
        int n1 = 1, n2 = 2, temp = n2;
        // f(n) = f(n - 1) + f(n - 2)
        if (numOfStep == 1) {
            return 1;
        }
        else if (numOfStep == 2) {
            return 2;
        }
        else {
            for (int i = 3; i <= numOfStep; i = i + 1) {
                temp = n2;
                n2 = n2 + n1;
                n1 = temp;
                // Code below is for Question 2
                // System.out.println(i + ": " + n2);
            }
            return n2;
        }
    }

    public static void main(String[] args) {
        int n = 24;
        // Code below is for Question 3
        try {
            if (args.length == 0) {
                System.out.print("Please input the number of steps: ");
                Scanner in = new Scanner(System.in);
                n = in.nextInt();
            }
            else {
                n = Integer.parseInt(args[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        int num = Main.walkOnStep(n);
        System.out.println(num);
    }
}
