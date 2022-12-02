//package byow.Core;
//import byow.TileEngine.Tileset;
//import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.Out;
//import edu.princeton.cs.algs4.StdDraw;
//import java.awt.Color;
//import java.awt.Font;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Random;
//import byow.TileEngine.TERenderer;
//import byow.TileEngine.TETile;
//
//public class drawing {
//    private final int yOff;
//    /** The width of the window of this game. */
//    private int width;
//    /** The height of the window of this game. */
//    private int height;
//    private final int xOff;
//    /** The current round the user is on. */
//    private int round;
//    /** The Random object used to randomly generate Strings. */
//    private Random rand;
//    /** Whether or not the game is over. */
//    private boolean gameOver;
//    private String answer;
//    private String moves = "";
//    private Out out;
//    public static File filename = new File("savedWorld.txt");
//    public drawing(TERenderer ter, int width, int height, int xOff, int yOff) {
//        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
//         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
//         */
//        ter.initialize(width, height, 0, 0 );
//        this.width = width;
//        this.height = height;
//        this.xOff = xOff;
//        this.yOff = yOff;
//    }
//    public void map(TETile[][] world, TERenderer ter){
//        ter.renderFrame(world);
//    }
//    public void mainMenu(){
//        StdDraw.clear(Color.BLACK);
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontBig);
//        StdDraw.text(width / 2, 25, "BEGIN YOUR JOURNEY...");
//        Font fontSmall = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontSmall);
//        StdDraw.text(width / 2, 17, "New Game (N)");
//        StdDraw.text(width / 2, 15, "Load Game (L)");
//        StdDraw.text(width / 2, 13, "Quit (Q)");
//        StdDraw.text(width / 2, 11, "Replay (R)");
//        StdDraw.text(width / 2, 9, "Character (C)");
//        StdDraw.show();
//        StdDraw.pause(10);
//    }
//    public void start(TERenderer ter) {
//        Boolean Q = false;
//        mainMenu();
//        String avatar = "a";
//        TETile avatarTile = Tileset.AVATAR;
//        while (!Q) {
//            if (StdDraw.hasNextKeyTyped()) {
//                char typed = StdDraw.nextKeyTyped();
////                if (typed == ':') {
////                    while (true) {
////                        if (StdDraw.hasNextKeyTyped()) {
////                            typed = StdDraw.nextKeyTyped();
////                            if (typed == 'Q' | typed == 'q') {
////                                System.exit(0);
////                            } else {
////                                break;
////                            }
////                        }
////                    }
////                }
//                typed = quit(typed, avatar);
//                if (typed == 'C' || typed == 'c') {
//                    charSelect();
//                    avatar = selected();
//                    mainMenu();
//                    continue;
//                }
//                if (typed == 'N' || typed == 'n') {
//                    moves = "";
//                    seedPrompt();
//                    answer = seedInput();
//                    drawFrame(answer);
//                    TETile[][] world = worldBuilder(answer, avatar);
//                    ter.renderFrame(world);
//                    while (true) {
//                        double priorX = 0.0;
//                        double priorY = 0.0;
//                        double x = StdDraw.mouseX();
//                        double y = StdDraw.mouseY();
//                        while (x != priorX && y != priorY) {
//                            mousePos(x, y, world, ter);
//                            priorX = x;
//                            priorY = y;
//                        }
//                        if (StdDraw.hasNextKeyTyped()) {
//                            typed = StdDraw.nextKeyTyped();
//                            if (typed == ':') {
//                                while (true) {
//                                    if (StdDraw.hasNextKeyTyped()) {
//                                        typed = StdDraw.nextKeyTyped();
//                                        if (typed == 'Q' || typed == 'q') {
//                                            out = new Out(filename.getName());
//                                            save(filename, out, answer, moves, avatar);
//                                            System.exit(0);
//                                        } else {
//                                            break;
//                                        }
//                                    }
//                                }
//                            } else {
//                                if (typed == 'w' || typed == 'a' || typed == 's' || typed == 'd') {
//                                    moves += typed;
//                                }
//                                if (avatar.equals("a")) {
//                                    avatarTile = Tileset.AVATAR;
//                                }
//                                if (avatar.equals("T")) {
//                                    avatarTile = Tileset.TREE;
//                                }
//                                if (avatar.equals("F")) {
//                                    avatarTile = Tileset.FLOWER;
//                                }
//                                if (avatar.equals("G")) {
//                                    avatarTile = Tileset.GRASS;
//                                }
//                                Movement.move(String.valueOf(typed), world, ter, avatarTile);
//                            }
//                        }
//                    }
//                }
//                if (typed == 'L' || typed == 'l') {
//                    TETile[][] world = load(filename, ter, avatar);
//                    ter.renderFrame(world);
//                    while (true) {
//                        double priorX = 0.0;
//                        double priorY = 0.0;
//                        double x = StdDraw.mouseX();
//                        double y = StdDraw.mouseY();
//                        while (x != priorX && y != priorY) {
//                            mousePos(x, y, world, ter);
//                            priorX = x;
//                            priorY = y;
//                        }
//                        if (StdDraw.hasNextKeyTyped()) {
//                            typed = StdDraw.nextKeyTyped();
//                            if (typed == ':') {
//                                while (true) {
//                                    if (StdDraw.hasNextKeyTyped()) {
//                                        typed = StdDraw.nextKeyTyped();
//                                        if (typed == 'Q' || typed == 'q') {
//                                            out = new Out(filename.getName());
//                                            save(filename, out, answer, moves, avatar);
//                                            System.exit(0);
//                                        } else {
//                                            break;
//                                        }
//                                    }
//                                }
//                            } else {
//                                if (typed == 'w' || typed == 'a' || typed == 's' || typed == 'd') {
//                                    moves += typed;
//                                }
//                                if (avatar.equals("a")) {
//                                    avatarTile = Tileset.AVATAR;
//                                }
//                                if (avatar.equals("T")) {
//                                    avatarTile = Tileset.TREE;
//                                }
//                                if (avatar.equals("F")) {
//                                    avatarTile = Tileset.FLOWER;
//                                }
//                                if (avatar.equals("G")) {
//                                    avatarTile = Tileset.GRASS;
//                                }
//                                Movement.move(String.valueOf(typed), world, ter, avatarTile);
//                            }
//                        }
//                    }
//                }
//                if (typed == 'R' || typed == 'r') {
//                    TETile[][] world = replay(filename.getName(), ter, avatar);
//                    ter.renderFrame(world);
//                        while (true) {
//                                double priorX = 0.0;
//                                double priorY = 0.0;
//                                double x = StdDraw.mouseX();
//                                double y = StdDraw.mouseY();
//                                while (x != priorX && y != priorY) {
//                                    mousePos(x, y, world, ter);
//                                    priorX = x;
//                                    priorY = y;
//                                }
//                            if (StdDraw.hasNextKeyTyped()) {
//                                typed = StdDraw.nextKeyTyped();
//                                if (typed == ':') {
//                                    while (true) {
//                                        if (StdDraw.hasNextKeyTyped()) {
//                                            typed = StdDraw.nextKeyTyped();
//                                            if (typed == 'Q' || typed == 'q') {
//                                                out = new Out(filename.getName());
//                                                save(filename, out, answer, moves, avatar);
//                                                System.exit(0);
//                                            } else {
//                                                break;
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    if (typed == 'w' || typed == 'a' || typed == 's' || typed == 'd') {
//                                        moves += typed;
//                                    }
////                                    movement.move(String.valueOf(typed), world, ter);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        public char quit(char typed, String avatar) {
//            if (typed == ':') {
//                while (true) {
//                    if (StdDraw.hasNextKeyTyped()) {
//                        typed = StdDraw.nextKeyTyped();
//                        if (typed == 'Q' | typed == 'q') {
//                            System.exit(0);
//                        } else {
//                            break;
//                        }
//                    }
//                }
//            }
//            return typed;
//        }
//
//
//
//
//
//    public void charSelect(){
//        StdDraw.clear(Color.BLACK);
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontBig);
//        StdDraw.text(width / 2, 17, "@ (A)");
//        StdDraw.text(width / 2, 15, "Tree (T)");
//        StdDraw.text(width / 2, 13, "Flower (F)");
//        StdDraw.text(width / 2, 10, "Grass (G)");
//        StdDraw.show();
//        StdDraw.pause(2000);
//    }
//    public String selected() {
//        String answer = "";
//        while (true) {
//            if (StdDraw.hasNextKeyTyped()) {
//                char typed = StdDraw.nextKeyTyped();
//                if (typed == 'T') {
//                    return "T";
//                }
//                if (typed == 'F') {
//                    return "F";
//                }
//                if (typed == 'G') {
//                    return "G";
//                }
//                if (typed == 'A') {
//                    return "a";
//                }
//            }
//        }
//    }
//
//    public static void save(File filename, Out out, String seed, String moves, String avatar) {
//        out.println(seed);
//        out.println(avatar);
//        out.println(moves);
//    }
//    public TETile[][] load(File filename, TERenderer ter, String avatar) {
//        String previousTurn = "";
//        In in = new In(filename);
//        String seed = in.readLine();
//        drawFrame(seed);
//        avatar = in.readLine();
//        TETile avatarTile = Tileset.AVATAR;
//        if (avatar.equals("a")) {
//            avatarTile = Tileset.AVATAR;
//        }
//        if (avatar.equals("T")) {
//            avatarTile = Tileset.TREE;
//        }
//        if (avatar.equals("F")) {
//            avatarTile = Tileset.FLOWER;
//        }
//        if (avatar.equals("G")) {
//            avatarTile = Tileset.GRASS;
//        }
//        TETile[][] world = worldBuilder(seed, avatar);
//        String[] moves = in.readLine().split("");
//        for (int i = 0; i < moves.length; i++) {
//            Movement.move(moves[i], world, ter, avatarTile);
//            previousTurn += moves[i];
//        }
//        Out out = new Out(filename.getName());
//        save(filename, out, seed, previousTurn, avatar);
//        return world;
//    }
//
//    public TETile[][] replay(String filename, TERenderer ter, String avatar) {
//        In in = new In(filename);
//        String seed = in.readLine();
//        drawFrame(seed);
//        avatar = in.readLine();
//        TETile avatarTile = Tileset.AVATAR;
//        if (avatar.equals("a")) {
//            avatarTile = Tileset.AVATAR;
//        }
//        if (avatar.equals("T")) {
//            avatarTile = Tileset.TREE;
//        }
//        if (avatar.equals("F")) {
//            avatarTile = Tileset.FLOWER;
//        }
//        if (avatar.equals("G")) {
//            avatarTile = Tileset.GRASS;
//        }
//        TETile[][] world = worldBuilder(seed, avatar);
//        String[] moves = in.readLine().split("");
//        for (int i = 0; i < moves.length; i++) {
//            Movement.replayMove(moves[i], world, ter, avatarTile);
//        }
//        return world;
//    }
//
//    public void hudFrame(String s){
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 15);
//        StdDraw.setFont(fontBig);
//        StdDraw.text(10, this.height - 2, s);
//        StdDraw.show();
//    }
//
//    public void mousePos(double x, double y, TETile [][] world, TERenderer ter){
//        String s = "";
//        int intX = (int) x;
//        int intY = (int) y;
//        if (world[intX][intY] == Tileset.MOUNTAIN) {
//            s += "Mountain, HOPE YOU CAN CLIMB!";
//        } else if (world[intX][intY] == Tileset.SAND) {
//            s+= "Sand, IT'S HOT!";
//        } else if (world[intX][intY] == Tileset.LOCKED_DOOR) {
//            s+= "LOCKED DOOR, PLEASE UNLOCK!";
//        } else {
//            s+= "WATER, DON'T DROWN!";
//        }
//        hudFrame(s);
//        ter.renderFrame(world);
//    }
//
//    public void seedPrompt(){
//        StdDraw.clear(Color.BLACK);
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontBig);
//        StdDraw.text(width / 2, 16, "PLEASE ENTER A SEED FOLLOWED BY S");
//        StdDraw.show();
//        StdDraw.pause(50);
//    }
//    public String seedInput(){
//        String answer = "";
//        Boolean sTyped = false;
//        while(!sTyped) {
//            if (StdDraw.hasNextKeyTyped()) {
//                char typed = StdDraw.nextKeyTyped();
//                answer += (typed);
//                drawFrame(String.valueOf(answer));
//                if(typed == 'S' || typed == 's') {
//                    sTyped = true;
//                }
//            }
//        }
//        return answer;
//    }
//    public void showStringInput(){
//        Boolean sTyped = false;
//        while(!sTyped) {
//            if (StdDraw.hasNextKeyTyped()) {
//                char typed = StdDraw.nextKeyTyped();
//                String s = String.valueOf(typed);
//                drawFrame(s);
//                if(typed == 'S' || typed == 's') {
//                    sTyped = true;
//                }
//            }
//        }
//    }
//    public String seed(){
//        String answer = "";
//        while(StdDraw.hasNextKeyTyped()) {
//            char typed = StdDraw.nextKeyTyped();
//            answer += typed;
//        }
//        String real = (answer.substring(0, answer.length() - 1));
//        return real;
//    }
//    public void drawFrame(String s){
//        StdDraw.clear(Color.BLACK);
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontBig);
//        StdDraw.text(this.width / 2, this.height / 2, s);
//        StdDraw.show();
//        StdDraw.pause(100);
//    }
//    public TETile[][] worldBuilder(String seed, String avatar){
//        TETile[][] world = new TETile[width][height];
//        for (int x = 0; x < width; x += 1) {
//            for (int y = 0; y < height; y += 1) {
//                world[x][y] = Tileset.WATER;
//            }
//        }
//        String s = (seed.substring(0, seed.length() - 1));
//        Random rnd = new Random();
//        Long longSeed = Long.valueOf(s);
//        rnd.setSeed(longSeed);
//        int numRooms = rnd.nextInt(2000);
//        int prevXCoord = 0;
//        int prevYCoord = 0;
//        int prevLength = 0;
//        int prevWidth = 0;
//        for(int i = 0; i < numRooms; i ++) {
//            Room roomPrev = new Room(prevXCoord, prevYCoord, prevLength, prevWidth);
//            int xCoord = rnd.nextInt(70);
//            int yCoord = rnd.nextInt(20);
//            int width = rnd.nextInt(6);
//            int length = rnd.nextInt(6);
//            if (width < 3 || length < 3) {
//                while (width < 3 || length < 3) {
//                    width = rnd.nextInt(10);
//                    length = rnd.nextInt(10);
//                }
//            }
//            Room roomCurr = new Room(xCoord, yCoord, length, width);
//            if (Room.overlap(roomCurr, world)) {
//                bigWorld.buildRoom(xCoord, yCoord, world, length, width);
//                Room.addRoom(roomCurr);
//            }
//        }
//        Room.hallwayMakerRight(world);
//        Room.hallwayMakerUp(world);
//        bigWorld.worldAdjust(world);
//        int xDoor = rnd.nextInt(80);
//        int yDoor = rnd.nextInt(30);
//        int xAv = rnd.nextInt(80);
//        int yAv = rnd.nextInt(30);
//        while(world[xDoor][yDoor] != Tileset.SAND){
//            xDoor = rnd.nextInt(80);
//            yDoor = rnd.nextInt(30);
//        }
//        world[xDoor][yDoor] = Tileset.LOCKED_DOOR;
//        while(world[xAv][yAv] != Tileset.MOUNTAIN){
//            xAv = rnd.nextInt(80);
//            yAv = rnd.nextInt(30);
//        }
//        if (avatar.equals("a")) {
//            world[xAv][yAv] = Tileset.AVATAR;
//        }
//        if (avatar.equals("T")) {
//            world[xAv][yAv] = Tileset.TREE;
//        }
//        if (avatar.equals("F")){
//            world[xAv][yAv] = Tileset.FLOWER;
//        }
//        if (avatar.equals("G")) {
//            world[xAv][yAv] = Tileset.GRASS;
//        }
//        bigWorld.avTrackerAdder(xAv, yAv);
//        return world;
//    }
//    public void loadScreen(){
//        StdDraw.clear(Color.BLACK);
//        StdDraw.setPenColor(Color.WHITE);
//        Font fontBig = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontBig);
//        StdDraw.text(this.width / 2, 25, "BEGIN YOUR JOURNEY...");
//        Font fontSmall = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(fontSmall);
//        StdDraw.text(this.width / 2, 17, "New Game (N)");
//        StdDraw.text(this.width / 2, 15, "Load Game (L)");
//        StdDraw.text(this.width / 2, 13, "Quit (Q)");
//        StdDraw.show();
//        StdDraw.pause(1000000000);
//    }
//}