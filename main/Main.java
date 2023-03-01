package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame jframe = new JFrame();
        GamePanel gamePanel = new GamePanel();
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        gamePanel.setAssets();
        gamePanel.startThread();
    }
}
