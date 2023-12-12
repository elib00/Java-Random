package ElementsGUI;

import GameUtils.RenderObj;

import java.awt.*;
import java.io.*;

public class ScoreDisplay extends RenderObj {
    private int scoreDisplay = 0;
    private String text;
    private int xPos = 40;
    private int yPos = 50;

    public ScoreDisplay(String text, int initialScoreDisplay){
        this.text = text;
        scoreDisplay = initialScoreDisplay;
    }

    public ScoreDisplay(String text, int initialScoreDisplay, int x, int y){
        this.text = text;
        scoreDisplay = initialScoreDisplay;
        xPos = x;
        yPos = y;
    }

    public void setScore(int score){
        scoreDisplay = score;
    }

    public int getScore(){
        return scoreDisplay;
    }

    public void resetScore(){
        scoreDisplay = 0;
    }

    public void recordScore(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/scores.txt", true))) {
            writer.write(getScore() + "");
            writer.newLine();
            System.out.println("Score successfully added to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resetScore();
    }

    public static int getHighScore(){
        int highScore = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/scores.txt"))) {
            String score;

            while ((score = reader.readLine()) != null) {
                System.out.println(score);
                highScore = Math.max(highScore, Integer.parseInt(score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return highScore;
    }


    @Override
    public void paintComponent(Graphics2D g) {
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(text + scoreDisplay, xPos, yPos);
    }
}
