package rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.FileWriter;

public class Game {
    static BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public void playGame(String playerName, int currentScore, List<String> customSigns) throws IOException {
        String answer = inputReader.readLine();
        String move = "";
        String aiResponse = "";

        if ("!exit".equals(answer)) {
            System.out.println("Bye!");
            Main.ratingsMap.put(playerName, currentScore);
            writeToFile("./ratings.txt");
            System.exit(0);
        }

        if ("!rating".equals(answer)) {
            System.out.println("Your rating: " + currentScore);
            playGame(playerName, currentScore, customSigns);
        }

        try {
            move = getPlayerMove(answer, customSigns);
            aiResponse = chooseAiMove(customSigns);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            playGame(playerName, currentScore, customSigns);
        }

        String youWin = "Well done. The computer chose " + aiResponse + " and failed";
        String youDraw = "There is a draw (" + aiResponse + ")";
        String youLose = "Sorry, but the computer chose " + aiResponse;

        Results result = compareSigns(move, aiResponse, customSigns);

        switch (result) {
            case WIN:
                System.out.println(youWin);
                currentScore += 100;
                break;
            case DRAW:
                System.out.println(youDraw);
                currentScore += 50;
                break;
            case LOSE:
                System.out.println(youLose);
                break;
            default:
        }
        playGame(playerName, currentScore, customSigns);
    }

    public void writeToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Map.Entry<String, Integer> pair : Main.ratingsMap.entrySet()) {
                fileWriter.write(pair.getKey() + " " + pair.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong writing to file");
        }
    }

    private Results compareSigns(String playerMove, String aiMove, List<String> signs) {
        int playerIndex = 0;
        int aiIndex = 0;
        for (int i = 0; i < signs.size(); i++) {
            if (playerMove.equals(signs.get(i))) {
                playerIndex = i;
            }
            if (aiMove.equals(signs.get(i))) {
                aiIndex = i;
            }
        }

        if (playerIndex == aiIndex) {
            return Results.DRAW;
        }

        if (playerIndex <= (signs.size() + 1) / 2) {
            if (aiIndex > playerIndex && aiIndex < playerIndex + (signs.size() + 1) / 2) {
                return Results.LOSE;
            } else {
                return Results.WIN;
            }
        } else {
            if (aiIndex < playerIndex && aiIndex > playerIndex - (signs.size() + 1) / 2) {
                return Results.WIN;
            }
        }
        return Results.LOSE;
    }

    private String getPlayerMove(String playerMove, List<String> signs) {
        if (!signs.contains(playerMove)) {
            throw new IllegalArgumentException("Invalid input");
        }
        for (String sign: signs) {
            if (playerMove.equals(sign)) {
                return playerMove;
            }
        }
        return null;
    }

    public static String getResponse() throws IOException {
        return inputReader.readLine();
    }

    private String chooseAiMove(List<String> signs) {
        Random random = new Random();
        int choice = random.nextInt(signs.size());
        return signs.get(choice);
    }
}
