package byow.Core;
import java.awt.*;
import java.util.Random;
import java.util.*;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
//    public static TETile[][] myWorld = new TETile[WIDTH][HEIGHT];

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
//        drawing canvas = new drawing(ter, 80, 30, 0, 0);
//        canvas.start(ter);
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     * <p>
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     * <p>
     * In other words, running both of these:
     * - interactWithInputString("n123sss:q")
     * - interactWithInputString("lww")
     * <p>
     * should yield the exact same world state as:
     * - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] myWorld = new TETile[WIDTH][HEIGHT];
        /** adds each char from the user input to an array list*/
        ArrayList seedSplit = new ArrayList();
        for (char ch : input.toCharArray()) {
            seedSplit.add(ch);
        }
        /**sets the string seed to the seed*/
        String seed = "";
        int j = 1;
        while (true) {
            if (seedSplit.get(j).equals('s') || seedSplit.get(j).equals('S')) {
                break;
            }
            else {
                seed += seedSplit.get(j);
                j++;
            }
        }
        /** letters is the length of n***s */
        int letters = j + 1;

        /** sets the seed and creates the first room/hallway in a random place*/
        Long longSeed = Long.valueOf(seed);

        bigWorld big = new bigWorld();
        big.buildBigWorld(HEIGHT, WIDTH, longSeed, myWorld);
        ter.renderFrame(myWorld);
        return myWorld;
    }
}
//        Random rnd = new Random();
//        rnd.setSeed(longSeed);
//        int numRooms = rnd.nextInt(2000);
//        for(int i = 0; i < numRooms; i ++) {
//            int xCoord = rnd.nextInt(70);
//            int yCoord = rnd.nextInt(20);
//            int width = rnd.nextInt(10);
//            int length = rnd.nextInt(10);
//            if (width < 3 || length < 3) {
//                while (width < 3 || length < 3) {
//                    width = rnd.nextInt(10);
//                    length = rnd.nextInt(10);
//                }
//            }
//            Room roomCurr = new Room(xCoord, yCoord, length, width);
//            if (!Room.overlap(roomCurr, world)) {
//                Room.buildRoom(xCoord, yCoord, world, length, width);
//                Room.addRoom(roomCurr);
//            }
//        }
//        Room.sort(Room.roomTracker);


//        Long longSeed = Long.valueOf(seed);
//        Random rnd = new Random();
//        rnd.setSeed(longSeed);
//        int numRooms = rnd.nextInt(2000);
//        int firstRoomLength = rnd.nextInt(10);
//        int firstRoomWidth = rnd.nextInt(10);
//        int firstRoomxCoord = rnd.nextInt(70);
//        int firstRoomyCoord = rnd.nextInt(20);
//        if (firstRoomWidth < 4 || firstRoomLength < 4) {
//            while (firstRoomWidth < 4 || firstRoomLength < 4) {
//                firstRoomWidth = rnd.nextInt(10);
//                firstRoomLength = rnd.nextInt(10);
//            }
//        }
//        int bound = 0;
//        if (firstRoomWidth < firstRoomLength) {
//            bound = firstRoomWidth;
//        } else {
//            bound = firstRoomLength;
//        }
//        int sideOfRoom = rnd.nextInt(bound);
//        int doorXCoord = 0;
//        int doorYCoord = 0;
//        String direction = "";
//
//        if (firstRoomyCoord < 4 || sideOfRoom == 0) {
//            doorXCoord = firstRoomxCoord + rnd.nextInt(firstRoomWidth - 2) + 1;
//            doorYCoord = firstRoomyCoord + firstRoomLength - 1;
//            direction = "north";
//        } else if (firstRoomyCoord + firstRoomLength - 1 > 26 || sideOfRoom == 1) {
//            doorXCoord = firstRoomxCoord + rnd.nextInt(firstRoomWidth - 2) + 1;
//            doorYCoord = firstRoomyCoord;
//            direction = "south";
//        } else if (firstRoomxCoord + firstRoomWidth - 1 > 76 || sideOfRoom == 2) {
//            doorXCoord = firstRoomxCoord;
//            doorYCoord = firstRoomyCoord + rnd.nextInt(firstRoomLength - 2) + 1;
//            direction = "west";
//        } else if (firstRoomxCoord < 4 || sideOfRoom == 3) {
//            doorXCoord = firstRoomxCoord + firstRoomWidth - 1;
//            doorYCoord = firstRoomyCoord + rnd.nextInt(firstRoomLength - 2) + 1;
//            direction = "east";
//        }
//
//        Room.buildRoom(firstRoomxCoord, firstRoomyCoord, world, firstRoomLength, firstRoomWidth, doorXCoord, doorYCoord);
//
//        int firstHallwayXCoord = doorXCoord;
//        int firstHallwayYCoord = doorYCoord;
//        int firstHallwayLength = rnd.nextInt(20);
//        int hallwayShape = rnd.nextInt(2);
//        boolean Lshaped = false;
//        if (hallwayShape == 1) {
//            Lshaped = true;
//        }
//
//        Room.buildHallway(firstHallwayXCoord, firstHallwayYCoord, firstHallwayLength, Lshaped, direction, world);
//
//        for(int i = 1; i < numRooms - 1; i ++) {
//            Room roomPrev = new Room(prevXCoord, prevYCoord, prevLength, prevWidth);
//            int xCoord = rnd.nextInt(69);
//            int yCoord = rnd.nextInt(19);
//            int width = rnd.nextInt(10);
//            int length = rnd.nextInt(10);
//            if (width < 3 || length < 3) {
//                while (width < 3 || length < 3) {
//                    width = rnd.nextInt(10);
//                    length = rnd.nextInt(10);
//                }
//            }
//            Room roomCurr = new Room(xCoord, yCoord, length, width);
//            if (Room.noOverlap(roomCurr)) {
//                bigWorld.buildRoom(xCoord, yCoord, world, length, width);
//                Room.roomTrackerAdder(roomCurr);
//            }
//        }
//        /** ?? */
//        Room.hallwayMakerRight(world);
//        Room.hallwayMakerUp(world);
//        bigWorld.worldAdjust(world);
//        /** finds random x and y to put locked door*/
//        int xDoor = rnd.nextInt(80);
//        int yDoor = rnd.nextInt(30);
//        int xAv = rnd.nextInt(80);
//        int yAv = rnd.nextInt(30);
//        while(world[xDoor][yDoor] != Tileset.SAND){
//            xDoor = rnd.nextInt(80);
//            yDoor = rnd.nextInt(30);
//        }
//        world[xDoor][yDoor] = Tileset.LOCKED_DOOR;
//        /** finds random place to put avatar */
//        while(world[xAv][yAv] != Tileset.MOUNTAIN){
//            xAv = rnd.nextInt(80);
//            yAv = rnd.nextInt(30);
//        }
//        world[xAv][yAv] = Tileset.AVATAR;
//        bigWorld.avTrackerAdder(xAv, yAv);
/** goes thru the users wasd moves and adds them to string */
//        String moves = "";
//        for(int h = letters; h < seedSplit.size(); h++){
//            String nextMove = String.valueOf(seedSplit.get(h));
//            movement.move(nextMove, world, ter);
//            moves = moves + nextMove;
//        }
/** saves seed and moves to text file if :q*/
//        if (moves.charAt(moves.length() - 1) == 'q' || moves.charAt(moves.length() - 1) == 'Q') {
//            if (moves.charAt(moves.length() - 2) == ':') {
//                drawing.save(fi, seed , moves);
//            }
//        }