package bearmaps;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class KDTreeTest {

    public static void main(String[] args) {

        /**
        // initialize the data structures from file
        String filename = args[0];
        In in = new In(filename);
        int i = 0;

        List<Point> points = new ArrayList<>();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point p = new Point(x, y);
            points.add(i, p);
            i += 1;
        }
        KDTree kdtree = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);
        StdOut.println(kdtree.nearest(0.52, 0.77));
        StdOut.println(nps.nearest(0.52, 0.77));

         */





         //* random test

        int n = Integer.parseInt(args[0]);
        int seed = Integer.parseInt(args[1]);
        Random random = new Random(seed);

        //build list of random points
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i += 1) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            Point p = new Point(x, y);
            points.add(i, p);
        }

        //instantiate
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);

        //give a test
        double x = random.nextDouble();
        double y = random.nextDouble();

        StdOut.println(nps.nearest(x, y));
        StdOut.println(kdt.nearest(x, y));


    }
}
