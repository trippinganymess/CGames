package madLibs;

import java.util.Scanner;

public class Helper {

    public static void GetAdjectives(String adjective[], int n, Scanner sc, Validators v) {

        for (int i = 0; i < 3; i++) {
            while (true) {
                System.out.println("Enter the " + i + " adjectives :");
                adjective[i] = sc.next();
                if (!(v.SearchWord(adjective[i], v.useConveter()))) {
                    System.out.println("Not an adjective :");
                }
                else
                {
                    break;
                }
            }
        }
    }

    public static void GetVerbs(String verb[], int n, Scanner sc, Validators v) {
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
        if (cnt != -1) {
            System.out.print(load);
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                if (cnt != -1)
                    System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
