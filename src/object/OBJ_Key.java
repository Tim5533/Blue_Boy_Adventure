package object;

import java.io.*;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{

    static File key = new File("res\\objects\\key.png");

    public OBJ_Key() {
        
        name = "Key";
        try 
        {
            image = ImageIO.read(key);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
