package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.time.chrono.ThaiBuddhistEra;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 60;
    private static Position position = new Position();

    private static final long SEED = 1536845;
    private static final Random RANDOM = new Random(SEED);

    private static class Position {
        int xPosition;
        int yPosition;

        private Position() {
        }

        private Position(int x, int y) {
            xPosition = x;
            yPosition = y;
        }

    }

    private static void addHexagon(Position p, int size, TETile[][] tiles) {
        int start = p.xPosition + size - 1;
        int end = start + size;
        TETile tile = randomTile();
        for (int i = p.yPosition; i < p.yPosition + size; i += 1) {
            for (int j = start; j < end; j += 1) {
                tiles[j][i] = tile;
            }
            start -= 1;
            end += 1;
        }

        for (int i = p.yPosition + size; i < p.yPosition + size * 2; i += 1) {
            start += 1;
            end -= 1;
            for (int j = start; j < end; j += 1) {
                tiles[j][i] = tile;
            }
        }
    }

    // draw oneLine of Hexagons at the given Position, xDistance and the total number.
    private static void drawOneLine(Position p, int xDistance, int number, int hexagonSize, TETile[][] tiles) {
        for (int i = 0; i < number; i += 1) {
            addHexagon(p, hexagonSize, tiles);
            p.xPosition += xDistance;
        }
    }

    private static void manyHexagon(int size, TETile[][] tiles) {
        Position p = new Position(WIDTH / 2 - size, 0);
        Position helper = new Position(WIDTH / 2 - size, 0);
        for (int i = 0; i < size - 1; i += 1) {
            drawOneLine(p, 3 * size + 1, i + 1, size, tiles);
            p.xPosition = helper.xPosition - size* 2 + 1;
            p.yPosition = helper.yPosition + size;
            helper.xPosition = p.xPosition;
            helper.yPosition = p.yPosition;
        }

        for (int i = 0; i < 2 * size - 1; i += 1) {
            if (i % 2 == 0) {
                drawOneLine(p, 3 * size + 1, size, size, tiles);
                p.xPosition = helper.xPosition + 2 * size - 1;
            } else {
                drawOneLine(p, 3 * size + 1, size - 1, size, tiles);
                p.xPosition = helper.xPosition - size* 2 + 1;
            }
            p.yPosition = helper.yPosition + size;
            helper.xPosition = p.xPosition;
            helper.yPosition = p.yPosition;
        }

        for (int i = 0; i < size - 1; i += 1) {
            drawOneLine(p, 3 * size + 1, size - i - 1, size, tiles);
            p.xPosition = helper.xPosition + size* 2 - 1;
            p.yPosition = helper.yPosition + size;
            helper.xPosition = p.xPosition;
            helper.yPosition = p.yPosition;
        }
    }

    private static TETile[][] inital() {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.TREE;
            default: return Tileset.FLOWER;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = inital();
        manyHexagon(4, world);

        ter.renderFrame(world);
    }
}
