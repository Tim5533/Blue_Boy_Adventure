package main;

import java.awt.*;
import javax.swing.*;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16*16 tiles 
    final int scale = 3; // 小人是 16px*16px，再放大三倍

    // SCREEN SETTING
    public final int tileSize = originalTileSize * scale; // 48*48 tiles
    final int maxScreenCol = 16; // 將螢幕分成 16*12 格
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixcels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixcels

    // FPS
    int FPS = 60;

    KeyHandler KeyH = new KeyHandler();
    Thread gameThread;//多執行續繼承的class，物件代表一個執行續(Thread也實作了Runnable)
    Player player = new Player(this, KeyH);

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

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime-lastTime)/drawInterval;
            timer += currentTime-lastTime;

            lastTime = currentTime;

            if (delta >= 1){
                
                // UPDATE: update information such as charator positions
                update();

                // DRAW: draw the screen with the update information
                repaint(); // 呼叫paintComponent的方法
            
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();

    }

    public void paintComponent(Graphics g){
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }
}