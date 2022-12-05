package byow.Core;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.io.*;

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
                    TETile[][] world = load(filename);
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
                    replay(filename, ter);
                    while (true) {
//                                double priorX = 0.0;
//                                double priorY = 0.0;
//                                double x = StdDraw.mouseX();
//                                double y = StdDraw.mouseY();
//                                while (x != priorX && y != priorY) {
//                                    mousePos(x, y, world, ter);
//                                    priorX = x;
//                                    priorY = y;
//                                }
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
                if (typed == 'T') {
                    return 'T';
                }
                if (typed == 'F') {
                    return 'F';
                }
                if (typed == 'G') {
                    return 'G';
                }
                if (typed == 'A') {
                    return 'a';
                }
            }
        }
    }
    public static void save(File filename, String input) throws IOException {
//        In in = new In(filename.getName());
//        in.readAll();
//        Out out = new Out(filename.getName());
//        out.println(input);

        FileWriter writer = new FileWriter(filename.getName(), true);
        BufferedWriter buffered = new BufferedWriter(writer);
        for (int i = 0; i < input.length(); i ++) {
            if (input.charAt(i) == 'n' || input.charAt(i) == 'N') {
//                System.out.println("true");
                buffered.newLine();
                buffered.write(input.charAt(i));
            } else {
                buffered.write(input.charAt(i));
            }
        }
        buffered.close();
    }

    //    public static void loadSave(File filename, String input) {
//        Out out = new Out(filename.getName());
//        out.print(input);
//    }
    public TETile[][] load(File filename) throws IOException {
//        In in = new In(filename);
//        String input = in.readLine();
//        Out out = new Out(filename.getName());
//        out.println(input);
//        System.out.println(input);

        FileReader reader = new FileReader(filename.getName());
        LineNumberReader lineReader = new LineNumberReader(reader);
        String lineText = null;
        String input = "";
        while ((lineText = lineReader.readLine()) != null) {
//            System.out.println(lineText);
            input = lineText;
        }
        System.out.println(input);
        return Engine.interactWithInputString(input);
    }

    public TETile[][] replay(File filename, TERenderer ter) throws IOException {
        FileReader reader = new FileReader(filename.getName());
        LineNumberReader lineReader = new LineNumberReader(reader);
        String lineText = null;
        String input = "";
        while ((lineText = lineReader.readLine()) != null) {
//            System.out.println(lineText);
            input = lineText;
        }
        System.out.println(input);
        int i = 0;
        String seed = "";
        while (input.charAt(i) != 'c') {
            seed += input.charAt(i);
        }
        System.out.println(seed);
        TETile[][] world = Engine.interactWithInputString(seed);
        return world;
//        In in = new In(filename);
//        String[] input = in.readLine().split("");
//        String seed = "";
//        TETile[][] world;
//        int i = 0;
//        while (!input[i].equals('s')) {
//            seed += input[i];
//            i = i + 1;
//        }
//        seed += 's';
//        world = Engine.interactWithInputString(seed);
//        i = i + 1;
//        while (i < input[i].length()) {
//            seed += input[i];
//            world = Engine.interactWithInputString(seed);
//            i = i + 1;
//        }
//        return world;
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
}