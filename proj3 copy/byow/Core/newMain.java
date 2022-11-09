package byow.Core;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

public class newMain {
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
        Random random = new Random();
        for(int i = 0; i < 15; i ++) {
            int x = RandomUtils.uniform(random, 50);
            int y = RandomUtils.uniform(random, 25);
            int length = RandomUtils.uniform(random, 10);
            int width = RandomUtils.uniform(random, 10);
            bigWorld.buildRoom(x, y, world, length, width);
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        bigWorld.buildRoom(20, 20, world, 6, 3);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
