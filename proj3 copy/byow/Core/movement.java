package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.TERenderer;
public class movement {
    public static void move(String n, TETile[][] world, TERenderer ter) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.avatarUp(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.avatarRight(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.avatarDown(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.avatarLeft(world);
                ter.renderFrame(world);
            }
        }
    }
    public static void Fmove(String n, TETile[][] world, TERenderer ter) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.FavatarUp(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.FavatarRight(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.FavatarDown(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.FavatarLeft(world);
                ter.renderFrame(world);
            }
        }
    }
    public static void Tmove(String n, TETile[][] world, TERenderer ter) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.TavatarUp(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.TavatarRight(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.TavatarDown(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.TavatarLeft(world);
                ter.renderFrame(world);
            }
        }
    }
    public static void Gmove(String n, TETile[][] world, TERenderer ter) {
        if (n.equals("w")) {
            if(bigWorld.movementCheckerUp(world)){
                bigWorld.GavatarUp(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("d")) {
            if(bigWorld.movementCheckerRight(world)){
                bigWorld.GavatarRight(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("s")) {
            if(bigWorld.movementCheckerDown(world)){
                bigWorld.GavatarDown(world);
                ter.renderFrame(world);
            }
        }
        if (n.equals("a")) {
            if(bigWorld.movementCheckerLeft(world)){
                bigWorld.GavatarLeft(world);
                ter.renderFrame(world);
            }
        }
    }
}
