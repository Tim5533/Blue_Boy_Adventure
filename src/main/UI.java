package main;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    
    GamePanel gp;
    Font arial, arialB;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    
    // Game Over
    GameOver gameover;
    double stopTimeCounter = 0;
    boolean loseSdPlay = true;
    public boolean gameOvered = false;

    int sparkle = 0;

    public UI (GamePanel gp) {
        this.gp = gp;

        arial = new Font("Arial", Font.PLAIN, 40);
        arialB = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
        
        gameover = new GameOver(gp);
    }

    public void showMessage (String text) {

        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(arial);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x; int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - 144;
            g2.drawString(text, x, y);

            text = "Your Time is :" + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + 192;
            g2.drawString(text, x, y);

            g2.setFont(arialB);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + 96;
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else if (playTime >= Main.gameLimit) {

            gameOvered = true;

            if (stopTimeCounter == 0) {
                gp.stopMusic();
            }

            if (stopTimeCounter == gp.FPS * 0.5f) {
                gameover.playLoseSound(loseSdPlay);
                loseSdPlay = false;
            }
            
            if (stopTimeCounter == gp.FPS * 2.4f) {
                gameover.draw(g2);
                gameover.playGameOverSound();
                gp.gameThread = null;
            }
            
            stopTimeCounter ++;
        }
        else {
            g2.setFont(arial);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, 24, 24, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            // TIME
            playTime += (double)1/60;
            g2.drawString("Time:" + dFormat.format(playTime), 528, 65);

            // MESSAGE
            if (messageOn == true) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, 24, 240);

                messageCounter ++;

                if (messageCounter > gp.FPS*2) {    // 60幀 * 2 = 2秒
                    messageCounter = 0;
                    messageOn = false;
                }
            }

            // HURRY!
            if (playTime >= Main.hurryTime-0.5 && sparkle >= gp.FPS*0.5) {

                g2.setFont(g2.getFont().deriveFont(Font.BOLD ,26F));
                g2.setColor(Color.red);
                g2.drawString("HURRY!", 533, 105);
            }

            if (sparkle >= gp.FPS*1){
                sparkle = 0;
            }
            
            sparkle ++;
        }
    }

}
