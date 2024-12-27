package main;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16*16 tiles
    final int scale = 3;

    // SCREEN SETTING
    final int tileSize = originalTileSize * scale; // 48*48 tiles
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixcels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixcels

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

    }

}
