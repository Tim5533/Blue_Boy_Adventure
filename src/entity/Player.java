package entity;
import main.*;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler KeyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        
        this.gp = gp;
        this.KeyH = keyH;

        // 設定玩家位置在畫面正中間 
        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        // 設定玩家碰撞區域
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        
        worldX = gp.tileSize * 23;  
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try
        {
            // 圖片檔案在 Entity.java 
            up1 = ImageIO.read(Entity.boy_up_1);
            up2 = ImageIO.read(Entity.boy_up_2);
            down1 = ImageIO.read(Entity.boy_down_1);
            down2 = ImageIO.read(Entity.boy_down_2);
            left1 = ImageIO.read(Entity.boy_left_1);
            left2 = ImageIO.read(Entity.boy_left_2);
            right1 = ImageIO.read(Entity.boy_right_1);
            right2 = ImageIO.read(Entity.boy_right_2);
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void update() {
        if (KeyH.upPressed == true || KeyH.downPressed == true 
            || KeyH.leftPressed == true || KeyH.rightPressed == true){
                
                if (KeyH.upPressed == true){
                    direction = "up";
                }
                else if (KeyH.downPressed == true){
                    direction = "down";
                }
                else if (KeyH.leftPressed == true){
                    direction = "left";
                }
                else if (KeyH.rightPressed == true){
                    direction = "right";
                }

                // CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkTile(this);

                // CHECK OBEJECT COLLISION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObeject(objIndex);

                // IF COLLISION IS FALSE, PLAYER CAN MOVE
                if (collisionOn == false) {

                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }
        
                spriteCounter++;
                if(spriteCounter > 12){ // 12f換一次圖
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if(spriteNum == 2){
                        spriteNum = 1;
                    }
        
                    spriteCounter = 0;
                }
            }
    }

    public void pickUpObeject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;

                case "Door":
                    if (hasKey > 0){
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You Open the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    if(hasKey > 0){
                        gp.playSE(2);
                        speed += 1;
                        gp.obj[i] = null;
                        gp.ui.showMessage("Speed Up!");
                    }
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        
        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                    break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
