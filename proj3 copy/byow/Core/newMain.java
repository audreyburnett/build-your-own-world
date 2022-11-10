package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class newMain {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] finalWorld = Engine.interactWithInputString("n62s");
//        Random random = new Random();
//        Long seed = random.nextLong(10000);
//        for(int i = 0; i < 15; i ++) {
//            int x = RandomUtils.uniform(random, 50);
//            int y = RandomUtils.uniform(random, 25);
//            int length = RandomUtils.uniform(random, 10);
//            int width = RandomUtils.uniform(random, 10);
//            bigWorld.buildRoom(x, y, world, length, width);
//        }

        // fills in a block 14 tiles wide by 4 tiles tall
//        bigWorld.buildRoom(20, 20, world, 6, 3);

        // draws the world to the screen
        ter.renderFrame(finalWorld);
    }
}
