package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class bigWorld {
    public static void buildRoom(int x, int y, TETile[][] world, int length, int width){
        for(int start = x; start < x + width; start ++){
            world[start][y] = Tileset.WALL;
            world[start][y + length - 1] = Tileset.WALL;
        }
        for(int upStart = y+1; upStart < y + length - 1; upStart ++){
            world[x][upStart] = Tileset.WALL;
            world[x+width - 1][upStart] = Tileset.WALL;
        }
        /** builds floors*/
        for(int start = x + 1; start < x + width - 1; start ++){
            for (int upStart = y + 1; upStart < y + length - 1; upStart ++) {
                world[start][upStart] = Tileset.FLOOR;
            }
        }
    }
    public void buildHallway(int x, int y, TETile[][] world, int length, int width){
        buildRoom(x, y, world, length, width);
    }

}
