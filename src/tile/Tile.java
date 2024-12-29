package tile;

import java.io.*;
import java.awt.image.BufferedImage;

public class Tile {
    // 匯入圖片檔案
    static File grass = new File("res\\tiles\\grass.png");
    static File wall = new File("res\\tiles\\wall.png");
    static File water = new File("res\\tiles\\water.png");
    static File earth = new File("res\\tiles\\earth.png");
    static File tree = new File("res\\tiles\\tree.png");
    static File sand = new File("res\\tiles\\sand.png");
    
    public BufferedImage image;
    public boolean collision = false;

}
