package byow.Core;
import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.*;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

public class Engine {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() throws IOException {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        Drawing canvas = new Drawing(ter, 80, 30, 0, 0);
        canvas.start(ter);
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
//        System.out.println(input);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] myWorld = new TETile[WIDTH][HEIGHT];
        ArrayList<Character> inputSplit = new ArrayList();
        for (char ch : input.toCharArray()) {
            inputSplit.add(ch);
        }
        String seed = "";
        Avatar myPlayer = new Avatar();
        int j = 0;
//        if (inputSplit.get(j).equals('n')) {
//            j = 1;
//        } else {
//            while (!inputSplit.get(j).equals('n')) {
//                if (inputSplit.get(j).equals('c')) {
//                    char avatarSymbol = inputSplit.get(j+1);
//                    myPlayer = new Avatar(avatarSymbol);
//                    j = j + 2;
//                }
//            }
//        }
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
//        System.out.println(seed);
        Long longSeed = Long.valueOf(seed);
        System.out.println(longSeed);
        bigWorld big = new bigWorld();
        big.buildBigWorld(HEIGHT, WIDTH, longSeed, myWorld, myPlayer);
        String moves = "";
        for(int h = j; h < inputSplit.size(); ) {
//            System.out.println(h);
            if (inputSplit.get(h).equals('c')) {
                char avatarSymbol = inputSplit.get(h + 1);
                int x = myPlayer.avTrackerGetter().x;
                int y = myPlayer.avTrackerGetter().y;
                myPlayer = new Avatar(avatarSymbol);
                myPlayer.placeAvatar(x, y, myPlayer.avatar, myWorld);
                h = h + 2;
                continue;
            } else if (!(inputSplit.get(h).equals(':')) || !(inputSplit.get(h).equals('q')) || !(inputSplit.get(h).equals('l'))){
                String nextMove = String.valueOf(inputSplit.get(h));
                myPlayer.move(nextMove, myWorld, ter, myPlayer.avatar);
                moves = moves + nextMove;
                h = h + 1;
            } else if ((inputSplit.get(h).equals(':')) || (inputSplit.get(h).equals('q')) || (inputSplit.get(h).equals('l'))){
                h = h + 1;
            }
        }
        ter.renderFrame(myWorld);
        return myWorld;
    }


    public String selected() {
        String answer = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char typed = StdDraw.nextKeyTyped();
                if (typed == 'T') {
                    return "T";
                }
                if (typed == 'F') {
                    return "F";
                }
                if (typed == 'G') {
                    return "G";
                }
                if (typed == 'A') {
                    return "a";
                }
            }
        }
    }

//    public static void save(File filename, Out out, String seed, String moves, String avatar) {
//        out.println(seed);
//        out.println(avatar);
//        out.println(moves);
//    }
}





