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
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("res\\maps\\map01.txt");
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

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] tempNums = line.split(" ");
                    int tempNum = Integer.parseInt(tempNums[col]);

                    mapTileNum[col][row] = tempNum;
                    col ++;
                }

                if (col == gp.maxScreenCol) {
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            col ++;
            x += gp.tileSize;
            
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row ++;
                y += gp.tileSize;
            }
        }
    }
}
