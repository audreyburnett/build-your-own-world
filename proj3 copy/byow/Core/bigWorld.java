package byow.Core;
import java.util.ArrayList;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.Core.RandomUtils;
import java.lang.Math;
import java.util.HashMap;
import java.util.Random;

public class bigWorld {
    public static HashMap<String, Coordinate> avatarTracker= new HashMap<>();
    public static void buildRoom(int x, int y, TETile[][] world, int length, int width){
        for(int start = x; start < x + width; start ++){
            world[start][y] = Tileset.SAND;
            world[start][java.lang.Math.abs(y + length - 1)] = Tileset.SAND;

        }
        for(int upStart = y; upStart < y + length; upStart ++){
            world[x][upStart] = Tileset.SAND;
            world[java.lang.Math.abs(x+width - 1)][upStart] = Tileset.SAND;
        }
        /** builds floors*/
        for(int start = x + 1; start < x + width - 1; start ++){
            for (int upStart = y + 1; upStart < y + length - 1; upStart ++) {
                world[start][upStart] = Tileset.MOUNTAIN;
            }
        }
    }

    public static void worldAdjust(TETile[][] world){
        for( int x = 1; x < 79; x ++){
            for(int y = 1; y < 29; y++){
                if(world[x][y] == Tileset.SAND){
                    if(world[x][y-1] == Tileset.MOUNTAIN && world[x][y+1] == Tileset.MOUNTAIN){
                        world[x][y] = Tileset.MOUNTAIN;
                    }
                    if(world[x+1][y] == Tileset.MOUNTAIN && world[x-1][y] == Tileset.MOUNTAIN){
                        world[x][y] = Tileset.MOUNTAIN;
                    }
                    if(world[x][y - 1] == Tileset.WATER && world[x-1][y] == Tileset.MOUNTAIN && world[x-1][y-1] == Tileset.SAND){
                        world[x][y] = Tileset.MOUNTAIN;
                        world[x][y -1] = Tileset.SAND;
                    }
                }
                if(world[x][y] == Tileset.MOUNTAIN){
                    if(world[x - 1][y] == Tileset.MOUNTAIN && world[x+1][y] == Tileset.WATER){
                        world[x][y] = Tileset.SAND;
                    }
                    if(world[x + 1][y] == Tileset.MOUNTAIN && world[x - 1][y] == Tileset.WATER){
                        world[x][y] = Tileset.SAND;
                    }
                    if(world[x][y-1] == Tileset.MOUNTAIN && world[x][y+1] == Tileset.WATER){
                        world[x][y] = Tileset.SAND;
                    }
                    if(world[x][y+1] == Tileset.MOUNTAIN && world[x][y-1] == Tileset.WATER) {
                        world[x][y] = Tileset.SAND;
                    }
                }
                if(x == 0 || y == 0){
                    if(world[x][y] == Tileset.MOUNTAIN){
                        world[x][y] = Tileset.SAND;
                    }
                }
            }
        }
    }
    public static Boolean movementCheckerUp(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x][current.y + 1] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean movementCheckerRight( TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x + 1][current.y] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean movementCheckerDown(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x][current.y - 1] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    public static Boolean movementCheckerLeft(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x - 1][current.y] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    public static void avatarUp(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y + 1] = Tileset.AVATAR;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate up = new Coordinate(current.x, current.y+1);
        avatarTracker.put("avatar", up);
    }
    public static void FavatarUp(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y + 1] = Tileset.FLOWER;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate up = new Coordinate(current.x, current.y+1);
        avatarTracker.put("avatar", up);
    }
    public static void TavatarUp(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y + 1] = Tileset.TREE;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate up = new Coordinate(current.x, current.y+1);
        avatarTracker.put("avatar", up);
    }
    public static void GavatarUp(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y + 1] = Tileset.GRASS;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate up = new Coordinate(current.x, current.y+1);
        avatarTracker.put("avatar", up);
    }
    public static void avatarRight(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x + 1][current.y] = Tileset.AVATAR;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate right = new Coordinate(current.x+1, current.y);
        avatarTracker.put("avatar", right);
    }
    public static void FavatarRight(TETile[][] world) {
        Coordinate current = avatarTracker.get("avatar");
        world[current.x + 1][current.y] = Tileset.FLOWER;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate right = new Coordinate(current.x + 1, current.y);
        avatarTracker.put("avatar", right);
    }
    public static void TavatarRight(TETile[][] world) {
        Coordinate current = avatarTracker.get("avatar");
        world[current.x + 1][current.y] = Tileset.TREE;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate right = new Coordinate(current.x + 1, current.y);
        avatarTracker.put("avatar", right);
    }
    public static void GavatarRight(TETile[][] world) {
        Coordinate current = avatarTracker.get("avatar");
        world[current.x + 1][current.y] = Tileset.GRASS;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate right = new Coordinate(current.x + 1, current.y);
        avatarTracker.put("avatar", right);
    }
    public static void avatarDown(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y - 1] = Tileset.AVATAR;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate down = new Coordinate(current.x, current.y-1);
        avatarTracker.put("avatar", down);
    }
    public static void TavatarDown(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y - 1] = Tileset.TREE;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate down = new Coordinate(current.x, current.y-1);
        avatarTracker.put("avatar", down);
    }
    public static void FavatarDown(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y - 1] = Tileset.FLOWER;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate down = new Coordinate(current.x, current.y-1);
        avatarTracker.put("avatar", down);
    }
    public static void GavatarDown(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y - 1] = Tileset.GRASS;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate down = new Coordinate(current.x, current.y-1);
        avatarTracker.put("avatar", down);
    }
    public static void avatarLeft( TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x - 1][current.y] = Tileset.AVATAR;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate left = new Coordinate(current.x-1, current.y);
        avatarTracker.put("avatar", left);
    }
    public static void FavatarLeft( TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x - 1][current.y] = Tileset.FLOWER;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate left = new Coordinate(current.x-1, current.y);
        avatarTracker.put("avatar", left);
    }
    public static void TavatarLeft( TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x - 1][current.y] = Tileset.TREE;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate left = new Coordinate(current.x-1, current.y);
        avatarTracker.put("avatar", left);
    }
    public static void GavatarLeft( TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x - 1][current.y] = Tileset.GRASS;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate left = new Coordinate(current.x-1, current.y);
        avatarTracker.put("avatar", left);
    }
    public static void avTrackerAdder(int x, int y){
        Coordinate av = new Coordinate(x,y);
        avatarTracker.put("avatar", av);
    }
    public static Coordinate avTrackerGetter(){
        Coordinate coord = avatarTracker.get("avatar");
        return coord;
    }
}
