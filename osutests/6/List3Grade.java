import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.list.List;
import components.list.List1L;

/**
 * JUnit test fixture for {@code List<String>}'s constructor and kernel methods.
 * 
 * @author Paolo Bucci
 * @modified Derek Schneider
 * 
 * @revised 10/8/2013
 */
public class List3Grade {

    /**
     * Invokes the {@code List} constructor for the implementation under test
     * and returns the result.
     * 
     * @return the new list
     * @ensures <pre>
     * {@code constructorTest = (<>, <>)}
     * </pre>
     */
    protected final List<String> constructorTest() {
        return new List3<String>();
    }

    /**
     * Invokes the {@code List} constructor for the reference implementation and
     * returns the result.
     * 
     * @return the new list
     * @ensures <pre>
     * {@code constructorRef = (<>, <>)}
     * </pre>
     */
    protected final List<String> constructorRef() {
        return new List1L<String>();
    }

    /**
     * Constructs a {@code List<String>} with the entries in {@code args} and
     * length of the left string equal to {@code leftLength}.
     * 
     * @param list
     *            the {@code List} to construct
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @updates {@code list}
     * @requires <pre>
     * {@code list = (<>, <>)  and  0 <= leftLength <= args.length}
     * </pre>
     * @ensures <pre>
     * {@code list =
     *   ([first leftLength entries in args], [remaining entries in args])}
     * </pre>
     */
    private void createFromArgsHelper(List<String> list, int leftLength,
            String... args) {
        for (String s : args) {
            list.addRightFront(s);
            list.advance();
        }
        list.moveToStart();
        for (int i = 0; i < leftLength; i++) {
            list.advance();
        }
    }

    /**
     * Creates and returns a {@code List<String>} of the implementation under
     * test type with the given entries.
     * 
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @return the constructed list
     * @requires <pre>
     * {@code 0 <= leftLength <= args.length}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs =
     *   ([first leftLength entries in args], [remaining entries in args])}
     * </pre>
     */
    protected final List<String> createFromArgsTest(int leftLength,
            String... args) {
        assert 0 <= leftLength : "Violation of: 0 <= leftLength";
        assert leftLength <= args.length : "Violation of: leftLength <= args.length";
        List<String> list = this.constructorTest();
        this.createFromArgsHelper(list, leftLength, args);
        return list;
    }

    /**
     * Creates and returns a {@code List<String>} of the reference
     * implementation type with the given entries.
     * 
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @return the constructed list
     * @requires <pre>
     * {@code 0 <= leftLength <= args.length}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs =
     *   ([first leftLength entries in args], [remaining entries in args])}
     * </pre>
     */
    protected final List<String> createFromArgsRef(int leftLength,
            String... args) {
        assert 0 <= leftLength : "Violation of: 0 <= leftLength";
        assert leftLength <= args.length : "Violation of: leftLength <= args.length";
        List<String> list = this.constructorRef();
        this.createFromArgsHelper(list, leftLength, args);
        return list;
    }

    /*
     * Test cases for constructor, addRight, removeRight, rightFront advance,
     * moveToStart, moveToFinish, leftLength, and rightLength.
     */

