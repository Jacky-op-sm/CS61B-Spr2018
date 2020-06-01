package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * draw Floor between position1(house1) and position2(house2)
 */
public class Floor {
    static int k;
    static TETile floor = Tileset.FLOOR;
    static TETile wall = Tileset.WALL;

    //draw X direction
    static void drawFloorX(Position p1, Position p2, TETile[][] world) {
        int x2 = java.lang.Math.max(p1.xPosition, p2.xPosition);
        int x1 = p1.xPosition + p2.xPosition - x2;
        Wall.surroundedWall(p1, world);
        Wall.surroundedWall(p2, world);
        for (int i = x1; i <= x2; i += 1) {
            if (world[i][p1.yPosition] == floor) {
                k = 1;
            } else {
                world[i][p1.yPosition] = floor;
            }
            if (world[i][p1.yPosition - 1] == floor) {
                k = 1;
            } else {
                world[i][p1.yPosition - 1] = wall;
            }
            if (world[i][p1.yPosition + 1] == floor) {
                k = 1;
            } else {
                world[i][p1.yPosition + 1] = wall;
            }
        }
        p1.xPosition = p2.xPosition;
    }

    //draw y direction
    static void drawFloorY(Position p1, Position p2, TETile[][] world) {
        int y2 = java.lang.Math.max(p1.yPosition, p2.yPosition);
        int y1 = p1.yPosition + p2.yPosition - y2;
        Wall.surroundedWall(p1, world);
        Wall.surroundedWall(p2, world);
        for (int i = y1; i <= y2; i += 1) {
            if (world[p1.xPosition][i] == floor) {
                k = 1;
            } else {
                world[p1.xPosition][i] = floor;
            }
            if (world[p1.xPosition - 1][i] == floor) {
                k = 1;
            } else {
                world[p1.xPosition - 1][i] = wall;
            }
            if (world[p1.xPosition + 1][i] == floor) {
                k = 1;
            } else {
                world[p1.xPosition + 1][i] = wall;
            }
        }
        p1.yPosition = p2.yPosition;
    }

}
