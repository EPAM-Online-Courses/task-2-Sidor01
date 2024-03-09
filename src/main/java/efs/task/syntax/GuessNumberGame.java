package efs.task.syntax;
import java.util.Scanner;
import java.util.Random;
public class GuessNumberGame {

    public int M;
    public int L;
    public int guessAttempt;

    public int randomNumber;

    public String guessBar = "[";

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {

        this.guessAttempt = 0;

        try {

            this.M = Integer.parseInt(argument);

            if (M < 1 || M > UsefulConstants.MAX_UPPER_BOUND)
            {
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new java.lang.IllegalArgumentException(" - jest spoza zakresu <1,400>");
            }

        }
        catch (NumberFormatException e) {

            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new java.lang.IllegalArgumentException("- to nie jest liczba!");

        }

        this.L = (int) (Math.log(M) / Math.log(2) + 1);

        Random random = new Random();
        this.randomNumber = random.nextInt(M) + 1;

        for(int i = 0;i < L;i++)
        {
            guessBar = guessBar.concat(Character.toString('.'));
        }
        guessBar = guessBar.concat(Character.toString(']'));

    }
    public String updateGuessBar(String guessBar){
        if (guessAttempt >= 0 && guessAttempt < guessBar.length()) {
            guessAttempt++;
           return guessBar.substring(0, guessAttempt) + "*" + guessBar.substring(guessAttempt + 1);
        } else {
            System.out.println("Index out of bounds");
            return "";
        }
    }

    public void play() {

        Scanner scanner = new Scanner(System.in);

        int guess;

        System.out.println("Witaj w grze Za dużo, za mało, w ktorej musisz odgadnac liczbe z zakresu <1," + M + ">");

        while (guessAttempt < L) {

        guessBar = updateGuessBar(guessBar);
        System.out.println("Aktualna liczba prob: " + guessBar);

        System.out.println(UsefulConstants.GIVE_ME);

        try{

            guess = Integer.parseInt(scanner.nextLine());

            }
            catch (NumberFormatException e) {

                System.out.println(UsefulConstants.NOT_A_NUMBER);
                continue;
            }
            if( guess > randomNumber ){

                System.out.println(UsefulConstants.TO_MUCH);

            }
            else if( guess < randomNumber )
            {
                System.out.println(UsefulConstants.TO_LESS);
            }
            else
            {
                System.out.println(UsefulConstants.YES);
                guessAttempt--;
                break;
            }
        }

        if(guessAttempt < L)
        {
            System.out.println(UsefulConstants.CONGRATULATIONS);
        }
        else
        {
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }
}
