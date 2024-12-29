package tile;
import main.*;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("res\\maps\\world01.txt");
    }

    public void getTileImage() {

        // 圖片檔案在 Tile.java 
        try 
        {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Tile.grass);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Tile.wall);

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Tile.water);

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Tile.earth);

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Tile.tree);

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Tile.sand);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try 
        {
            // 將 InputStream 替換成 FileReader，以利於讀寫檔案
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] tempNums = line.split(" ");
                    int tempNum = Integer.parseInt(tempNums[col]);

                    mapTileNum[col][row] = tempNum;
                    col ++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row ++;
                }
            }

            br.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            // 世界座標 (絕對座標)
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            // 螢幕座標 (世界座標相對玩家的座標)
            // 螢幕讀取座標為左上角，必須增加玩家位於螢幕的座標，彌補座標差，使讀取座標為玩家座標位置
            int screenX = worldX - gp.player.worldX + gp.player.screenX;    
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // 若地塊超出螢幕邊界，就不畫出
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) 
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol ++;
            
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
