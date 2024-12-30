package main;

import java.awt.*;

public class GameOver {

    boolean GameOverStatus = false;

    Font arial,arialB;

    GamePanel gp;
    Sound loseSd = new Sound();
    Sound GameoverSd = new Sound();
    
    GameOver (GamePanel gp) {
        this.gp = gp;

        arial = new Font("Arial", Font.PLAIN, 30);
        arialB = new Font("Arial", Font.BOLD, 80);
    }

    public void draw(Graphics2D g2) {

        int x,y;
        String text;
        int textLength;

        // Set background color
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // "Game over" text
        g2.setFont(arialB);
        g2.setColor(Color.red);

        text = "Game Over";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 - 72;

        g2.drawString(text, x, y);

        // "Better Luck" text
        g2.setFont(arial);
        g2.setColor(Color.white);

        text = "Better luck next Time!";
        textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth/2 - textLength/2;
        y = gp.screenHeight/2 + 72;

        g2.drawString(text, x, y);
    }

    public void playLoseSound(boolean flag) {
        if (flag) {
            loseSd.setFile(6);
            loseSd.play();
        }
    }

    public void playGameOverSound() {
        GameoverSd.setFile(5);
        GameoverSd.play();
    }
}
