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
        if (aXCoord + aWidth < bXCoord ) {
            int distBetween = bXCoord - (aXCoord + aWidth);
            int connectorY = java.lang.Math.max(aYCoord, bYCoord);
            bigWorld.buildRoom((aXCoord + aWidth), (connectorY), world, 3, (distBetween + 2));
            for (int i = bXCoord + 1; i < bXCoord + bWidth - 1; i++){
                world[i][connectorY + 1] = Tileset.MOUNTAIN;
                world[i][connectorY + 2] = Tileset.MOUNTAIN;
            }
            world[aXCoord + aWidth][connectorY + 1] = Tileset.MOUNTAIN;
            world[aXCoord + aWidth - 1][connectorY + 1] = Tileset.MOUNTAIN;
        }
        if (bYCoord > aYCoord + aLength){
            int distBetween = bYCoord - (aYCoord + aLength);
            int connectorX = java.lang.Math.max(aXCoord, bXCoord);
            bigWorld.buildRoom((connectorX), (aYCoord + aLength), world, distBetween + 2, 3);
            for (int i = bYCoord + 1; i < bYCoord + bLength - 1; i++) {
                world[connectorX + 1][i] = Tileset.MOUNTAIN;
            }
            world[connectorX + 1][aYCoord + aLength] = Tileset.MOUNTAIN;
            world[connectorX + 1][aYCoord + aLength - 1] = Tileset.MOUNTAIN;
        }

    }
}
