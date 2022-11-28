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
    public static void Fmove(String n, TETile[][] world) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.FavatarUp(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.FavatarRight(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.FavatarDown(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.FavatarLeft(world);
            }
        }
    }
    public static void Tmove(String n, TETile[][] world) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.TavatarUp(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.TavatarRight(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.TavatarDown(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.TavatarLeft(world);
            }
        }
    }
    public static void Gmove(String n, TETile[][] world) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.GavatarUp(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.GavatarRight(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.GavatarDown(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.GavatarLeft(world);
            }
        }
    }
}
