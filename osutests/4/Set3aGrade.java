import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods
 * to be used in grading the Set3 projects. Uses Set1L as the reference
 * implementation.
 *
 * @author Paolo Bucci (.2)
 * @modified Derek Schneider (.641)
 * @modified Wayne Heym (.1)
 *
 * @revised 10/8/2013
 * @revised 10/14/2020
 * @revised 3/27/2023
 *
 */
public class Set3aGrade {

    /**
     * Invokes the {@code Set} constructor for the implementation under test and
     * returns the result.
     *
     * @return the new set
     * @ensures <pre>
     * {@code constructor = {}}
     * </pre>
     */
    private Set<String> constructorTest() {
        return new Set3a<String>();
    }

    /**
     * Invokes the {@code Set} constructor for the reference implementation and
     * returns the result.
     *
     * @return the new set
     * @ensures <pre>
     * {@code constructor = {}}
     * </pre>
     */
    private Set<String> constructorRef() {
        return new Set1L<String>();
    }

    /**
     *
     * Creates and returns a {@code Set<String>} with the given elements.
     *
     * @param args
     *            the elements for the map
     * @return the constructed set
     * @requires <pre>
     * {@code [args entries are all different]}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = [entries in args]}
     * </pre>
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> testSet = this.constructorTest();

        this.createFromArgs(testSet, args);

        return testSet;
    }

    /**
     *
     * Creates and returns a {@code Set<String>} with the given elements.
     *
     * @param args
     *            the elements for the map
     * @return the constructed set
     * @requires <pre>
     * {@code [args entries are all different]}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = [entries in args]}
     * </pre>
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> refSet = this.constructorRef();

        this.createFromArgs(refSet, args);

        return refSet;
    }

    /**
     *
     * Creates and returns a {@code Set<String>} with the given elements.
     *
     * @param ri
     *            the reference-implementation flag
     * @param args
     *            the elements for the map
     * @return the constructed set
     * @requires <pre>
     * {@code [args entries are all different]}
     * </pre>
     * @ensures <pre>
     * {@code createFromArgs = [entries in args]}
     * </pre>
     */
    private void createFromArgs(Set<String> set, String... args) {

        set.clear();

        for (int i = 0; i < args.length; i++) {
            set.add(args[i]);
        }
    }

    @Test
    public final void testConstructor() {
        Set<String> s1 = this.constructorTest();
        Set<String> s2 = this.constructorRef();

        assertEquals(s2, s1);
    }

