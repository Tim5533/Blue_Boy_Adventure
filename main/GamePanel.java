package main;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16*16 tiles 
    final int scale = 3; // 小人是 16px*16px，再放大三倍

    // SCREEN SETTING
    final int tileSize = originalTileSize * scale; // 48*48 tiles
    final int maxScreenCol = 16; // 將螢幕分成 16*12 格
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixcels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixcels

    // FPS
    int FPS = 60;

    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;//多執行續繼承的class，物件代表一個執行續(Thread也實作了Runnable)

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() { // 呼叫繼承來的函式，可不加this(加了可增強可讀性)，或是可以改成super
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // 減少閃爍，(可以先將繪圖操作在影藏緩衝區完成，再一次展現
        this.addKeyListener(KeyH);
        this.setFocusable(true);//可收到輸入
    }

    public void startGameThread() {

        gameThread = new Thread(this); // 把GamePanel(this)傳給Thread物件的建構子
        gameThread.start(); //會自動呼叫run method
    }

    @Override
    public void run() {

        while (gameThread != null) {

            // UPDATE: update information such as charator positions
            update();
            
            // DRAW: draw the screen with the update information
            repaint(); // 呼叫paintComponent的方法

        }
    }

    public void update(){

        if (KeyH.upPressed == true){
            playerY -= playerSpeed;
        }
        else if (KeyH.downPressed == true){
            playerY += playerSpeed;
        }
        else if (KeyH.leftPressed == true){
            playerX -= playerSpeed;
        }
        else if (KeyH.rightPressed == true){
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
