import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Zhengyang Peng, Zeyu Huang
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Tests int non-argument constructor.
     */
    @Test
    public final void testNoArgumentNaturalNumberConstructor() {
        NaturalNumber e = this.constructorTest();
        NaturalNumber expect = this.constructorRef();
        assertEquals(e, expect);
    }

    /**
     * Tests int constructor when e is 0.
     */
    @Test
    public final void testIntConstructorZero() {
        int i = 0;
        NaturalNumber e = this.constructorTest(i);
        NaturalNumber expect = this.constructorRef(i);
        assertEquals(e, expect);
    }

    /**
     * Tests int constructor when e is a small integer.
     */
    @Test
    public final void testIntConstructorSmallNumber() {
        int i = 5;
        NaturalNumber e = this.constructorTest(i);
        NaturalNumber expect = this.constructorRef(i);
        assertEquals(e, expect);
    }

    /**
     * Tests int constructor when i is the maximum.
     */
    @Test
    public final void testIntMaxConstructor() {
        int i = Integer.MAX_VALUE;
        NaturalNumber e = this.constructorTest(i);
        NaturalNumber expect = this.constructorRef(i);
        assertEquals(e, expect);
    }

    /**
     * Tests string constructor when s is a regular string.
     */
    @Test
    public final void testStringConstructor() {
        String s = "5";
        NaturalNumber e = this.constructorTest(s);
        NaturalNumber expect = this.constructorRef(s);
        assertEquals(e, expect);
    }

    /**
     * Tests string constructor when s is "0".
     */
    @Test
    public final void testStringConstructorZero() {
        String s = "0";
        NaturalNumber e = this.constructorTest(s);
        NaturalNumber expect = this.constructorRef(s);
        assertEquals(e, expect);
    }

    /**
     * Tests naturalnumber constructor when n is a regular number.
     */
    @Test
    public final void testConstructorNaturalNumber() {
        NaturalNumber n = new NaturalNumber1L(276);
        NaturalNumber e = this.constructorTest(n);
        NaturalNumber expect = this.constructorRef(n);
        assertEquals(e, expect);
    }

    /**
     * Tests naturalnumber constructor when n is 0.
     */
    @Test
    public final void testConstructorNaturalNumberZero() {
        NaturalNumber n = new NaturalNumber1L(0);
        NaturalNumber e = this.constructorTest(n);
        NaturalNumber expect = this.constructorRef(n);
        assertEquals(e, expect);
    }

    /**
     * Tests multiplyBy when e is 0.
     */
    @Test
    public final void testmultiplyBy10Zero() {
        NaturalNumber e = this.constructorTest(0);
        int k = 7;
        e.multiplyBy10(k);
        NaturalNumber expect = this.constructorRef(7);
        assertEquals(e, expect);
    }

    /**
     * Tests multiplyBy when e is a small number.
     */
    @Test
    public final void testmultiplyBy10SmallNumber() {
        NaturalNumber e = this.constructorTest(12);
        int k = 1;
        e.multiplyBy10(k);
        NaturalNumber expect = this.constructorRef(121);
        assertEquals(e, expect);
    }

    /**
     * Tests DivideBy10 when e is 0.
     */
    @Test
    public final void testDivideBy10Zero() {
        NaturalNumber e = this.constructorTest();
        int remainder = e.divideBy10();
        NaturalNumber expect = this.constructorRef(0);
        int remainderExpect = 0;
        assertEquals(e, expect);
        assertEquals(remainder, remainderExpect);
    }

    /**
     * Tests DivideBy10 when e is a small number.
     */
    @Test
    public final void testDivideBy10SmallNumber() {
        NaturalNumber e = this.constructorTest(15);
        int remainder = e.divideBy10();
        NaturalNumber expect = this.constructorRef(1);
        int remainderExpect = 5;
        assertEquals(e, expect);
        assertEquals(remainder, remainderExpect);
    }

    /**
     * Tests IsZero when e is a small number.
     */
    @Test
    public final void testIsZeroSmallNumber() {
        NaturalNumber e = this.constructorTest(123);
        assertTrue(!e.isZero());
    }

    /**
     * Tests IsZero when e is a large number.
     */
    @Test
    public final void testIsZeroLargeNumber() {
        NaturalNumber e = this.constructorTest(123456789);
        assertTrue(!e.isZero());
    }

    /**
     * Tests IsZero when e is 0.
     */
    @Test
    public final void testIsZeroZero() {
        NaturalNumber e = this.constructorTest();
        assertTrue(e.isZero());
    }

}
