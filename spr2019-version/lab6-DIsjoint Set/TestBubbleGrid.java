import org.junit.Test;
import static org.junit.Assert.*;

public class TestBubbleGrid {

    @Test
    public void testPopBubble() {
        BubbleGrid sample = new BubbleGrid();
        sample.grid = new int[][] {{1, 1, 0},{1, 0, 0},{1, 1, 0},{1, 1, 1}};
        int[][] darts = new int[][] {{2, 2}, {2, 0}};
        int[] actual = sample.popBubbles(darts);
        int[] expect = new int[]{0, 4};
        assertEquals(expect, actual);
    }
}
