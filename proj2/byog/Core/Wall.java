package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Wall {
    static TETile floor = Tileset.FLOOR;
    static TETile wall = Tileset.WALL;

    // surround a position with wall. 3 * 3;
    static void surroundedWall(Position p, TETile[][] world) {
        for (int i = p.xPosition - 1; i <= p.xPosition + 1; i += 1) {
            for (int j = p.yPosition - 1; j <= p.yPosition + 1; j += 1) {
                if (world[i][j] == floor) {
                } else {
                    world[i][j] = wall;
                }
            }
        }
    }
}
