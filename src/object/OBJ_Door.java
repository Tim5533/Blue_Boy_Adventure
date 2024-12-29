package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{

    static File door = new File("res\\objects\\door.png");

    public OBJ_Door() {
        
        name = "Door";
        try {
            image = ImageIO.read(door);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
