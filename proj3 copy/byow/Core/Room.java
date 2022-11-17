package byow.Core;

import byow.TileEngine.TETile;

import java.util.HashSet;

import byow.TileEngine.Tileset;
import java.util.HashMap;
import java.util.ArrayList;

public class Room {
    private int x;
    private int y;
    private int length;
    private int width;
    private static HashSet<Room> roomTracker = new HashSet<>();
    private static HashSet<Coordinate> doorTracker = new HashSet<Coordinate>();

    public Room(int x, int y, int length, int width) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
        this.roomTracker = roomTracker;
    }
    /** returns arraylist with orig coord first and first wall hit coord second*/
    public static ArrayList hallwayFinderRight(Room origin, TETile[][] world){
        int right = origin.x + origin.width - 1;
        int bottom = origin.y;
        int top = origin.y + origin.length -1;
        ArrayList answer = new ArrayList();
        for(int wallY = bottom + 1; wallY < top; wallY++){
            int currY = wallY;
            int currX = right;
            for(int probe = right; probe < 79; probe++){
                if(world[probe + 1][wallY] == Tileset.SAND){
                    Coordinate originCoord = new Coordinate(currX, currY);
                    Coordinate destinationCoord = new Coordinate(probe + 1, wallY);
                    answer.add(originCoord);
                    answer.add(destinationCoord);
                    return answer;
                }
            }
        }
        return null;
    }
    public static ArrayList hallwayFinderUp(Room origin, TETile[][] world){
        int top = origin.y + origin.length - 1;
        int left = origin.x;
        int right = origin.x + origin.width - 1;
        ArrayList answer = new ArrayList();
        for(int wallX = left + 1; wallX < right; wallX ++){
            int currX = wallX;
            int currY = top;
            for(int probe = top; probe < 29; probe++){
                if(world[wallX][probe + 1] == Tileset.SAND){
                    Coordinate originCoord = new Coordinate(currX, currY);
                    Coordinate destinationCoord = new Coordinate(wallX, probe + 1);
                    answer.add(originCoord);
                    answer.add(destinationCoord);
                    return answer;
                }
            }
        }
        return null;
    }
    public static void hallwayMakerRight(TETile[][] world){
        for(Room origin : roomTracker){
            if(hallwayFinderRight(origin, world) == null){
                continue;
            }
            else{
                ArrayList coords = hallwayFinderRight(origin, world);
                hallway.connectRight(coords, world);
            }
        }
    }
    public static void hallwayMakerUp(TETile[][] world){
        for(Room origin : roomTracker){
            if(hallwayFinderUp(origin, world) == null){
                continue;
            }
            else{
                ArrayList coords = hallwayFinderUp(origin, world);
                hallway.connectUp(coords, world);
            }
        }
    }

    public static Boolean noOverlap(Room a) {
        for (Room i : roomTracker) {
            int left = i.x;
            int right = i.x + i.width - 1;
            int bottom = i.y;
            int top = i.y + i.length - 1;
            /** upper right */
            if (a.x <= right && a.y <= top && a.y + a.length - 1 >= top && a.y >= bottom) {
                return false;
            }
            /** mid right */
            else if (a.x <= right && a.y <= top && a.length - 1 <= top && a.y >= bottom) {
                return false;
            }
            /** bottom right */
            else if (a.x <= right && a.y <= bottom && a.y + a.length - 1 >= bottom && a.y + a.length - 1 <= top) {
                return false;
            }
            /** bottom mid */
            else if (a.y <= bottom && a.x >= left && a.x + a.width - 1 <= right && a.y + a.length - 1 >= bottom && a.y + a.length - 1 <= top) {
                return false;
            }
            /** bottom left */
            else if (a.x <= left && a.y <= bottom && a.x + a.width - 1 >= left && a.y + a.length - 1 >= bottom) {
                return false;
            }
            /** mid left */
            else if (a.x <= left && a.x + a.width - 1 >= left && a.y >= bottom && a.y + a.length - 1 <= top) {
                return false;
            }
            /** upper left */
            else if (a.x <= left && a.x + a.width - 1 >= left && a.y <= top && a.y + a.length - 1 >= top) {
                return false;
            }
            /** upper mid */
            else if (a.x >= left && a.x + a.width - 1 <= right && a.y <= top && a.y + a.length - 1 >= top) {
                return false;
            }
        }
        return true;
    }

    public static void roomTrackerAdder(Room room) {
        roomTracker.add(room);
    }
}


