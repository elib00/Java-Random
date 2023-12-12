package Entities;
import ElementsGUI.Background;
import ElementsGUI.ScoreDisplay;
import GameScreens.SampleScreen;
import GameUtils.Game;
import GameUtils.RenderObj;
import GameUtils.Sound;
import GameUtils.Updater;
import Main.Main;
import Popups.GameOverPopup;
import SoundEffects.GameSound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Ball extends RenderObj implements Updater{
    private int xCoordinate = Main.WINDOW_SIZE_X / 2;
    private final int xInitial = xCoordinate;
    private int yCoordinate = 40;
    private final int yInitial = yCoordinate;
    private int radius = 10;
    private double maxVelocity = 18;
    private double xVelocity = 0;
    private double yVelocity = 5;
    private BufferedImage ballImage;

    public Ball(){
        try {
            ballImage = ImageIO.read(new File("src/Assets/ball.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateXVelocity(){
        double xMiddle = Paddle.xCoordinate;
        double xDiff = xMiddle - xCoordinate;
        double reductionFactor = (double) (Paddle.width / 2) / maxVelocity;
        double tempXVel = xDiff / reductionFactor;
        xVelocity = (int) Math.round(-1 * tempXVel);
    }

    private void resetBallPosition(){
        xCoordinate = xInitial;
        yCoordinate = yInitial;
        xVelocity = 0;
        yVelocity = 5;
    }

    private void moveBall(){
        xCoordinate += (int) Math.round(xVelocity);
        yCoordinate += (int) Math.round(yVelocity);
        handleCollision();
    }

    private void handleCollision(){
        if(xCoordinate + radius >= Main.WINDOW_SIZE_X || xCoordinate - radius <= 0){
            xVelocity *= -1;
            Sound.play(GameSound.wallBounceSoundEffect);
        }

        if(yCoordinate - radius <= 0){
            yVelocity *= -1;
            Sound.play(GameSound.wallBounceSoundEffect);
        }

        if(yCoordinate + radius >= Main.WINDOW_SIZE_Y){
            Sound.play(GameSound.gameOverSoundEffect);
            Sound.stop(Main.bgMusic);
            xVelocity = 0;
            yVelocity = 0;
            xCoordinate = xInitial;
            yCoordinate = yInitial;

            int choice = JOptionPane.showConfirmDialog(
                    Main.gameOverPopup.frame,
                    Main.gameOverPopup.panel,
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            int temp = Main.currentScore.getScore();
            Main.highScore.setScore(Math.max(temp, Main.highScore.getScore()));
            if(Main.currentHighScore < temp){
                Main.currentScore.recordScore();
                Main.currentHighScore = temp;
            }

            if(choice == JOptionPane.YES_OPTION){
                Main.currentScore.resetScore();
                Sound.replay(Main.bgMusic);
                resetBallPosition();
                Main.game.run();
            }else{
                System.exit(0);
            }
        }

        if(yCoordinate + radius >= Paddle.yCoordinate + Paddle.height / 2){
            if(xCoordinate - radius >= Paddle.xCoordinate - (Paddle.width / 2) && xCoordinate + radius <= Paddle.xCoordinate + (Paddle.width / 2)){
                yVelocity *= -1;
                Sound.play(GameSound.bounceSoundEffect);
                Main.currentScore.setScore(Main.currentScore.getScore() + 1);
                calculateXVelocity();
            }
        }
    }

    @Override
    public void paintComponent(Graphics2D g) {
//        g.setColor(Color.RED);
//        g.fillOval(xCoordinate - radius, yCoordinate - radius, radius * 2, radius * 2);
        if (ballImage != null) {
            // Draw the ball's image at the specified coordinates
            g.drawImage(ballImage, xCoordinate - radius, yCoordinate - radius, null);
        } else {
            // If the image couldn't be loaded, draw a default circle
            g.setColor(Color.RED);
            g.fillOval(xCoordinate - radius, yCoordinate - radius, radius * 2, radius * 2);
        }
    }


    @Override
    public void update() {
        moveBall();
    }
}
