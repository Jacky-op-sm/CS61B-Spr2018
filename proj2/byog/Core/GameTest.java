package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class GameTest {
    public static void main(String[] args) {
        TETile[][] world1 = Game.playWithInputString("n1234s");
        TETile[][] world2 = Game.playWithInputString("n1245234s");
        TERenderer ter = new TERenderer();
        ter.initialize(80, 30);
        ter.renderFrame(world2);
    }
}
