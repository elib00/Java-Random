package ElementsGUI;

import GameUtils.RenderObj;

import javax.swing.*;
import java.awt.*;

public class Background extends RenderObj {
    private final Image background = new ImageIcon("src/Assets/background.jpg").getImage();
    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(background, 0, 0, null);
    }
}
