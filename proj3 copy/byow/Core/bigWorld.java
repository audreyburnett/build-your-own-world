package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.Core.RandomUtils;
import java.lang.Math;

public class bigWorld {
    public static void buildRoom(int x, int y, TETile[][] world, int length, int width){
        for(int start = x; start < x + width; start ++){
            world[start][y] = Tileset.SAND;
            world[start][java.lang.Math.abs(y + length - 1)] = Tileset.SAND;
        }
        for(int upStart = y+1; upStart < y + length - 1; upStart ++){
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
    /** creates random (random size,coords) room*/
    //public static void randomRooms(){

    //}

    public static void buildHallway(int x, int y, TETile[][] world, int length, int width){
        buildRoom(x, y, world, length, width);
    }


}
