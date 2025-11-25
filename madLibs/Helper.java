package madLibs;
import madLibs.Engine;
import java.util.Scanner;

public class Helper {
   

    public static void GetAdjectives(String adjective[], int n, Scanner sc) {

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter the " + i + " adjectives :");
            adjective[i] = sc.next();
        }
    }

    public static void GetVerbs(String verb[], int n, Scanner sc) {
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter the " + i + " verb:");
            verb[i] = sc.next();
        }
    }

    public static void Loading(int cnt) {
        String load = "Loading";
        if (cnt > 0) {
            load = "reloading";
        }
        System.out.print(load);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
