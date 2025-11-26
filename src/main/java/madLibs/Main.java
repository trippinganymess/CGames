package madLibs;

import java.util.Scanner;
import java.util.Random;
import madLibs.Helper;

public class Main {
     public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String adjective[] = new String[3];
        String verb[] = new String[3];
        Random ran = new Random();
        int random = 0;
        int cnt = 0;
        while (true) {
            int play = 1;
            System.out.println("Enter 1 to start, and 0 to exit. ");
            play = sc.nextInt(); 
            if (play == 0) {
                break;
            }
            System.out.println("Enter a noun : ");
            String noun = sc.next();
            Helper.GetAdjectives(adjective, 3, sc);
            Helper.GetVerbs(verb, 3, sc);
            Helper.Loading(cnt);
            random = ran.nextInt(0, 25);
            Engine.runEngine(adjective, verb, noun, random);
            Helper.Loading(-1);
            cnt++;
        }
    }
    
}
