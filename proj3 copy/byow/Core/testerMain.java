package byow.Core;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class testerMain {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        bigWorld.buildRoom(1, 1, world, 5, 4);
        bigWorld.buildRoom(2, 8, world, 4, 3);
        room roomA = new room(1,1, 5, 4);
        room roomB = new room(2,8, 4, 3);
        room.connect(roomA, roomB, world);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
