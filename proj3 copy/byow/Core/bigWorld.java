package byow.Core;
import java.util.*;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.Core.RandomUtils;
import java.lang.Math;

import static java.lang.Math.abs;

public class bigWorld {

    private HashSet<Room> roomTracker = new HashSet<>();

    public bigWorld(){}
    public TETile[][] buildBigWorld(int worldHeight, int worldWidth, long seed, TETile[][] world, Avatar myPlayer) {
        for (int x = 0; x < worldWidth; x += 1) {
            for (int y = 0; y < worldHeight; y += 1) {
                world[x][y] = Tileset.WATER;
            }
        }
        Random rnd = new Random();
        rnd.setSeed(seed);
        int numRooms = rnd.nextInt(2000);
//        System.out.println(numRooms);
        for(int i = 0; i < numRooms; i ++) {
            int xCoord = rnd.nextInt(70);
            int yCoord = rnd.nextInt(20);
            int width = rnd.nextInt(10);
            int length = rnd.nextInt(10);
            if (width < 4 || length < 4) {
                while (width < 4 || length < 4) {
                    width = rnd.nextInt(10);
                    length = rnd.nextInt(10);
                }
            }
//            System.out.println(xCoord + yCoord + width + length);
            Room roomCurr = new Room(xCoord, yCoord, length, width);
            if (!overlap(roomCurr, world)) {
                buildRoom(xCoord, yCoord, world, length, width);
                addRoom(roomCurr);
            }
        }
        List<Room> sortedRoomTracker = sort(roomTracker);
        for (int i = 0; i < sortedRoomTracker.size() - 1; i++ ) {
            Room a = sortedRoomTracker.get(i);
            Room b = sortedRoomTracker.get(i+1);
            int XOrigin = a.x + rnd.nextInt(a.width - 2) + 1;
            int YOrigin = a.y + rnd.nextInt(a.length - 2) + 1;
            int XDestination = b.x + rnd.nextInt(b.width - 2) + 1;
            int YDestination = b.y + rnd.nextInt(b.length - 2) + 1;
//            System.out.println(XOrigin + YOrigin + XDestination + YDestination);
            buildHallway(XOrigin, YOrigin, XDestination, YDestination, world);
        }

        int xAv = rnd.nextInt(80);
        int yAv = rnd.nextInt(30);
        while(world[xAv][yAv] != Tileset.MOUNTAIN) {
            xAv = rnd.nextInt(80);
            yAv = rnd.nextInt(30);
        }
//        System.out.println(xAv + yAv);
        myPlayer.placeAvatar(xAv, yAv, myPlayer.avatar, world);
        return world;
    }

    public class Room {
        private int x;
        private int y;
        private int length;
        private int width;

        public Room(int x, int y, int length, int width) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.width = width;
        }
    }

    private void buildRoom(int x, int y, TETile[][] world, int length, int width){
        for(int start = x; start < x + width; start ++){
            world[start][y] = Tileset.SAND;
            world[start][abs(y + length - 1)] = Tileset.SAND;

        }
        for(int upStart = y; upStart < y + length; upStart ++){
            world[x][upStart] = Tileset.SAND;
            world[abs(x+width - 1)][upStart] = Tileset.SAND;
        }
        /** builds floors*/
        for(int start = x + 1; start < x + width - 1; start ++){
            for (int upStart = y + 1; upStart < y + length - 1; upStart ++) {
                world[start][upStart] = Tileset.MOUNTAIN;
            }
        }
    }

    private void buildHallway(int oX, int oY, int dX, int dY, TETile[][] world) {
        int xDistance = oX - dX;
        int yDistance = oY - dY;
        int xdirection = 0;
        int ydirection = 0;
        if (xDistance < 0) {
            xdirection = 1;
        } else {
            xdirection = -1;
        }
        if (yDistance < 0) {
            ydirection = 1;
        } else {
            ydirection = -1;
        }
        int xIncrement = 0;
        int yIncrement = 0;
        for (int x = 0; x < abs(xDistance); x++) {
            world[oX + xIncrement][oY] = Tileset.MOUNTAIN;
            xIncrement += xdirection;
        }
        for (int y = 0; y < abs(yDistance); y++) {
            world[oX + xIncrement][oY + yIncrement] = Tileset.MOUNTAIN;
            yIncrement += ydirection;
        }
        buildHorizontalWalls(oX, oY, abs(xDistance), xdirection, world);
        buildVerticalWalls(oX + xIncrement, oY, abs(yDistance), ydirection, world);
    }

    private void buildHorizontalWalls(int xOrigin, int yOrigin, int length, int direction, TETile[][] world) {
        for (int i = 0; i <= length + 1; i ++) {
            if (world[xOrigin + (direction * i)][yOrigin + direction] == Tileset.WATER) {
                world[xOrigin + (direction * i)][yOrigin + direction] = Tileset.SAND;
            }
            if (world[xOrigin + (direction * i)][yOrigin - direction] == Tileset.WATER) {
                world[xOrigin + (direction * i)][yOrigin - direction] = Tileset.SAND;
            }
        }
    }

    private void buildVerticalWalls(int xOrigin, int yOrigin, int length, int direction, TETile[][] world) {
        for (int i = 0; i <= length; i ++) {
            if (world[xOrigin - 1][yOrigin + (direction * i)] == Tileset.WATER) {
                world[xOrigin - 1][yOrigin + (direction * i)] = Tileset.SAND;
            }
            if (world[xOrigin + 1][yOrigin + (direction * i)] == Tileset.WATER) {
                world[xOrigin + 1][yOrigin + (direction * i)] = Tileset.SAND;
            }
        }
    }

    private void addRoom(Room room) {
        roomTracker.add(room);
    }

    private List<Room> sort(HashSet<Room> roomTracker) {
        List<Room> roomList = new ArrayList<Room>(roomTracker);
        Comparator<Room> c = new bigWorld.sortByX();
        Collections.sort(roomList, c);
        return roomList;
    }

    class sortByX implements Comparator<Room> {
        public int compare(Room a, Room b) {
            if (a.x - b.x == 0) {
                return a.y - b.y;
            } else {
                return a.x - b.x;
            }
        }
    }

    private Boolean overlap(Room a, TETile[][] world) {
        for (int i = a.x; i < a.x + a.width; i++) {
            for (int j = a.y; j < a.y + a.length; j++) {
                if (world[i][j] != Tileset.WATER) {
                    return true;
                }
            }
        }
        return false;
    }
}






