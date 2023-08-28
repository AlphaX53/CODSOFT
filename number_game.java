import java.util.Random;
import java.util.Scanner;

public class number_game {
    public static void main(String[] args) {
       
        int round = 1;
        int won_rounds = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            int tries = 0;
            System.out.println("Enter the no of tries");
            int no_of_tries = sc.nextInt();
            Random rand = new Random();
            System.out.println("round:- " + round);
            while (tries < no_of_tries) {
                System.out.println("Enter your desired no between 1 - 100");
                int num = sc.nextInt();
                int random = rand.nextInt(101);
                if (random == num) {
                    System.out.println("yay you got it in " + tries + " tries");
                    won_rounds += 1;
                    break;
                } else {
                    System.out.println(
                            "sry you did not get it..your input is " + num + " and the random int is " + random);
                    System.out.println("Please try again");
                }
                tries += 1;
                if (tries >= no_of_tries) {
                    System.out.println("Sorry, you've reached the maximum number of tries. " + tries);
                }

                // prints a random Int between 0 - 4 (including 0 & 4);
            }
            System.out.println("Do you want to play again(y/n):- ");
            String playagain = sc.next().toLowerCase();
            if(!playagain.equals("y")){
                System.out.println("Total rounds won = "+won_rounds);
                break;
            }
            round+=1;
            

        }

    }
}