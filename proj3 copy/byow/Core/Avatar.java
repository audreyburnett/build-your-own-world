package byow.Core;
import byow.TileEngine.TETile;
import byow.TileEngine.TERenderer;
import byow.TileEngine.Tileset;

import java.util.HashMap;

public class Avatar {
    private HashMap<String, Coordinate> avatarTracker= new HashMap<>();

    private void placeAvatar(int x, int y, TETile[][] world) {
        world[x][y] = Tileset.AVATAR;
        avTrackerAdder(x, y);
    }
    private Boolean movementCheckerUp(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x][current.y + 1] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    private Boolean movementCheckerRight(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x + 1][current.y] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    private Boolean movementCheckerDown(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x][current.y - 1] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    private Boolean movementCheckerLeft(TETile[][] world){
        Coordinate current = avatarTracker.get("avatar");
        if(world[current.x - 1][current.y] == Tileset.MOUNTAIN){
            return true;
        }
        else{
            return false;
        }
    }
    private void avatarUp(TETile[][] world, TETile avatar){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y + 1] = avatar;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate up = new Coordinate(current.x, current.y+1);
        avatarTracker.put("avatar", up);
    }
    private void avatarRight(TETile[][] world, TETile avatar){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x + 1][current.y] = avatar;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate right = new Coordinate(current.x+1, current.y);
        avatarTracker.put("avatar", right);
    }
    private void avatarDown(TETile[][] world, TETile avatar){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x][current.y - 1] = avatar;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate down = new Coordinate(current.x, current.y-1);
        avatarTracker.put("avatar", down);
    }

    private void avatarLeft(TETile[][] world, TETile avatar){
        Coordinate current = avatarTracker.get("avatar");
        world[current.x - 1][current.y] = avatar;
        world[current.x][current.y] = Tileset.MOUNTAIN;
        Coordinate left = new Coordinate(current.x-1, current.y);
        avatarTracker.put("avatar", left);
    }
    private void avTrackerAdder(int x, int y){
        Coordinate av = new Coordinate(x,y);
        avatarTracker.put("avatar", av);
    }
    private Coordinate avTrackerGetter(){
        Coordinate coord = avatarTracker.get("avatar");
        return coord;
    }
    private void move(String n, TETile[][] world, TERenderer ter, TETile avatar) {
        if (n.equals("w")) {
            if (movementCheckerUp(world)) {
                avatarUp(world, avatar);
            }
        }
        if (n.equals("d")) {
            if (movementCheckerRight(world)) {
                avatarRight(world, avatar);
            }
        }
        if (n.equals("s")) {
            if (movementCheckerDown(world)) {
                avatarDown(world, avatar);
            }
        }
        if (n.equals("a")) {
            if (movementCheckerLeft(world)) {
                avatarLeft(world, avatar);
            }
        }
    }

    private void replayMove(String n, TETile[][] world, TERenderer ter, TETile avatar) {
        if (n.equals("w")) {
            if (movementCheckerUp(world)) {
                avatarUp(world, avatar);
                ter.renderFrame(world);
            }
        }
        if (n.equals("d")) {
            if (movementCheckerRight(world)) {
                avatarRight(world, avatar);
                ter.renderFrame(world);
            }
        }
        if (n.equals("s")) {
            if (movementCheckerDown(world)) {
                avatarDown(world, avatar);
                ter.renderFrame(world);
            }
        }
        if (n.equals("a")) {
            if (movementCheckerLeft(world)) {
                avatarLeft(world, avatar);
                ter.renderFrame(world);
            }
        }
    }
}
