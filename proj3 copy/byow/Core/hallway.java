package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;

public class hallway {
    public static void connectRight(ArrayList answer, TETile[][] world){
        Coordinate origin = (Coordinate) answer.get(0);
        Coordinate destination = (Coordinate) answer.get(1);
        int origX = origin.x;
        int origY = origin.y;
        int destX = destination.x;
        int destY = destination.y;
        for(int start = origX; start <= destX; start++){
            world[start][origY] = Tileset.MOUNTAIN;
            world[start][origY + 1] = Tileset.SAND;
            world[start][origY - 1] = Tileset.SAND;
        }
    }
    public static void connectUp(ArrayList answer, TETile[][] world){
        Coordinate origin = (Coordinate) answer.get(0);
        Coordinate destination = (Coordinate) answer.get(1);
        int origX = origin.x;
        int origY = origin.y;
        int destX = destination.x;
        int destY = destination.y;
        for(int start = origY; start <= destY; start++){
            world[origX][start] = Tileset.MOUNTAIN;
            world[origX + 1][start] = Tileset.SAND;
            world[origX - 1][start] = Tileset.SAND;
        }
    }
    public static void connectDown(ArrayList answer, TETile[][] world){
        Coordinate origin = (Coordinate) answer.get(0);
        Coordinate destination = (Coordinate) answer.get(1);
        int origX = origin.x;
        int origY = origin.y;
        int destX = destination.x;
        int destY = destination.y;
        for(int start = origY; start >= destY; start--){
            world[origX][start] = Tileset.MOUNTAIN;
            world[origX + 1][start] = Tileset.SAND;
            world[origX - 1][start] = Tileset.SAND;
        }
    }
    public static void connectLeft(ArrayList answer, TETile[][] world){
        Coordinate origin = (Coordinate) answer.get(0);
        Coordinate destination = (Coordinate) answer.get(1);
        int origX = origin.x;
        int origY = origin.y;
        int destX = destination.x;
        int destY = destination.y;
        for(int start = origX; start >= destX; start--){
            world[start][origY] = Tileset.MOUNTAIN;
            world[start][origY + 1] = Tileset.SAND;
            world[start][origY - 1] = Tileset.SAND;
        }
    }
}
