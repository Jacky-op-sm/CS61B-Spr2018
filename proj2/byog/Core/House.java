package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


// draw a rectangle house with size of m * n at position p.
class House {
    static long SEED;
    static Random RANDOM;

    Position p;
    int m;
    int n;

    House(long seed) {
        this.SEED = seed;
        RANDOM = new Random(seed);
    }

    //build a random house at a random position at random size.
    House() {
        Position p1 = new Position(RANDOM.nextInt(World.WIDTH - 8),
                RANDOM.nextInt(World.HEIGHT - 8));
        int m1 = RANDOM.nextInt(6) + 3;
        int n1 = RANDOM.nextInt(6) + 3;
        if (p1.xPosition + m1 >= World.WIDTH) {
            m1 = World.WIDTH - p1.xPosition - 1;
        }
        if (p1.yPosition + n1 >= World.HEIGHT) {
            n1 = World.HEIGHT - p1.yPosition - 1;
        }
        this.p = p1;
        this.m = m1;
        this.n = n1;
    }



    void draw(TETile[][] tiles) {
        TETile floor = Tileset.FLOOR;
        TETile wall = Tileset.WALL;
        for (int i = p.xPosition; i < p.xPosition + m; i += 1) {
            tiles[i][p.yPosition] = wall;
        }

        for (int i = p.xPosition; i < p.xPosition + m; i += 1) {
            tiles[i][p.yPosition + n - 1] = wall;
        }

        for (int j = p.yPosition + 1; j < p.yPosition + n - 1; j += 1) {
            tiles[p.xPosition][j] = wall;
            tiles[p.xPosition + m - 1][j] = wall;
            for (int i = p.xPosition + 1; i < p.xPosition + m - 1; i += 1) {
                tiles[i][j] = floor;
            }
        }
    }

    //choose random position of the house.
    Position randomPosition(House h) {
        int x = RANDOM.nextInt(m - 1) + h.p.xPosition + 1;
        int y = RANDOM.nextInt(n - 1) + h.p.yPosition + 1;
        return new Position(x, y);
    }

    /**
     * Connect another house with FLOOR tile.
     * It has 2 choices: x precedes y or y precedes x.
     */
    void connect(House h, TETile[][] world) {
        Position p1 = randomPosition(this);
        Position p2 = randomPosition(h);
        int choice = RANDOM.nextInt(2);
        if (choice == 0) {
            Floor.drawFloorX(p1, p2, world);
            Floor.drawFloorY(p1, p2, world);
        } else {
            Floor.drawFloorY(p1, p2, world);
            Floor.drawFloorX(p1, p2, world);
        }
    }
}
