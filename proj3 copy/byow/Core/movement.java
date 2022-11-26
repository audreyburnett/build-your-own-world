package byow.Core;
import byow.TileEngine.TETile;

public class movement {
    public static void move(String n, TETile[][] world) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.avatarUp(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.avatarRight(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.avatarDown(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.avatarLeft(world);
            }
        }
    }
}
