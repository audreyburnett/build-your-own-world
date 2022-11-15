package byow.Core;

import byow.TileEngine.TETile;

import java.util.HashSet;

import byow.TileEngine.Tileset;

public class Room {
    private int x;
    private int y;
    private int length;
    private int width;
    private HashSet<Room> roomTracker= new HashSet<>();
    public Room(int x, int y, int length, int width){
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
        this.roomTracker.add(new Room(x,y,length,width));
    }
    public static void connect(Room roomA, Room roomB, TETile[][] world){
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

    public TETile[][] noOverlap(Room a, TETile[][] world) {
        for (Room i : roomTracker) {
            int left = i.x;
            int right = i.x + i.width;
            int bottom = i.y;
            int top = i.y + i.length;
            if (a.x  >= left && a.x <= right && a.y <= top && a.y >= bottom) {
                //mutateRoom
            } else if (a.x + a.width  >= left && a.x + a.width <= right && a.y <= top && a.y >= bottom) {

            } else if (a.x  >= left && a.x <= right && a.y + a.length <= top && a.y + a.length >= bottom) {

            } else if (a.x + a.width  >= left && a.x + a.width <= right && a.y + a.length <= top && a.y + a.length >= bottom) {

            }
        }
        return world;
    }

    public void mutateRoom() {
        //shrinks room if it overlaps?
    }


}

