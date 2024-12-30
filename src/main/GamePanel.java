package main;
import entity.*;
import object.*;
import tile.*;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16*16 tiles 
    final int scale = 3; // 小人是 16px*16px，再放大三倍

    // SCREEN SETTING
    public final int tileSize = originalTileSize * scale; // 48*48 tiles
    public final int maxScreenCol = 16; // 將螢幕分成 16*12 格
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixcels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixcels

    // WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxScreenCol;
    public final int worldHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    // SYSTEM
    public TileManager tileM = new TileManager(this);
    KeyHandler KeyH = new KeyHandler(); 
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);     // 碰撞偵測
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;      // 多執行續繼承的class，物件代表一個執行續(Thread也實作了Runnable)

    // ENTITY AND OBJECT
    public Player player = new Player(this, KeyH);
    public SuperObject obj[] = new SuperObject[10];
    

    public GamePanel() { // 呼叫繼承來的函式，可不加this(加了可增強可讀性)，或是可以改成super

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // 減少閃爍，(可以先將繪圖操作在影藏緩衝區完成，再一次展現)
        this.addKeyListener(KeyH);
        this.setFocusable(true);//可收到輸入
    }

    public void setupGame() {

        aSetter.setObject();

        playMusic(0);
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
            }
            if (timer >= 1000000000){
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

        //TILE
        tileM.draw(g2);

        //OBEJECT
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();
    }

    public void playMusic (int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE (int i) {
        sound.setFile(i);
        sound.play();
    }
}
