package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class World {
    static int WIDTH = Game.WIDTH;
    static int HEIGHT = Game.HEIGHT;
    long SEED;
    Random RANDOM;
    private int houseNumber = 0;
    private int n;
    private House[] houses;
    private House houseGroup = new House(SEED);

    World(long seed) {
        this.SEED = seed;
        RANDOM = new Random(seed);
        n = RANDOM.nextInt(10) + 10;
        houses = new House[n + 10];
    }

    static TETile[][] inital(int w, int h) {
        TETile[][] world = new TETile[w][h];
        for (int x = 0; x < w; x += 1) {
            for (int y = 0; y < h; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public void connectHouse(House h1, House h2, TETile[][] world) {
        h1.connect(h2, world);
    }

    public void drawHouse(TETile[][] world) {
        House h = new House();
        h.draw(world);
        houses[houseNumber] = h;
        houseNumber += 1;
    }

    // draw houses of number n.
    public TETile[][] drawHouseLoop(TETile[][] world) {
        for (int i = 0; i < n; i += 1) {
            drawHouse(world);
        }
        for (int i = 0; i < n - 1; i += 1) {
            connectHouse(houses[i], houses[i + 1], world);
        }
        return world;
    }

    /**
     public static void main(String[] args) {
     TERenderer ter = new TERenderer();
     ter.initialize(WIDTH, HEIGHT);
     TETile[][] world = inital();
     drawHouseLoop(world);
     ter.renderFrame(world);
     }*/
}
