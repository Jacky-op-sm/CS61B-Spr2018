package bearmaps;

import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void testaddAndRemoveOnce() {
        ArrayHeapMinPQ<Character> pq = new ArrayHeapMinPQ<>();
        pq.add('e', 5);
        pq.add('d', 4);
        pq.add('c', 3);
        pq.add('a', 1);
        pq.add('b', 2);

        pq.changePriority('a', 2.5);



         Character c = 'a';
        for (int i = 0; i < 5; i += 1) {
            StdOut.println(pq.contains(c));
            StdOut.println(pq.removeSmallest());

        }
    }
}
