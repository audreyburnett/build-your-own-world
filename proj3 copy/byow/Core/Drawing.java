package byow.Core;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class Drawing {
    private final int yOff;
    /** The width of the window of this game. */
    private int width;
    /** The height of the window of this game. */
    private int height;
    private final int xOff;
    private String moves = "";
    public static File filename = new File("savedWorld.txt");
    public Drawing(TERenderer ter, int width, int height, int xOff, int yOff) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        ter.initialize(width, height, 0, 0 );
        this.width = width;
        this.height = height;
        this.xOff = xOff;
        this.yOff = yOff;
    }
    public void map(TETile[][] world, TERenderer ter){
        ter.renderFrame(world);
    }
    public void mainMenu(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(width / 2, 25, "BEGIN YOUR JOURNEY...");
        Font fontSmall = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontSmall);
        StdDraw.text(width / 2, 17, "New Game (N)");
        StdDraw.text(width / 2, 15, "Load Game (L)");
        StdDraw.text(width / 2, 13, "Quit (Q)");
        StdDraw.text(width / 2, 11, "Replay (R)");
        StdDraw.show();
        StdDraw.pause(10);
    }

    public void charSelectMenu() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(width / 2, 25, "SELECT YOUR CHARACTER");
        Font fontSmall = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontSmall);
        StdDraw.text(width / 2, 17, "Character (C)");
        StdDraw.text(width / 2, 15, "Quit (Q)");
        StdDraw.show();
        StdDraw.pause(10);
    }

    public void start(TERenderer ter) throws IOException {
        mainMenu();
        Avatar myPlayer = new Avatar();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char typed = StdDraw.nextKeyTyped();
                if (typed == ':') {
                    while (true) {
                        if (StdDraw.hasNextKeyTyped()) {
                            typed = StdDraw.nextKeyTyped();
                            if (typed == 'Q' | typed == 'q') {
                                System.exit(0);
                            } else {
                                break;
                            }
                        }
                    }
                }
                if (typed == 'N' || typed == 'n') {
                    seedPrompt();
                    String answer = seedInput();
                    drawFrame(answer);
                    moves += "n" + answer;
                    charSelectMenu();
                    while (true) {
                        if (StdDraw.hasNextKeyTyped()) {
                            typed = StdDraw.nextKeyTyped();
                            if (typed == 'C' || typed == 'c') {
                                charSelect();
                                char avatarSymbol = selected();
                                myPlayer = new Avatar(avatarSymbol);
                                moves += typed;
                                moves += myPlayer.avatarSymbol;
                                break;
                            }
                        }
                    }
                    TETile[][] world = Engine.interactWithInputString(moves);
                    ter.renderFrame(world);
                    while (true) {
                        double priorX = 0.0;
                        double priorY = 0.0;
                        double x = StdDraw.mouseX();
                        double y = StdDraw.mouseY();
                        while (x != priorX && y != priorY) {
                            mousePos(x, y, world, ter);
                            priorX = x;
                            priorY = y;
                        }
                        if (StdDraw.hasNextKeyTyped()) {
                            typed = StdDraw.nextKeyTyped();
                            if (typed == ':') {
                                while (true) {
                                    if (StdDraw.hasNextKeyTyped()) {
                                        typed = StdDraw.nextKeyTyped();
                                        if (typed == 'Q' || typed == 'q') {
                                            save(filename, moves);
                                            System.exit(0);
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (typed == 'w' || typed == 'a' || typed == 's' || typed == 'd') {
                                    moves += typed;
                                }
                                myPlayer.move(String.valueOf(typed), world, ter, myPlayer.avatar);
                            }
                        }
                    }
                }
                if (typed == 'L' || typed == 'l') {
                    String input = load(filename);
                    TETile[][] world = Engine.interactWithInputString(input);
                    while (true) {
                        double priorX = 0.0;
                        double priorY = 0.0;
                        double x = StdDraw.mouseX();
                        double y = StdDraw.mouseY();
                        while (x != priorX && y != priorY) {
                            mousePos(x, y, world, ter);
                            priorX = x;
                            priorY = y;
                        }
                        if (StdDraw.hasNextKeyTyped()) {
                            typed = StdDraw.nextKeyTyped();
                            if (typed == ':') {
                                while (true) {
                                    if (StdDraw.hasNextKeyTyped()) {
                                        typed = StdDraw.nextKeyTyped();
                                        if (typed == 'Q' || typed == 'q') {
                                            save(filename, moves);
                                            System.exit(0);
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if (typed == 'w' || typed == 'a' || typed == 's' || typed == 'd') {
                                    moves += typed;
                                }
                                myPlayer.move(String.valueOf(typed), world, ter, myPlayer.avatar);
                            }
                        }
                    }
                }
                if (typed == 'R' || typed == 'r') {
                    TETile[][] world = replay(filename, ter);
                    while (true) {
                        double priorX = 0.0;
                        double priorY = 0.0;
                        double x = StdDraw.mouseX();
                        double y = StdDraw.mouseY();
                        while (x != priorX && y != priorY) {
                            mousePos(x, y, world, ter);
                            priorX = x;
                            priorY = y;
                        }
                        if (StdDraw.hasNextKeyTyped()) {
                            typed = StdDraw.nextKeyTyped();
                            if (typed == ':') {
                                while (true) {
                                    if (StdDraw.hasNextKeyTyped()) {
                                        typed = StdDraw.nextKeyTyped();
                                        if (typed == 'Q' || typed == 'q') {
                                            System.exit(0);
                                        } else {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void charSelect(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(width / 2, 17, "@ (A)");
        StdDraw.text(width / 2, 15, "Tree (T)");
        StdDraw.text(width / 2, 13, "Flower (F)");
        StdDraw.text(width / 2, 10, "Grass (G)");
        StdDraw.show();
        StdDraw.pause(2000);
    }
    public Character selected() {
        String answer = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char typed = StdDraw.nextKeyTyped();
                if (typed == 'T' || typed == 't') {
                    return 'T';
                }
                if (typed == 'F' || typed == 'f') {
                    return 'F';
                }
                if (typed == 'G' || typed == 'g') {
                    return 'G';
                }
                if (typed == 'A' || typed == 'a') {
                    return 'a';
                }
            }
        }
    }
    public static void save(File filename, String input) throws IOException {
        FileWriter writer = new FileWriter(filename.getName(), true);
        BufferedWriter buffered = new BufferedWriter(writer);
        for (int i = 0; i < input.length(); i ++) {
            if (input.charAt(i) == 'n' || input.charAt(i) == 'N') {
                buffered.newLine();
                buffered.write(input.charAt(i));
            } else {
                buffered.write(input.charAt(i));
            }
        }
        buffered.close();
    }

    public String load(File filename) throws IOException {
        FileReader reader = new FileReader(filename.getName());
        LineNumberReader lineReader = new LineNumberReader(reader);
        String lineText = null;
        String input = "";
        while ((lineText = lineReader.readLine()) != null) {
            input = lineText;
        }
        return input;
    }

    public TETile[][] replay(File filename, TERenderer ter) throws IOException {
        FileReader reader = new FileReader(filename.getName());
        LineNumberReader lineReader = new LineNumberReader(reader);
        String lineText = null;
        String input = "";
        while ((lineText = lineReader.readLine()) != null) {
            input = lineText;
        }
        TETile[][] world = Engine.replayInteract(input);
        return world;
    }

    public void hudFrame(String s){
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 15);
        StdDraw.setFont(fontBig);
        StdDraw.text(10, this.height - 2, s);
        StdDraw.show();
    }

    public void mousePos(double x, double y, TETile [][] world, TERenderer ter){
        String s = "";
        int intX = (int) x;
        int intY = (int) y;
        if (world[intX][intY] == Tileset.MOUNTAIN) {
            s += "Mountain, HOPE YOU CAN CLIMB!";
        } else if (world[intX][intY] == Tileset.SAND) {
            s+= "Sand, IT'S HOT!";
        } else if (world[intX][intY] == Tileset.LOCKED_DOOR) {
            s+= "LOCKED DOOR, PLEASE UNLOCK!";
        } else {
            s+= "WATER, DON'T DROWN!";
        }
        hudFrame(s);
        ter.renderFrame(world);
    }

    public void seedPrompt(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(width / 2, 16, "PLEASE ENTER A SEED FOLLOWED BY S");
        StdDraw.show();
        StdDraw.pause(50);
    }
    public String seedInput(){
        String answer = "";
        Boolean sTyped = false;
        while(!sTyped) {
            if (StdDraw.hasNextKeyTyped()) {
                char typed = StdDraw.nextKeyTyped();
                answer += (typed);
                drawFrame(String.valueOf(answer));
                if(typed == 'S' || typed == 's') {
                    sTyped = true;
                }
            }
        }
        return answer;
    }
    public void showStringInput(){
        Boolean sTyped = false;
        while(!sTyped) {
            if (StdDraw.hasNextKeyTyped()) {
                char typed = StdDraw.nextKeyTyped();
                String s = String.valueOf(typed);
                drawFrame(s);
                if(typed == 'S' || typed == 's') {
                    sTyped = true;
                }
            }
        }
    }
    public String seed(){
        String answer = "";
        while(StdDraw.hasNextKeyTyped()) {
            char typed = StdDraw.nextKeyTyped();
            answer += typed;
        }
        String real = (answer.substring(0, answer.length() - 1));
        return real;
    }
    public void drawFrame(String s){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width / 2, this.height / 2, s);
        StdDraw.show();
        StdDraw.pause(100);
    }

    public void loadScreen(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(this.width / 2, 25, "BEGIN YOUR JOURNEY...");
        Font fontSmall = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontSmall);
        StdDraw.text(this.width / 2, 17, "New Game (N)");
        StdDraw.text(this.width / 2, 15, "Load Game (L)");
        StdDraw.text(this.width / 2, 13, "Quit (Q)");
        StdDraw.show();
        StdDraw.pause(1000000000);
    }

    public TETile[][] replayInteract(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        TETile[][] myWorld = new TETile[width][height];
        ArrayList<Character> inputSplit = new ArrayList();
        for (char ch : input.toCharArray()) {
            inputSplit.add(ch);
        }
        String seed = "";
        Avatar myPlayer = new Avatar();
        int j = 0;
        while (true) {
            if (inputSplit.get(j).equals('n') || inputSplit.get(j).equals('N')) {
                j = j + 1;
                continue;
            }
            if (inputSplit.get(j).equals('s') || inputSplit.get(j).equals('S')) {
                j = j + 1;
                break;
            } else {
                seed += inputSplit.get(j);
                j = j + 1;
            }
        }
        Long longSeed = Long.valueOf(seed);
        bigWorld big = new bigWorld();
        big.buildBigWorld(height, width, longSeed, myWorld, myPlayer);
        String moves = "";
        int h = j;
        while (h < inputSplit.size()) {
            if (inputSplit.get(h).equals('c') || inputSplit.get(h).equals('C')) {
                char avatarSymbol = inputSplit.get(h + 1);
                int x = myPlayer.avTrackerGetter().x;
                int y = myPlayer.avTrackerGetter().y;
                myPlayer = new Avatar(avatarSymbol);
                myPlayer.placeAvatar(x, y, myPlayer.avatar, myWorld);
                ter.renderFrameSlow(myWorld);
                h = h + 2;
                continue;
            } else if (!(inputSplit.get(h).equals(':')) || !(inputSplit.get(h).equals('q')) || !(inputSplit.get(h).equals('l'))) {
                String nextMove = String.valueOf(inputSplit.get(h));
                myPlayer.move(nextMove, myWorld, ter, myPlayer.avatar);
                ter.renderFrameSlow(myWorld);
                moves = moves + nextMove;
                h = h + 1;
            } else if ((inputSplit.get(h).equals(':')) || (inputSplit.get(h).equals('q')) || (inputSplit.get(h).equals('l'))) {
                h = h + 1;
            }
        }
        ter.renderFrame(myWorld);
        return myWorld;
    }
}