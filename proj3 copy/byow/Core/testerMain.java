//package byow.Core;
//import byow.TileEngine.TERenderer;
//import byow.TileEngine.TETile;
//import byow.TileEngine.Tileset;
//
//public class testerMain {
//    private static final int WIDTH = 60;
//    private static final int HEIGHT = 30;
//
//    public static void main(String[] args) {
//        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
//        TERenderer ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);
//
//        // initialize tiles
//        TETile[][] world = new TETile[WIDTH][HEIGHT];
//        for (int x = 0; x < WIDTH; x += 1) {
//            for (int y = 0; y < HEIGHT; y += 1) {
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
//
//        // tests if 2 rooms connect
////        bigWorld.buildRoom(1, 1, world, 5, 4);
////        bigWorld.buildRoom(2, 8, world, 4, 3);
////        Room roomA = new Room(1,1, 5, 4);
////        Room roomB = new Room(2,8, 4, 3);
////        Room.connect(roomA, roomB, world);
//
//        //tests if overlap works
//        Room roomC = new Room(3,4, 4, 3);
//        Room roomD = new Room(24,6, 5, 3);
//        bigWorld.buildRoom(3, 5, world, 4, 3);
//        Room.roomTrackerAdder(roomC);
//        if (!(Room.noOverlap(roomD))) {
//            bigWorld.buildRoom(24, 6, world, 5, 3);
//        }
//
//
//
//        // draws the world to the screen
//        ter.renderFrame(world);
//    }
//}
