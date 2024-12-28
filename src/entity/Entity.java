package entity;

import java.io.File;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    File boy_up_1 = new File("res\\player\\boy_up_1.png");
    File boy_up_2 = new File("res\\player\\boy_up_2.png");
    File boy_down_1 = new File("res\\player\\boy_down_1.png");
    File boy_down_2 = new File("res\\player\\boy_down_2.png");
    File boy_left_1 = new File("res\\player\\boy_left_1.png");
    File boy_left_2 = new File("res\\player\\boy_left_2.png");
    File boy_right_1 = new File("res\\player\\boy_right_1.png");
    File boy_right_2 = new File("res\\player\\boy_right_2.png");

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction; 
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
