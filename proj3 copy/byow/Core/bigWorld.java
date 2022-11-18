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

    public static void buildHallway(int x, int y, TETile[][] world, int length, int width){
        buildRoom(x, y, world, length, width);
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
            }
        }
    }
    public static void doorBuilder(TETile[][] world, Coordinate coord){
        int x = coord.x;
        int y = coord.y;
        world[x][y] = Tileset.LOCKED_DOOR;
    }
}
