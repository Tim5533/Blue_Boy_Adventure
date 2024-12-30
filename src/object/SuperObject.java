package object;
import main.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {

        // 螢幕座標 (相對玩家的座標)
        int screenX = worldX - gp.player.worldX + gp.player.screenX;    
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // 若地塊超出螢幕邊界，就不畫出
        if (worldX + 2*gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - 2*gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + 2*gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - 2*gp.tileSize < gp.player.worldY + gp.player.screenY ) 
        {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
