/*
Name:       Dale Hendricks
Section:    02
Instructor: Sarah Foss
Date:       Sept 26, 2023
Description: Console casino, featuring virtual craps.  Plays an intro animation, then prompts user for
    which game to play (currently craps only).  Select 0 for instructor mode to skip the extra features.
                                                          "Just the lab, please" - LA Codefidential
*/

import java.util.*;
import static java.lang.Math.floor;

public class Codesino {

    //the InterruptedException is required by the Thread.sleep() call in my intro function
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        //Plays the intro graphic, then directs to a menu for game selection.
        //The rest of the logic will be within the functions.
        intro();
        menu(input);
    }

    //Just a little fun with ASCII
    public static void intro() throws InterruptedException {
        final String YELLOW = "\u001B[33m";
        final String RESET = "\u001B[0m";

        String[] title = {"╔══════════════════════════════════════════════════════════════════════════════════════╗",
                "║                   ╔═══  ╔═══╗ ╔═══  ╔═══ ╔════ ═════ ╔╗   ║ ╔═══╗                    ║",
                "║                   ║     ║   ║ ║   ║ ║═══ ╚═══╗   ║   ║ ║  ║ ║   ║                    ║",
                "║                   ║     ║   ║ ║   ║ ║        ║   ║   ║  ║ ║ ║   ║                    ║",
                "║                   ╚═══  ╚═══╝ ╚═══  ╚═══ ════╝ ═════ ║   ╚╝ ╚═══╝                    ║",
                "╚══════════════════════════════════════════════════════════════════════════════════════╝"};
        for (int line = 0; line < 6; line++) {
            System.out.print(YELLOW + title[line] + RESET);
            Thread.sleep(500L);
            System.out.println("\r" + title[line]);
        }
    }

    public static void menu(Scanner input) {
        System.out.println("""
                Howdy there pardner, and welcome to the Codesino!  Where ya headed?
                1 -> Craps table
                2 -> <*CLOSED*>
                3 -> <*UNDER CONSTRUCTION*>2
                4 -> <*OUT OF ORDER*>
                5 -> Exit
                                    
                0 -> Skip the formalities, I'm the instructor and I just want to see the lab
                """);
        switch (input.nextInt()) {
            case 1:
                System.out.println("Well head on over!");
                playCraps(input);
                break;
            case 2:
                System.out.println("Sorry pardner, that's closed!");
                menu(input);
                break;
            case 3:
                System.out.println("Sorry pardner, we're still workin' on that!");
                menu(input);
                break;
            case 4:
                System.out.println("Sorry pardner, that room needs a little more time in the oven!");
                menu(input);
                break;
            case 5:
                System.out.println("So long pardner!");
                break;
            case 0:
                labCraps();
            default:
                System.out.println("Sorry pardner, I didn't quite catch ya there!");
                break;
        }
    }

    //  I condensed the program to be just the required lab stuff here.
    public static void labCraps() {
        int goal = roll() + roll();
        int dieOne = roll();
        int dieTwo = roll();
        int total = dieOne + dieTwo;

        System.out.println("You rolled " + goal);
        if (goal == 7 || goal == 11) {
            System.out.println("You win off the bat!");
            System.exit(0);
        } else if (goal == 2 || goal == 3 || goal == 12) {
            System.out.println("You lose right off!");
            System.exit(0);
        } else {
            System.out.println("Lets play!");
        }

        while (total != 7 && total != goal) {
            System.out.println("You rolled a " + dieOne + " and a " + dieTwo + " for a total of " + total + "!");
            dieOne = roll();
            dieTwo = roll();
            total = dieOne + dieTwo;
        }
        if (total == 7){
            System.out.println("You lose!");
        } else {
            System.out.println("You win!");
        }
    }

    /*  Prompts to see if the player needs instructions, then calls comeOut() to start the game,
        which calls the recursive craps() function that runs until the game resolves.  When the
        code bounces back up here, it prompts the player for another round.  */
    public static void playCraps(Scanner input) {
        final String INSTRUCTIONS = "TODO";
        System.out.print("Alrighty pardner, do you know how to play?\nY/N:");
        String help = input.next();

        if (help.equals("N")) {
            System.out.println("Straight to it!  Roll 'em!");
        } else if (help.equals("Y")) {
            System.out.println("Alrighty pardner, here ya go!");
            System.out.println(INSTRUCTIONS);
        } else {
            System.out.println("I didn't quite catch that pardner, but I'll assume you need a refresher!");
            System.out.println(INSTRUCTIONS);
        }

        comeOut();

        System.out.println("Want to go again?\nY/N:");
        switch (input.next().toLowerCase()) {
            case "y": System.out.println("Alrighty!  Roll 'em!");
                comeOut();
                break;
            case "n": System.out.println("See ya next time!");
                break;
            default: System.out.println("I didn't catch that pardner, maybe step outside while you sober up!");
        }
    }


    //just a little die roll function to call instead of writing out the expression every time
    public static Integer roll() {
        return (int) floor(Math.random() * 6 + 1);
    }

    //the longer version of the Come Out step, just with the added cowboy talk
    public static void comeOut() {
        int goal = roll() + roll();

        System.out.println("Yeeeee hawww!  Come on out!");
        System.out.println("You rolled " + goal);

        if (goal == 7 || goal == 11) {
            System.out.println("You win off the bat!  Next round is on you!");
        } else if (goal == 2 || goal == 3 || goal == 12) {
            System.out.println("You lose right off!  Sorry pardner!");
        } else {
            System.out.print("Lets play!");
            crap(goal);
        }
    }

    //the recursion version
    public static void crap (int goal) {
        //  not using my DICE ASCII array yet, but keeping it here so I don't need to write it again
        //final String[] DICE = {"Error", "⚀ 1", "⚁ 2", "⚂ 3", "⚃ 4", "⚄ 5", "⚅ 6"};
        int dieOne = roll();
        int dieTwo = roll();
        int total = dieOne + dieTwo;
        System.out.println("You rolled a " + dieOne + " and a " + dieTwo + " for a total of " + total + "!");
        if (total == 7) {
            System.out.println("You lose!");
        } else if (total == goal) {
            System.out.println("You win!");
        } else {
            crap(goal);
        }
    }
}