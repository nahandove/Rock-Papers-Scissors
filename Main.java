package rockpaperscissors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.List;
import java.util.Arrays;

public class Main {
    static Map<String, Integer> ratingsMap = new HashMap<>();
    static int currentScore;
    static Game game = new Game();
    static List<String> signsList = new ArrayList<>();

    public static void main(String[] args) {
        String name = "";
        try (BufferedReader fileReader = new BufferedReader(new FileReader("./rating.txt")))
         {
            String ratingsEntry = "";

            while (fileReader.ready()) {
                ratingsEntry = fileReader.readLine();
                String[] scores = ratingsEntry.split(" ");
                ratingsMap.put(scores[0], Integer.parseInt(scores[1]));
            }

            System.out.print("Enter your name:");
            name = Game.getResponse();
            System.out.println("Hello, " + name);

            if (ratingsMap.containsKey(name)) {
                currentScore = ratingsMap.get(name);
            } else {
                ratingsMap.put(name, 0);
            }

            String customSigns = Game.getResponse();

            if ("".equals(customSigns)) {
                signsList = List.of("rock", "paper", "scissors");
            } else {
                String[] entries = customSigns.split(",");
                signsList.addAll(Arrays.asList(entries));
            }
            System.out.println("Okay, let's start");
            game.playGame(name, currentScore, signsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
