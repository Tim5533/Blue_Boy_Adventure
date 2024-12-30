package object;

import java.io.*;
import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{

    static File boots = new File("res\\objects\\boots.png");

    public OBJ_Boots() {
        
        name = "Boots";
        try 
        {
            image = ImageIO.read(boots);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
