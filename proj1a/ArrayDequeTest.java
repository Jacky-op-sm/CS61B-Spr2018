/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */

    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    public static void addRemoveTest2() {
        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.addFirst("a");
        lld1.addFirst("b");
        lld1.addFirst("c");
        lld1.printDeque();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.addLast("k");
    }

    public static void test2() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.removeLast();
        lld1.get(1);
        lld1.addFirst(5);
        lld1.addLast(6);
        lld1.addLast(7);
        lld1.addLast(8);
        lld1.addFirst(9);
        lld1.get(1);
        lld1.get(0);
        lld1.addFirst(12);
        lld1.addLast(13);
        lld1.removeLast();
        lld1.removeFirst();
        lld1.get(4);
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeLast();
        lld1.removeFirst();
    }
    public static void resizeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        lld1.addFirst(4);
        lld1.addFirst(5);
        lld1.addFirst(6);
        lld1.addFirst(7);
        lld1.addFirst(8);
        lld1.addFirst(9);
        lld1.addFirst(10);
        lld1.addFirst(11);
        lld1.addFirst(12);
        lld1.addFirst(13);
        lld1.addFirst(14);
        lld1.addFirst(15);
        lld1.addFirst(16);
        System.out.println(lld1.get(0));
        System.out.println(lld1.get(1));
        System.out.println(lld1.get(2));
        System.out.println(lld1.get(3));
        System.out.println(lld1.get(15));
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        lld1.removeFirst();
        lld1.printDeque();
    }
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        addRemoveTest2();
        resizeTest();
        test2();
    }

} 