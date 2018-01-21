package game;

import javax.swing.JFrame;

public class PirateGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        DrawingSurface ds = new DrawingSurface();
        frame.setSize(DrawingSurface.gameWidth, DrawingSurface.gameHeight);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ye Olde Pirate Game");
        frame.add(ds);
        frame.setVisible(true); //must be last line for some reason

    }

}
