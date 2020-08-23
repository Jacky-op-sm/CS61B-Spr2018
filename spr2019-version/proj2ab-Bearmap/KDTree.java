package bearmaps;

import java.util.List;

public class KDTree implements PointSet {

    private Node root;
    private int size;

    public KDTree(List<Point> points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points) {
            insert(p);
        }
    }

    //validate the input p
    private void validate(Point p) {
        if (p == null) throw new IllegalArgumentException();
    }

    private void insert(Point p) {
        validate(p);
        root = insert(root, p, true);
    }

    private Node insert(Node n, Point p, boolean vertical) {
        if (n == null) {
            size += 1;
            return new Node(p);
        }
        if (vertical) {
            double cmp = p.getX() - n.p.getX();
            if (cmp < 0) {
                n.lb = insert(n.lb, p, false);
            } else {
                if (!n.p.equals(p)) {
                    n.rt = insert(n.rt, p, false);
                }
            }
        } else {
            double cmp = p.getY() - n.p.getY();
            if (cmp < 0) {
                n.lb = insert(n.lb, p, true);
            } else {
                if (!n.p.equals(p)) {
                    n.rt = insert(n.rt, p, true);
                }
            }
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        Point tar = new Point(x, y);
        return nearest(root, tar, root.p, true);
    }

    private Point nearest(Node n, Point p, Point best_so_far, boolean vertical) {
        if (n == null) return best_so_far;
        if (Point.distance(n.p, p) < Point.distance(best_so_far, p)) best_so_far = n.p;
        Node good_side, bad_side;

        //decide the GOOD_SIDE and BAD_SIDE
        double cmp;
        if (vertical) cmp = p.getX() - n.p.getX();
        else cmp = p.getY() - n.p.getY();
        if (cmp < 0) {
            good_side = n.lb;
            bad_side = n.rt;
        } else {
            good_side = n.rt;
            bad_side = n.lb;
        }

        best_so_far = nearest(good_side, p, best_so_far, !vertical);

        //decide the BAD_SIDE still has a chance or not
        if (Math.pow(cmp, 2) < Point.distance(best_so_far, p))
            best_so_far = nearest(bad_side, p, best_so_far, !vertical);

        return best_so_far;
    }

    private static class Node {
        private Point p;      // the point
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        private Node(Point p) {
            this.p = p;
        }
    }


}
