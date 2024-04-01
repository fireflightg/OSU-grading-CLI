import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.queue.Queue;
import components.queue.Queue1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods to be used in grading the SortingMachine5a project. Uses
 * SortingMachine1L as the reference implementation.
 * 
 * @author Paolo Bucci (.2)
 * @modified Derek Schneider (.641)
 * 
 * @revised 6/19/2013
 * 
 */
public class SortingMachine5aGrade {

    /**
     * Comparator<String> implementation to be used in all test cases.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /**
     * Invokes the {@code SortingMachine} constructor for the implementation
     * under test and returns the result.
     * 
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires <pre>
     * {@code IS_TOTAL_PREORDER([relation computed by order.compare method])}
     * </pre>
     * @ensures <pre>
     * {@code constructor = (true, order, {})}
     * </pre>
     */
    private SortingMachine<String> constructorTest(Comparator<String> order) {
        return new SortingMachine5a<String>(order);
    }

    /**
     * Invokes the {@code SortingMachine} constructor for the reference
     * implementation and returns the result.
     * 
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires <pre>
     * {@code IS_TOTAL_PREORDER([relation computed by order.compare method])}
     * </pre>
     * @ensures <pre>
     * {@code constructor = (true, order, {})}
     * </pre>
     */
    private SortingMachine<String> constructorRef(Comparator<String> order) {
        return new SortingMachine1L<String>(order);
    }

