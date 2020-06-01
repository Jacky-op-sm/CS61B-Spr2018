package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class World {
    static final int WIDTH = 90;
    static final int HEIGHT = 50;
    private static int n;
    private static House[] houses = new House[n];
    private static int houseNumber = 0;


    static TETile[][] inital(int WIDTH, int HEIGHT) {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    public static void drawHouse(TETile[][] World) {
        House h = new House();
        h.draw(World);
        houses[houseNumber] = h;
        houseNumber += 1;
    }

    public static void connectHouse(House h1, House h2, TETile[][] World) {
        h1.connect(h2, World);
    }

    // draw houses of number n.
    public static TETile[][] drawHouseLoop(TETile[][] World) {
        for (int i = 0; i < n; i += 1) {
            drawHouse(World);
        }
        for (int i = 0; i < n - 1; i += 1) {
            connectHouse(houses[i], houses[i + 1], World);
        }
        return World;
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
