package ChatBot;
import java.util.Scanner;
public class ChatBot {

    public static void main(String[] args) {

        String botname = "JabbaBot";

        System.out.println("Hello! My name is " + botname + ".");
        System.out.println("I was created in " + 2023 + ".");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, remind me your name.");
        String UserName = scanner.nextLine();
        System.out.println("What a great name you have, "+ UserName +"!");
    }
}
