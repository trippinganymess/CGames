package madLibs;

import madLibs.Helper;
import java.util.Scanner;

public class Main {
     public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String adjective[] = new String[3];
        String verb[] = new String[3];
        int cnt = 0;
        while (true) {
            int play = 1;
            System.out.println("Enter 1 to start, and 0 to exit. ");
            Helper.Loading(cnt);
            play = sc.nextInt();
            if (play == 0) {
                break;
            }
            Helper.GetAdjectives(adjective, 3, sc);
            Helper.GetVerbs(verb, 3, sc);
            Engine.runEngine(adjective, verb, cnt);
            cnt++;
        }
    }
    
}
