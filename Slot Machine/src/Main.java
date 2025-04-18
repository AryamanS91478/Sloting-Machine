import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int balance = 100;
        int bet;
        int payout;
        String[] row;

        System.out.println("_____________________________");
        System.out.println("Welcome to Java Slot Machine");
        System.out.println(" Symbols: 🍒 🍐 🍉 🔔 ⭐");
        System.out.println("_____________________________");

        while (balance > 0){
            System.out.println("Your current Balance is: $" + balance);
            System.out.print("Enter your bet amount: $");
            bet = scanner.nextInt();
            scanner.nextLine();

            if(bet>balance){
                System.out.println("Insufficient Balance");
            } else if (bet<=0) {
                System.out.println("Bet amount cant be negative");
            }
            else{
                balance -= bet;
            }

            System.out.println("Spinning....");
            row = spinRow();
            printRow(row);
            payout = getPayout(row,bet);

            if (payout>0){
                System.out.println("You Won &" + payout);
                balance+=payout;
            }
            else {
                System.out.println("You Lost");
            }

            System.out.print("Do you want to play Again? (Y/N): ");
            String playAgain = scanner.nextLine().toUpperCase();

            if (!playAgain.equals("Y")){
                break;
            }
        }
        System.out.println("Game Over! Your final balance is $" + balance);
    }
    static String[] spinRow(){
        String[] symbols = {"🍒","🍐","🍉","🔔","⭐"};
        String[] rows = new String[3];
        Random random = new Random();

        for(int i = 0; i<3; i++){
            rows[i] = symbols[random.nextInt(symbols.length)];
        }

        return rows;
    }
    static void printRow(String[] row){
        System.out.println("_____________________________");
        System.out.println(" " + String.join("|",row));
        System.out.println("_____________________________");
    }
    static int getPayout(String[] row, int bet){

        if(row[0].equals(row[1]) && row[1].equals(row[2])){
            return switch (row[0]){
                case "🍒" -> bet * 3;
                case "🍐" -> bet * 4;
                case "🍉" -> bet * 5;
                case "🔔" -> bet * 10;
                case "⭐" -> bet * 20;
                default -> 0;
            };
        } else if (row[0].equals(row[1]) || row[1].equals(row[2]) || row[0].equals(row[2])) {
            return switch (row[0]){
                case "🍒" -> bet * 1;
                case "🍐" -> bet * 2;
                case "🍉" -> bet * 3;
                case "🔔" -> bet * 5;
                case "⭐" -> bet * 8;
                default -> 0;
            };
        }
        else {
            return 0;
        }
    }
}
