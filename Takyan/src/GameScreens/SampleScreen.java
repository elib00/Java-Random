package GameScreens;

import Entities.Paddle;
import Main.Main;

import javax.swing.*;
import java.awt.*;

public class SampleScreen extends JPanel {
    private JFrame frame;
    private JLayeredPane pane;
    public SampleScreen(){
        pane = new JLayeredPane();
        JLabel sample = new JLabel("Hi sample");
        add(sample, BorderLayout.CENTER);
    }
}
