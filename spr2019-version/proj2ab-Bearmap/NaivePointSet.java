package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet{

    private final List<Point> ps;

    public NaivePointSet(List<Point> points) {
        if (points == null) throw new IllegalArgumentException();
        ps = new ArrayList<>();
        ps.addAll(points);
    }

    @Override
    public Point nearest(double x, double y) {
        Point min = ps.get(0);
        Point tar = new Point(x, y);
        for (Point p : ps) {
            if (Point.distance(p, tar) < Point.distance(min, tar))
                min = p;
        }
        return min;
    }
}