    /**
     * 
     * Creates and returns a {@code Set<String>} with the given elements.
     * 
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires <pre>
     * {@code IS_TOTAL_PREORDER([relation computed by order.compare method])}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = (insertionMode, order, [multiset of entries in args])}
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        this.createFromArgs(sm, order, insertionMode, args);
        return sm;
    }

    /**
     * 
     * Creates and returns a {@code Set<String>} with the given elements.
     * 
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires <pre>
     * {@code IS_TOTAL_PREORDER([relation computed by order.compare method])}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = (insertionMode, order, [multiset of entries in args])}
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        this.createFromArgs(sm, order, insertionMode, args);
        return sm;
    }

    /**
     * 
     * Creates and returns a {@code SortingMachine<String>} with the given
     * entries and mode.
     * 
     * @param ri
     *            the reference-implementation flag
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires <pre>
     * {@code IS_TOTAL_PREORDER([relation computed by order.compare method])}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = (insertionMode, order, [multiset of entries in args])}
     * </pre>
     */
    protected final SortingMachine<String> createFromArgs(
            SortingMachine<String> sm, Comparator<String> order,
            boolean insertionMode, String... args) {

        sm.clear();

        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    @Test
    public final void testConstructor() {
        SortingMachine<String> m1 = this.constructorTest(ORDER);
        SortingMachine<String> m2 = this.constructorRef(ORDER);

        assertEquals(m2, m1);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> m2 = this
                .createFromArgsRef(ORDER, true, "green");
        m1.add("green");
        assertEquals(m2, m1);
    }

    @Test
    public final void testAddNonEmptyNotInContents() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "red", "blue", "yellow");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true, "blue",
                "yellow", "green", "purple", "red");
        m1.add("purple");
        assertEquals(m2, m1);
    }

    @Test
    public final void testAddNonEmptyInContents() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "red", "blue", "yellow", "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true, "blue",
                "yellow", "green", "purple", "red", "purple");
        m1.add("purple");
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false);
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeOne() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green");
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeTwoSame() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green", "green");
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeTwoDifferentInOrder() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "red");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false, "red",
                "green");
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeTwoDifferentNotInOrder() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true, "red",
                "green");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green", "red");
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeNonEmptyEven() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green", "blue", "yellow", "green", "purple", "red",
                "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green", "green", "blue", "yellow", "green", "purple", "red",
                "purple");
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testChangeToExtractionModeNonEmptyOdd() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green", "blue", "yellow", "green", "purple", "red",
                "purple", "orange");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green", "green", "blue", "yellow", "green", "orange",
                "purple", "red", "purple");
        m1.changeToExtractionMode();
        assertEquals(m2, m1);
    }

    @Test
    public final void testRemoveFirstLeavingEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false);
        String s = m1.removeFirst();
        assertEquals(m2, m1);
        assertEquals("green", s.toString());
    }

    @Test
    public final void testRemoveFirstLeavingNonEmptyTwoSame() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green", "green");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green");
        String s = m1.removeFirst();
        assertEquals(m2, m1);
        assertEquals("green", s.toString());
    }

    @Test
    public final void testRemoveFirstLeavingNonEmptyTwoDifferentInOrder() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green", "red");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false, "red");
        String s = m1.removeFirst();
        assertEquals(m2, m1);
        assertEquals("green", s.toString());
    }

    @Test
    public final void testRemoveFirstLeavingNonEmptyTwoDifferentNotInOrder() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "red", "green");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false, "red");
        String s = m1.removeFirst();
        assertEquals(m2, m1);
        assertEquals("green", s.toString());
    }

    @Test
    public final void testRemoveFirstLeavingNonEmptyEven() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green", "green", "blue", "yellow", "green", "purple", "red",
                "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green", "green", "yellow", "green", "purple", "red", "purple");
        String s = m1.removeFirst();
        assertEquals(m2, m1);
        assertEquals("blue", s.toString());
    }

    @Test
    public final void testRemoveFirstLeavingNonEmptyOdd() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "green", "yellow", "green", "purple", "red", "purple");
        String s = m1.removeFirst();
        assertEquals(m2, m1);
        assertEquals("blue", s.toString());
    }

    @Test
    public final void testIsInInsertionModeInsertionEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true);
        boolean b = m1.isInInsertionMode();
        assertEquals(m2, m1);
        assertEquals(true, b);
    }

    @Test
    public final void testIsInInsertionModeInsertionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        boolean b = m1.isInInsertionMode();
        assertEquals(m2, m1);
        assertEquals(true, b);
    }

    @Test
    public final void testIsInInsertionModeExtractionEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false);
        boolean b = m1.isInInsertionMode();
        assertEquals(m2, m1);
        assertEquals(false, b);
    }

    @Test
    public final void testIsInInsertionModeExtractionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow");
        boolean b = m1.isInInsertionMode();
        assertEquals(m2, m1);
        assertEquals(false, b);
    }

    @Test
    public final void testOrderModeInsertionEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true);
        Comparator<String> c = m1.order();
        assertEquals(m2, m1);
        assertEquals(ORDER, c);
    }

    @Test
    public final void testOrderInsertionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        Comparator<String> c = m1.order();
        assertEquals(m2, m1);
        assertEquals(ORDER, c);
    }

    @Test
    public final void testOrderExtractionEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false);
        Comparator<String> c = m1.order();
        assertEquals(m2, m1);
        assertEquals(ORDER, c);
    }

    @Test
    public final void testOrderExtractionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow");
        Comparator<String> c = m1.order();
        assertEquals(m2, m1);
        assertEquals(ORDER, c);
    }

    @Test
    public final void testSizeInsertionEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true);
        int n = m1.size();
        assertEquals(m2, m1);
        assertEquals(0, n);
    }

    @Test
    public final void testSizeInsertionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, true,
                "green", "green", "blue", "yellow", "purple", "red", "purple");
        int n = m1.size();
        assertEquals(7, n);
        assertEquals(m2, m1);

    }

    @Test
    public final void testSizeExtractionEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false);
        int n = m1.size();
        assertEquals(m2, m1);
        assertEquals(0, n);
    }

    @Test
    public final void testSizeExtractionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow");
        int n = m1.size();
        assertEquals(m2, m1);
        assertEquals(8, n);
    }

    // End of basic test cases--the rest are extras to provide more thorough
    // testing

    @Test
    public final void testIteratorInsertionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        Queue<String> q = new Queue1L<String>();
        for (String s : m) {
            q.enqueue(s);
        }
        assertEquals("(true,[order],{})", m.toString());
        assertEquals("<>", q.toString());
    }

    @Test
    public final void testIteratorInsertionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green", "green");
        Queue<String> q = new Queue1L<String>();
        for (String s : m) {
            q.enqueue(s);
        }
        assertEquals("(true,[order],{green,green})", m.toString());
        assertEquals("<green,green>", q.toString());
    }

    @Test
    public final void testIteratorExtractionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        Queue<String> q = new Queue1L<String>();
        for (String s : m) {
            q.enqueue(s);
        }
        assertEquals("(false,[order],{})", m.toString());
        assertEquals("<>", q.toString());
    }

    @Test
    public final void testIteratorExtractionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "green");
        Queue<String> q = new Queue1L<String>();
        for (String s : m) {
            q.enqueue(s);
        }
        assertEquals("(false,[order],{green,green})", m.toString());
        assertEquals("<green,green>", q.toString());
    }

    @Test
    public final void testNewInstanceInsertionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green");
        SortingMachine<String> m2 = m1.newInstance();
        assertEquals("(true,[order],{green,green})", m1.toString());
        assertEquals("(true,[order],{})", m2.toString());
    }

    @Test
    public final void testNewInstanceExtractionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green", "green");
        SortingMachine<String> m2 = m1.newInstance();
        assertEquals("(false,[order],{green,green})", m1.toString());
        assertEquals("(true,[order],{})", m2.toString());
    }

    @Test
    public final void testClearInsertionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green", "green");
        m.clear();
        assertEquals("(true,[order],{})", m.toString());
    }

    @Test
    public final void testClearExtractionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "green");
        m.clear();
        assertEquals("(true,[order],{})", m.toString());
    }

    @Test
    public final void testTransferFromInsertionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, true,
                "green", "green");
        SortingMachine<String> m2 = this.createFromArgsTest(ORDER, true);
        m2.transferFrom(m1);
        assertEquals("(true,[order],{})", m1.toString());
        assertEquals("(true,[order],{green,green})", m2.toString());
    }

    @Test
    public final void testTransferFromExtractionNonEmpty() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "green", "green");
        SortingMachine<String> m2 = this.createFromArgsTest(ORDER, true);
        m2.transferFrom(m1);
        assertEquals("(true,[order],{})", m1.toString());
        assertEquals("(false,[order],{green,green})", m2.toString());
    }

    @Test
    public final void testSortSorted() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "blue", "green", "red", "red");
        String s = m.removeFirst();
        assertEquals("blue", s.toString());
        s = m.removeFirst();
        assertEquals("green", s.toString());
        assertEquals("(false,[order],{red,red})", m.toString());
        s = m.removeFirst();
        assertEquals("red", s.toString());
        assertEquals("(false,[order],{red})", m.toString());
        s = m.removeFirst();
        assertEquals("red", s.toString());
        assertEquals("(false,[order],{})", m.toString());
    }

    @Test
    public final void testSortUnsorted() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "purple", "orange", "red", "green", "blue", "red");
        String s = m.removeFirst();
        assertEquals("blue", s.toString());
        s = m.removeFirst();
        assertEquals("green", s.toString());
        s = m.removeFirst();
        assertEquals("orange", s.toString());
        s = m.removeFirst();
        assertEquals("purple", s.toString());
        assertEquals("(false,[order],{red,red})", m.toString());
        s = m.removeFirst();
        assertEquals("red", s.toString());
        assertEquals("(false,[order],{red})", m.toString());
        s = m.removeFirst();
        assertEquals("red", s.toString());
        assertEquals("(false,[order],{})", m.toString());
    }

    @Test
    public final void testEqualsWithWrongType() {
        Comparator<String> order = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order, true,
                "green");
        String m2 = "foo";
        boolean eq = m1.equals(m2);
        assertEquals(false, eq);
        assertEquals("(true,[order],{green})", m1.toString());
        assertEquals("foo", m2.toString());
    }

    @Test
    public final void testEqualsInitialInitialEqual() {
        Comparator<String> order = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order, true);
        SortingMachine<String> m2 = this.createFromArgsTest(order, true);
        boolean eq = m1.equals(m2);
        assertEquals(true, eq); // because we used same order
        assertEquals("(true,[order],{})", m1.toString());
        assertEquals("(true,[order],{})", m2.toString());
    }

    @Test
    public final void testEqualsInitialInitialNotEqual() {
        Comparator<String> order1 = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order1, true);
        Comparator<String> order2 = new StringLT();
        SortingMachine<String> m2 = this.createFromArgsTest(order2, true);
        boolean eq = m1.equals(m2);
        assertEquals(false, eq); // because order1 != order2
        assertEquals("(true,[order],{})", m1.toString());
        assertEquals("(true,[order],{})", m2.toString());
    }

    @Test
    public final void testEqualsInsertionNonEmptyEmpty() {
        Comparator<String> order = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order, true,
                "green", "green");
        SortingMachine<String> m2 = this.createFromArgsTest(order, true);
        boolean eq = m1.equals(m2);
        assertEquals(false, eq); // even though order is identical
        assertEquals("(true,[order],{green,green})", m1.toString());
        assertEquals("(true,[order],{})", m2.toString());
    }

    @Test
    public final void testEqualsInsertionNonEmptyNonEmptyEqual() {
        Comparator<String> order = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order, true,
                "blue", "green", "red", "green");
        SortingMachine<String> m2 = this.createFromArgsTest(order, true,
                "green", "red", "green", "blue");
        boolean eq = m1.equals(m2);
        assertEquals(true, eq); // because order is identical, contents equal
    }

    @Test
    public final void testEqualsInsertionNonEmptyNonEmptyNotEqual() {
        Comparator<String> order = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order, true,
                "blue", "green");
        SortingMachine<String> m2 = this.createFromArgsTest(order, true,
                "green", "red");
        boolean eq = m1.equals(m2);
        assertEquals(false, eq);
    }

    @Test
    public final void testEqualsInsertionExtraction() {
        Comparator<String> order = new StringLT();
        SortingMachine<String> m1 = this.createFromArgsTest(order, true,
                "green", "green");
        SortingMachine<String> m2 = this.createFromArgsTest(order, false,
                "green", "green");
        boolean eq = m1.equals(m2);
        assertEquals("(true,[order],{green,green})", m1.toString());
        assertEquals("(false,[order],{green,green})", m2.toString());
        assertEquals(false, eq);
    }

    /**
     * This test case has been shown to expose bugs when all other test cases
     * have passed.
     * 
     * @author Derek Schneider (.641)
     */
    @Test
    public final void exposeBugs1() {
        SortingMachine<String> m1 = this.createFromArgsTest(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow", "blue", "green", "purple", "red", "purple", "green",
                "blue", "yellow", "cyan", "maroon", "beige", "vermillion",
                "crimson", "slate", "gray", "grey", "magenta", "chartuse",
                "something", "213", "3453465", "4534");
        SortingMachine<String> m2 = this.createFromArgsRef(ORDER, false,
                "blue", "green", "purple", "red", "purple", "green", "blue",
                "yellow", "blue", "green", "purple", "red", "purple", "green",
                "blue", "yellow", "cyan", "maroon", "beige", "vermillion",
                "crimson", "slate", "gray", "grey", "magenta", "chartuse",
                "something", "213", "3453465", "4534");
        int n = m1.size();

        while (m1.size() > 0) {
            assertEquals(m2.removeFirst(), m1.removeFirst());
        }

        assertEquals(m2, m1);
        assertEquals(30, n);

    }

}