    @Test
    public final void testAddEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s2 = this.createFromArgsRef("one");
        s1.add("one");
        assertEquals(s2, s1);
    }

    @Test
    public final void testAddNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("one");
        Set<String> s2 = this.createFromArgsRef("one", "two");
        s1.add("two");
        assertEquals(s1, s2);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        // Setup
        String target = "one";
        Set<String> s = this.createFromArgsTest(target);
        Set<String> sExpected = this.createFromArgsRef();
        String key = "on";
        key = key + "e";
        assert target.equals(key) : "Expected target.equals(key)";
        assert target != key : "target and key should be different objects.";

        // Call under test
        String result = s.remove(key);

        // Evaluation
        assertEquals(sExpected, s);
        assertEquals("one", result);
        assertTrue(
                "Violation of: method does not introduce an alias "
                        + "to the parameter (x) in its return value",
                result != key);
    }

    @Test
    public final void testRemoveLeavingNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("one", "two");
        Set<String> s2 = this.createFromArgsRef("two");

        String result = s1.remove("one");

        assertEquals(s2, s1);
        assertEquals("one", result);
    }

    @Test
    public final void testContainsEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s2 = this.createFromArgsRef();

        boolean result = s1.contains("one");

        assertEquals(s2, s1);
        assertEquals(false, result);
    }

    @Test
    public final void testContainsNonEmptyTrue() {
        Set<String> s1 = this.createFromArgsTest("one", "two");
        Set<String> s2 = this.createFromArgsRef("two", "one");

        boolean result = s1.contains("two");

        assertEquals(s2, s1);
        assertEquals(true, result);
    }

    @Test
    public final void testContainsNonEmptyFalse() {
        Set<String> s1 = this.createFromArgsTest("one", "two");
        Set<String> s2 = this.createFromArgsRef("two", "one");

        boolean result = s1.contains("three");

        assertEquals(s2, s1);
        assertEquals(false, result);
    }

    @Test
    public final void testSizeEmpty() {
        Set<String> s1 = this.createFromArgsTest();
        Set<String> s2 = this.createFromArgsRef();

        int size = s1.size();

        assertEquals(s2, s1);
        assertEquals(0, size);
    }

    @Test
    public final void testSizeNonEmpty() {
        Set<String> s1 = this.createFromArgsTest("one", "two");
        Set<String> s2 = this.createFromArgsRef("two", "one");

        int size = s1.size();

        assertEquals(s2, s1);
        assertEquals(2, size);
    }

    // End of basic test cases--the rest are extras to provide more thorough
    // testing

    @Test
    public final void testAdd_Root_L_R_LL_LR_RL_RR() {
        Set<String> s1 = this.createFromArgsTest("d", "b", "c", "f", "e", "g",
                "a");
        Set<String> s2 = this.createFromArgsRef("d", "b", "f", "a", "c", "e",
                "g");

        assertEquals(s2, s1);
    }

    @Test
    public final void testRemove_TRoot_LEmpty_REmpty() {
        Set<String> s1 = this.createFromArgsTest("a");
        Set<String> s2 = this.createFromArgsRef();

        String str = s1.remove("a");

        assertEquals(s2, s1);
        assertEquals("a", str);
    }

    @Test
    public final void testRemove_TRoot_LNEmpty_REmpty() {
        Set<String> s1 = this.createFromArgsTest("d", "b", "a", "c");
        Set<String> s2 = this.createFromArgsRef("b", "a", "c");

        String str = s1.remove("d");

        assertEquals(s2, s1);
        assertEquals("d", str);
    }

    @Test
    public final void testRemove_TRoot_LEmpty_RNEmpty() {
        Set<String> s1 = this.createFromArgsTest("d", "f", "e", "g");
        Set<String> s2 = this.createFromArgsRef("f", "e", "g");

        String str = s1.remove("d");

        assertEquals(s2, s1);
        assertEquals("d", str);
    }

    @Test
    public final void testRemove_TRoot_LNEmpty_RNEmpty() {
        Set<String> s1 = this.createFromArgsTest("d", "f", "b", "c", "e", "g",
                "a");
        Set<String> s2 = this.createFromArgsRef("b", "f", "a", "c", "e", "g");

        String str = s1.remove("d");

        assertEquals(s2, s1);
        assertEquals("d", str);
    }

    @Test
    public final void testRemove_TRoot_LNEmpty_RNEmptyLEmpty() {
        Set<String> s1 = this.createFromArgsTest("d", "f", "b", "c", "g", "a");
        Set<String> s2 = this.createFromArgsRef("b", "f", "a", "c", "g");

        String str = s1.remove("d");

        assertEquals(s2, s1);
        assertEquals("d", str);
    }

    @Test
    public final void testRemoveMany() {
        Set<String> s1 = this.createFromArgsTest("d", "b", "c", "f", "e", "g",
                "a");
        Set<String> s2 = this.createFromArgsRef("d", "b", "f", "a", "c", "e",
                "g");

        String str1 = s1.remove("d");
        String str2 = s2.remove("d");

        assertEquals(s2, s1);
        assertEquals("d", str1);

        str1 = s1.remove("f");
        str2 = s2.remove("f");

        assertEquals(s2, s1);
        assertEquals("f", str1);

        str1 = s1.remove("b");
        str2 = s2.remove("b");

        assertEquals(s2, s1);
        assertEquals("b", str1);

        str1 = s1.remove("g");
        str2 = s2.remove("g");

        assertEquals(s2, s1);
        assertEquals("g", str1);

        str1 = s1.remove("e");
        str2 = s2.remove("e");

        assertEquals(s2, s1);
        assertEquals("e", str1);

        str1 = s1.remove("c");
        str2 = s2.remove("c");

        assertEquals(s2, s1);
        assertEquals("c", str1);

        str1 = s1.remove("a");
        str2 = s2.remove("a");

        assertEquals(s2, s1);
        assertEquals("a", str1);
    }

    @Test
    public final void testContainsMany() {
        Set<String> s1 = this.createFromArgsTest("dh", "bh", "ch", "fh", "eh",
                "gh", "ah");
        Set<String> s2 = this.createFromArgsRef("dh", "bh", "fh", "ah", "ch",
                "eh", "gh");

        boolean c1 = s1.contains("ah");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("bh");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("ch");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("dh");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("eh");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("fh");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("gh");

        assertEquals(s2, s1);
        assertEquals(true, c1);

        c1 = s1.contains("aa");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("az");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("ba");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("bz");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("ca");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("cz");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("da");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("dz");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("ea");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("ez");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("fa");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("fz");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("ga");

        assertEquals(s2, s1);
        assertEquals(false, c1);

        c1 = s1.contains("gz");

        assertEquals(s2, s1);
        assertEquals(false, c1);
    }

}
