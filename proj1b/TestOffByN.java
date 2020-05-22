import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static OffByN offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('f', 'h'));
        assertFalse(offByN.equalChars('z', 'z'));
    }

}