    @Test
    public final void testConstructor() {
        List<String> list1 = this.constructorTest();
        List<String> list2 = this.createFromArgsRef(0);
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightLeftEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0, "red");
        list1.addRightFront("red");
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        list1.addRightFront("green");
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightLeftNonEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple", "red");
        list1.addRightFront("red");
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        list1.addRightFront("green");
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightLeftEmptyRightOne() {
        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(0);
        String s = list1.removeRightFront();
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "red", "blue");
        String s = list1.removeRightFront();
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightLeftNonEmptyRightOne() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        String s = list1.removeRightFront();
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "purple");
        String s = list1.removeRightFront();
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightFrontLeftEmptyRightOne() {
        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(0, "red");
        String s = list1.rightFront();
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightFrontLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        String s = list1.rightFront();
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightFrontLeftNonEmptyRightOne() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple", "red");
        String s = list1.rightFront();
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightFrontLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        String s = list1.rightFront();
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftEmptyRightOne() {
        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(1, "red");
        list1.advance();
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(1, "green", "red", "blue");
        list1.advance();
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftNonEmptyRightOne() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "orange",
                "purple", "red");
        list1.advance();
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "green", "purple");
        list1.advance();
        assertEquals(list2, list1);
    }

    @Test
    public final void testRetreatLeftOneRightEmpty() {
        List<String> list1 = this.createFromArgsTest(1, "red");
        List<String> list2 = this.createFromArgsRef(0, "red");
        list1.retreat();
        assertEquals(list2, list1);
    }

    @Test
    public final void testRetreatLeftOneRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(1, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        list1.retreat();
        assertEquals(list2, list1);
    }

    @Test
    public final void testRetreatLeftNonEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(4, "yellow", "orange",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple", "red");
        list1.retreat();
        assertEquals(list2, list1);
    }

    @Test
    public final void testRetreatLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        list1.retreat();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        list1.moveToStart();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        list1.moveToStart();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftNonEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "orange",
                "purple");
        list1.moveToStart();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "orange",
                "green", "purple");
        list1.moveToStart();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        list1.moveToFinish();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(3, "green", "red", "blue");
        list1.moveToFinish();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftNonEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        list1.moveToFinish();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "orange",
                "green", "purple");
        list1.moveToFinish();
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishShowBug() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0, "red");
        list1.moveToFinish();
        list1.addRightFront("red");
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        int i = list1.rightLength();
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        int i = list1.rightLength();
        assertEquals(3, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftNonEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        int i = list1.rightLength();
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        int i = list1.rightLength();
        assertEquals(2, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        int i = list1.leftLength();
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        int i = list1.leftLength();
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftNonEmptyRightEmpty() {
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        int i = list1.leftLength();
        assertEquals(3, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftNonEmptyRightNonEmpty() {
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        int i = list1.leftLength();
        assertEquals(2, i);
        assertEquals(list2, list1);
    }

    /*
     * Test cases for iterator.
     */

    @Test
    public final void testIteratorEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(0);
        for (String s : list1) {
            list2.addRightFront(s);
        }
        assertEquals(list3, list1);
        assertEquals(list3, list2);
    }

    @Test
    public final void testIteratorOnlyRight() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(0, "red", "blue");
        List<String> list4 = this.createFromArgsRef(0, "blue", "red");
        for (String s : list1) {
            list2.addRightFront(s);
        }
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    @Test
    public final void testIteratorOnlyLeft() {
        List<String> list1 = this.createFromArgsTest(3, "red", "green", "blue");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(3, "red", "green", "blue");
        List<String> list4 = this.createFromArgsRef(0, "blue", "green", "red");
        for (String s : list1) {
            list2.addRightFront(s);
        }
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    @Test
    public final void testIteratorLeftAndRight() {
        List<String> list1 = this.createFromArgsTest(2, "purple", "red",
                "green", "blue", "yellow");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(2, "purple", "red",
                "green", "blue", "yellow");
        List<String> list4 = this.createFromArgsRef(0, "yellow", "blue",
                "green", "red", "purple");
        for (String s : list1) {
            list2.addRightFront(s);
        }
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    /*
     * Test cases for equals.
     */

    @Test
    public final void testEqualsWithWrongType() {
        List<String> list1 = this.createFromArgsTest(0);
        String list2 = "foo";
        boolean eq = list1.equals(list2);
        assertEquals(false, eq);
        assertEquals("(<>,<>)", list1.toString());
        assertEquals("foo", list2.toString());
    }

    @Test
    public final void testEqualsEmptyEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsTest(0);
        boolean eq = list1.equals(list2);
        assertEquals(true, eq);
        assertEquals("(<>,<>)", list1.toString());
        assertEquals("(<>,<>)", list2.toString());
    }

    @Test
    public final void testEqualsNonEmptyEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsTest(0);
        boolean eq = list1.equals(list2);
        assertEquals(false, eq);
        assertEquals("(<>,<red,blue>)", list1.toString());
        assertEquals("(<>,<>)", list2.toString());
    }

    @Test
    public final void testEqualsEmptyNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsTest(0, "red", "blue");
        boolean eq = list1.equals(list2);
        assertEquals(false, eq);
        assertEquals("(<>,<>)", list1.toString());
        assertEquals("(<>,<red,blue>)", list2.toString());
    }

    @Test
    public final void testEqualsNonEmptyNonEmptyTrue() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsTest(0, "red", "blue");
        boolean eq = list1.equals(list2);
        assertEquals(true, eq);
        assertEquals("(<>,<red,blue>)", list1.toString());
        assertEquals("(<>,<red,blue>)", list2.toString());
    }

    @Test
    public final void testEqualsNonEmptyNonEmptyFalse() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsTest(0, "red", "green");
        boolean eq = list1.equals(list2);
        assertEquals(false, eq);
        assertEquals("(<>,<red,blue>)", list1.toString());
        assertEquals("(<>,<red,green>)", list2.toString());
    }

    @Test
    public final void testEqualsNonEmptyNonEmptyFalseFence() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsTest(1, "red", "blue");
        boolean eq = list1.equals(list2);
        assertEquals(false, eq);
        assertEquals("(<>,<red,blue>)", list1.toString());
        assertEquals("(<red>,<blue>)", list2.toString());
    }

    /*
     * Test cases for newInstance, clear, transferFrom.
     */

    @Test
    public final void testNewInstanceEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = list1.newInstance();
        assertEquals("(<>,<>)", list1.toString());
        assertEquals("(<>,<>)", list2.toString());
    }

    @Test
    public final void testNewInstanceNonEmpty() {
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = list1.newInstance();
        assertEquals("(<>,<red,blue>)", list1.toString());
        assertEquals("(<>,<>)", list2.toString());
    }

    @Test
    public final void testClearEmpty() {
        List<String> list = this.createFromArgsTest(0);
        list.clear();
        assertEquals("(<>,<>)", list.toString());
    }

    @Test
    public final void testClearNonEmpty() {
        List<String> list = this.createFromArgsTest(1, "red", "blue");
        list.clear();
        assertEquals("(<>,<>)", list.toString());
    }

    @Test
    public final void testTransferFromEmpty() {
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsTest(0);
        list2.transferFrom(list1);
        assertEquals("(<>,<>)", list1.toString());
        assertEquals("(<>,<>)", list2.toString());
    }

    @Test
    public final void testTransferFromNonEmpty() {
        List<String> list1 = this.createFromArgsTest(1, "red", "blue");
        List<String> list2 = this.createFromArgsTest(0);
        list2.transferFrom(list1);
        assertEquals("(<>,<>)", list1.toString());
        assertEquals("(<red>,<blue>)", list2.toString());
    }

}
