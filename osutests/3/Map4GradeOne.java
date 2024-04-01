import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods to be used in grading the Map4 projects. Uses Map1L as the reference
 * implementation.
 *
 * @author Paolo Bucci (.2)
 * @modified Derek Schneider (.641)
 * @modified Wayne Heym (.1)
 * @revised 10/2/2021 (just to change mode of map from updates to replaces in
 *          createFromArgs)
 *
 */
public class Map4GradeOne {

    /**
     * Invokes the {@code Map} constructor for the implementation under test and
     * returns the result.
     *
     * @return the new map
     * @ensures <pre>
     * {@code constructor = {}}
     * </pre>
     */
    private Map<String, String> constructorTest() {
        return new Map4<String, String>();
    }

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures <pre>
     * {@code constructor = {}}
     * </pre>
     */
    private Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} with the given entries.
     *
     * @param ri
     *            the reference-implementation flag
     * @param args
     *            the (key, value) pairs for the map
     * @replaces map
     * @requires <pre>
     * {@code [args.length is even and all keys must be distinct]}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = [pairs in args]}
     * </pre>
     */
    private void createFromArgs(Map<String, String> map, String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";

        map.clear();

        for (int i = 0; i < args.length; i += 2) {
            map.add(args[i], args[i + 1]);
        }
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * {@code [args.length is even and all keys must be distinct]}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = [pairs in args]}
     * </pre>
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";

        Map<String, String> testMap = this.constructorTest();

        this.createFromArgs(testMap, args);

        return testMap;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * {@code [args.length is even and all keys must be distinct]}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = [pairs in args]}
     * </pre>
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";

        Map<String, String> refMap = this.constructorRef();

        this.createFromArgs(refMap, args);

        return refMap;
    }

    @Test
    public final void testConstructor() {
        Map<String, String> m1 = this.constructorTest();
        Map<String, String> m2 = this.constructorRef();
        assertEquals(m2, m1);
    }

    @Test
    public final void testAddEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        m1.add("red", "one");
        assertEquals(m2, m1);
    }

    @Test
    public final void testAddNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("blue", "two");
        Map<String, String> m2 = this.createFromArgsRef("red", "one", "blue",
                "two");
        m1.add("red", "one");
        assertEquals(m2, m1);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef();
        Map.Pair<String, String> result = m1.remove("red");
        assertEquals(m2, m1);
        assertEquals("red", result.key());
        assertEquals("one", result.value());
    }

    @Test
    public final void testRemoveLeavingNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("blue", "two", "red",
                "one");
        Map<String, String> m2 = this.createFromArgsRef("blue", "two");
        Map.Pair<String, String> result = m1.remove("red");
        assertEquals(m2, m1);
        assertEquals("red", result.key());
        assertEquals("one", result.value());
    }

    @Test
    public final void testValue() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        String value = m1.value("red");
        assertEquals(m2, m1);
        assertEquals("one", value);
    }

    @Test
    public final void testHasKeyEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef();
        boolean result = m1.hasKey("red");
        assertEquals(m2, m1);
        assertEquals(false, result);
    }

    @Test
    public final void testHasKeyNonEmptyTrue() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        boolean result = m1.hasKey("red");
        assertEquals(m2, m1);
        assertEquals(true, result);
    }

    @Test
    public final void testHasKeyNonEmptyFalse() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        boolean result = m1.hasKey("blue");
        assertEquals(m2, m1);
        assertEquals(false, result);
    }

    @Test
    public final void testSizeEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef();
        int size = m1.size();
        assertEquals(m2, m1);
        assertEquals(0, size);
    }

    @Test
    public final void testSizeNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        int size = m1.size();
        assertEquals(m2, m1);
        assertEquals(1, size);
    }

    // End of basic test cases--the rest are extras to provide more thorough
    // testing

    @Test
    public final void testIteratorEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef();
        for (Map.Pair<String, String> pair : m1) {
            m2.add(pair.key(), pair.value());
        }
        assertEquals(m2, m1);
    }

    @Test
    public final void testIteratorNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one", "blue",
                "two");
        Map<String, String> m2 = this.createFromArgsRef();
        for (Map.Pair<String, String> pair : m1) {
            m2.add(pair.key(), pair.value());
        }
        assertEquals(m2, m1);
    }

    @Test
    public final void testClearEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef();
        m1.clear();
        assertEquals(m2, m1);
    }

    @Test
    public final void testClearNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef();
        m1.clear();
        assertEquals(0, m1.size());
        assertEquals(m2, m1);
    }

    @Test
    public final void testRemoveAnyOnlyOne() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef();
        Map.Pair<String, String> p = m1.removeAny();
        assertEquals(m2, m1);
        assertEquals("red", p.key());
        assertEquals("one", p.value());
    }

    @Test
    public final void testRemoveAnyABunch() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one", "green",
                "two", "blue", "three", "yellow", "four", "orange", "five");
        Map<String, String> m2 = this.createFromArgsRef("red", "one", "green",
                "two", "blue", "three", "yellow", "four", "orange", "five");
        Map<String, String> m3 = this.createFromArgsRef();
        while (m1.size() > 0) {
            Map.Pair<String, String> p = m1.removeAny();
            assertTrue(m2.hasKey(p.key()));
            assertFalse(m1.hasKey(p.key()));
            assertTrue(m2.hasValue(p.value()));
            assertFalse(m1.hasValue(p.value()));
        }
        assertEquals(m3, m1);
    }

    @Test
    public final void testSharesKeyWithEmptyEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef();
        boolean result = m1.sharesKeyWith(m2);
        assertEquals(false, result);
        assertEquals("{}", m1.toString());
        assertEquals("{}", m2.toString());
    }

    @Test
    public final void testSharesKeyWithNonEmptyEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef();
        boolean result = m1.sharesKeyWith(m2);
        assertEquals(false, result);
        assertEquals("{(red,one)}", m1.toString());
        assertEquals("{}", m2.toString());
    }

    @Test
    public final void testSharesKeyWithEmptyNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        boolean result = m1.sharesKeyWith(m2);
        assertEquals(false, result);
        assertEquals("{}", m1.toString());
        assertEquals("{(red,one)}", m2.toString());
    }

    @Test
    public final void testSharesKeyWithNonEmptyNonEmptyFalse() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("green", "one");
        boolean result = m1.sharesKeyWith(m2);
        assertEquals(false, result);
        assertEquals("{(red,one)}", m1.toString());
        assertEquals("{(green,one)}", m2.toString());
    }

    @Test
    public final void testSharesKeyWithNonEmptyNonEmptyTrue() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("red", "two");
        boolean result = m1.sharesKeyWith(m2);
        assertEquals(true, result);
        assertEquals("{(red,one)}", m1.toString());
        assertEquals("{(red,two)}", m2.toString());
    }

    @Test
    public final void testCombineWithEmptyEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef();
        m1.combineWith(m2);
        assertEquals("{}", m1.toString());
        assertEquals("{}", m2.toString());
    }

    @Test
    public final void testCombineWithNonEmptyEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef();
        m1.combineWith(m2);
        assertEquals("{(red,one)}", m1.toString());
        assertEquals("{}", m2.toString());
    }

    @Test
    public final void testCombineWithEmptyNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest();
        Map<String, String> m2 = this.createFromArgsRef("red", "one");
        m1.combineWith(m2);
        assertEquals("{(red,one)}", m1.toString());
        assertEquals("{}", m2.toString());
    }

    @Test
    public final void testCombineWithNonEmptyNonEmpty() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("blue", "two");
        m1.combineWith(m2);
        Map<String, String> m3 = this.createFromArgsRef();
        m3.add("red", "one");
        m3.add("blue", "two");
        assertEquals(m1, m3);
        assertEquals("{}", m2.toString());
    }

    @Test
    public final void testKey() {
        Map<String, String> m1 = this.createFromArgsTest("red", "one");
        Map<String, String> m2 = this.createFromArgsRef("red", "one");

        String key = m1.key("one");
        assertEquals("red", key);
        assertEquals(m2, m1);
    }

    @Test
    public final void testHasValueEmpty() {
        Map<String, String> m = this.createFromArgsTest();
        boolean result = m.hasValue("one");
        assertEquals("{}", m.toString());
        assertEquals(false, result);
    }

    @Test
    public final void testHasValueNonEmptyTrue() {
        Map<String, String> m = this.createFromArgsTest("red", "one");
        boolean result = m.hasValue("one");
        assertEquals("{(red,one)}", m.toString());
        assertEquals(true, result);
    }

    @Test
    public final void testHasValueNonEmptyFalse() {
        Map<String, String> m = this.createFromArgsTest("red", "one");
        boolean result = m.hasValue("two");
        assertEquals("{(red,one)}", m.toString());
        assertEquals(false, result);
    }

}
