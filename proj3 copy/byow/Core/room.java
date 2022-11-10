package byow.Core;

import byow.TileEngine.TETile;
import java.lang.Math;
import byow.TileEngine.Tileset;

public class room {
    private int x;
    private int y;
    private int length;
    private int width;
    public room(int x, int y, int length, int width){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }
    public static void connect(room roomA, room roomB, TETile[][] world){
        int aXCoord = roomA.x;
        int aYCoord = roomA.y;
        int aLength = roomA.length;
        int aWidth = roomA.width;
        int bXCoord = roomB.x;
        int bYCoord = roomB.y;
        int bLength = roomB.length;
        int bWidth = roomB.width;
        if (aXCoord > bXCoord + roomB.width) {
            int distBetween = bXCoord - (aXCoord + aWidth);
            int min = java.lang.Math.min(roomA.length, roomB.length);
            int connectorY = (min / 2) + roomB.y;
            bigWorld.buildRoom((aXCoord + aWidth), (connectorY - 1), world, 3, (distBetween + 2));
            world[roomB.x + roomB.width][connectorY] = Tileset.MOUNTAIN;
            world[roomA.x + roomA.width][connectorY] = Tileset.MOUNTAIN;
        }

    }
}
