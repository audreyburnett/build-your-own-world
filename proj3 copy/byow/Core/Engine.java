package byow.Core;
import java.util.Random;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
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
//        TERenderer ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WATER;
            }
        }
        Long seed = Long.valueOf(input.substring(1, input.length() - 1));
        Random rnd = new Random();
        rnd.setSeed(seed);
        int numRooms = rnd.nextInt(50);
        int prevXCoord = 0;
        int prevYCoord = 0;
        int prevLength = 0;
        int prevWidth = 0;
        for(int i = 0; i < numRooms; i ++) {
            room roomPrev = new room(prevXCoord, prevYCoord, prevLength, prevWidth);
            int xCoord = rnd.nextInt(70);
            int yCoord = rnd.nextInt(20);
            int width = rnd.nextInt(10);
            int length = rnd.nextInt(10);
            if (width < 3 || length < 3) {
                while (width < 3 || length < 3) {
                    width = rnd.nextInt(10);
                    length = rnd.nextInt(10);
                }
            }
            room roomCurr = new room(xCoord, yCoord, width, length);
            bigWorld.buildRoom(xCoord, yCoord, world, length, width);
            if(i != 0){
                room.connect(roomPrev, roomCurr, world);
            }
            prevXCoord = xCoord;
            prevYCoord = yCoord;
            prevLength = length;
            prevWidth = width;

        }


//        TETile[][] finalWorldFrame = null;
        return world;
    }
}
