package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        //設定
        window.setResizable(false);
        window.setTitle("Treasure Hunting RPG");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();  // 根據其內容的首選大小調整視窗的大小，不用手動

        window.setLocationRelativeTo(null); //設定視窗和指定component的相對位置，null放中間
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
