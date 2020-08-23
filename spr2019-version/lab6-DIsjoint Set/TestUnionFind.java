import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testUnion() {
        UnionFind sample = new UnionFind(16);
        sample.union(2, 3);
        sample.union(4, 5);
        sample.union(2, 4);
        assertEquals(4, sample.sizeOf(2));
        assertEquals(5, sample.find(2));
        assertEquals(5, sample.parent(2));
        assertTrue(sample.connected(2, 4));
    }
}
