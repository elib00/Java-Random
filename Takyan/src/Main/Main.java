package Main;

import ElementsGUI.Background;
import ElementsGUI.ScoreDisplay;
import Entities.Ball;
import Entities.Paddle;
import GameUtils.Game;
import GameUtils.Sound;
import Popups.GameOverPopup;
import SoundEffects.GameSound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame window;

    public static final int WINDOW_SIZE_X = 800;
    public static final int WINDOW_SIZE_Y = 600;
    public static ScoreDisplay currentScore = new ScoreDisplay("Score: ", 0);
    public static int currentHighScore = ScoreDisplay.getHighScore();
    public static ScoreDisplay highScore = new ScoreDisplay("High Score: ", currentHighScore, 40, 100);
    public static Clip bgMusic;
    public static Game game;
    public static GameOverPopup gameOverPopup = new GameOverPopup();

    public static void main(String[] args) {
        window = new JFrame("Takyan");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setResizable(false);
        ImageIcon logo = new ImageIcon("src/Assets/ball.png");
        window.setIconImage(logo.getImage());

        game = new Game(window, WINDOW_SIZE_X, WINDOW_SIZE_Y);
        game.add(new Background());
        game.add(currentScore);
        game.add(highScore);
        game.add(new Ball());
        game.add(new Paddle());

        Thread musicThread = new Thread(() -> {
            bgMusic = Sound.play(GameSound.backgroundMusic);
        });

        musicThread.start();
        game.start();
    }
}
