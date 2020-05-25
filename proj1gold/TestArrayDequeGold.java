import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void randomTest() {
        ArrayDequeSolution<Integer> expected = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> actual = new StudentArrayDeque<>();
        StringBuilder message = new StringBuilder();
        message.append("\n");

        while (true) {
            int i = StdRandom.uniform(9);
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne <= 0.3) {
                actual.addLast(i);
                expected.addLast(i);
                message.append("addLast(" + i + ")\n");
            }
            else if (numberBetweenZeroAndOne <= 0.6){
                actual.addFirst(i);
                expected.addFirst(i);
                message.append("addFirst(" + i + ")\n");
            }
            else if (numberBetweenZeroAndOne < 0.8 && expected.size() != 0) {
                Integer a = expected.removeFirst();
                Integer b = actual.removeFirst();
                message.append("removeFirst()\n");
                assertEquals(message.toString(), a, b);
            }
            else if (expected.size() != 0) {
                Integer a = expected.removeLast();
                Integer b = actual.removeLast();
                message.append("removeLast()\n");
                assertEquals(message.toString(), a, b);
            }
            }
        }
    }


