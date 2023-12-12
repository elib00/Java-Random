package Entities;
import GameUtils.Mouse;
import GameUtils.RenderObj;
import GameUtils.Sound;
import GameUtils.Updater;
import Main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Paddle extends RenderObj implements Updater {
    private Mouse mouse;
    public static int xCoordinate = Main.WINDOW_SIZE_X / 2;
    public static int yCoordinate = Main.WINDOW_SIZE_Y - 40;
    public static int width = 100;
    public static int height = 30;
    private BufferedImage paddleImage;

    public Paddle(){
        try {
            paddleImage = ImageIO.read(new File("src/Assets/tsinelas.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics2D g) {
        if(paddleImage != null){
            g.drawImage(paddleImage, xCoordinate - width / 2, yCoordinate - height / 2, null);
        }else{
            g.setColor(Color.YELLOW);
            g.fillRect(xCoordinate - width / 2, yCoordinate - height / 2, width, height);
        }
    }

    @Override
    public void update() {
        mouse = game.mouse;
        xCoordinate = mouse.x;
//        yCoordinate = mouse.y;
        yCoordinate = Main.WINDOW_SIZE_Y - 20;
        if(yCoordinate >= Main.WINDOW_SIZE_Y - height){
            yCoordinate = Main.WINDOW_SIZE_Y - height;
        }

        if(xCoordinate + width / 2 >= Main.WINDOW_SIZE_X){
            xCoordinate = Main.WINDOW_SIZE_X - width / 2;
        }

        if(xCoordinate - width / 2  <= 0){
            xCoordinate = width / 2;
        }
    }
}
