import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class NaturalNumberTest {
    /**
     * Twenty Three.
     */
    static final int TWENTY_THREE = 23;
    /**
     * Two hundred thirty.
     */
    static final int TWO_HUNDRED_THIRTY = 230;
    /**
     * Two hundred thirty one.
     */
    static final int TWO_HUNDRED_THIRTY_ONE = 231;
    /**
     * Seven.
     */
    static final int SEVEN = 7;

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

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /**
     * Default Constructor Test.
     */
    @Test
    public void testDefaultConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber expected = this.constructorRef();

        assertEquals(n, expected);
    }

    /**
     * int Constructor Test with zero.
     */
    @Test
    public void testIntConstructorZero() {
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber expected = this.constructorRef(1);
        assertEquals(n, expected);

    }

    /**
     * int Constructor Test with one.
     */
    @Test
    public void testIntConstructorOne() {
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber expected = this.constructorRef(1);
        assertEquals(n, expected);

    }

    /**
     * String Constructor Test with zero.
     */
    @Test
    public void testStringConstructorZero() {
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber expected = this.constructorRef("0");
        assertEquals(n, expected);
    }

    /**
     * String Constructor Test with One.
     */
    @Test
    public void testStringConstructorOne() {
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber expected = this.constructorRef("1");
        assertEquals(n, expected);
    }

    /**
     * NaturalNumber constructor Test with zero.
     */
    @Test
    public void testNNConstructorZero() {
        NaturalNumber number = this.constructorTest(0);
        NaturalNumber n = this.constructorTest(number);
        NaturalNumber expected = this.constructorRef(number);
        assertEquals(n, expected);
    }

    /**
     * NaturalNumber constructor Test with one.
     */
    @Test
    public void testNNConstructorOne() {
        NaturalNumber number = this.constructorTest(1);
        NaturalNumber n = this.constructorTest(number);
        NaturalNumber expected = this.constructorRef(number);
        assertEquals(n, expected);
    }

    /**
     * Test for multiplyBy10 with Zero.
     */
    @Test
    public void testMultBy10Zero() {

        NaturalNumber n = this.constructorTest(TWENTY_THREE);
        NaturalNumber expected = this.constructorTest(TWO_HUNDRED_THIRTY);
        n.multiplyBy10(0);
        assertEquals(n, expected);
    }

    /**
     * Test for multiplyBy10 with 1.
     */
    @Test
    public void testMultBy10One() {
        NaturalNumber n = this.constructorTest(TWENTY_THREE);
        NaturalNumber expected = this.constructorTest(TWO_HUNDRED_THIRTY_ONE);
        n.multiplyBy10(1);
        assertEquals(n, expected);
    }

    /**
     * Test for divideBy10 with 230.
     */
    @Test
    public void divideBy10TwoThirty() {
        NaturalNumber n = this.constructorTest(TWO_HUNDRED_THIRTY);
        NaturalNumber expected = this.constructorRef(TWENTY_THREE);
        int removed = n.divideBy10();
        assertEquals(removed, 0);
        assertEquals(n, expected);
    }

    /**
     * Test for divideBy10 with 231.
     */
    @Test
    public void divideBy10TwoThirtyOne() {
        NaturalNumber n = this.constructorTest(TWO_HUNDRED_THIRTY_ONE);
        NaturalNumber expected = this.constructorRef(TWENTY_THREE);
        int removed = n.divideBy10();
        assertEquals(removed, 1);
        assertEquals(n, expected);
    }

    /**
     * Test for divideBy10 with 10.
     */
    @Test
    public void divideBy10Ten() {
        NaturalNumber n = this.constructorTest(NaturalNumber.RADIX);
        NaturalNumber expected = this.constructorRef(1);
        int removed = n.divideBy10();
        assertEquals(removed, 0);
        assertEquals(n, expected);
    }

    /**
     * Test for divideBy10 with 7.
     */
    @Test
    public void divideBy10Seven() {
        NaturalNumber n = this.constructorTest(SEVEN);
        NaturalNumber expected = this.constructorRef();
        int removed = n.divideBy10();
        assertEquals(removed, SEVEN);
        assertEquals(n, expected);
    }

    /**
     * Test for divideBy10 with 0.
     */
    @Test
    public void divideBy10Zero() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber expected = this.constructorRef();
        int removed = n.divideBy10();
        assertEquals(removed, 0);
        assertEquals(n, expected);
    }

    /**
     * Test for isZero(True).
     */
    @Test
    public void isZeroTrue() {
        NaturalNumber n = this.constructorTest();
        boolean isZero = n.isZero();
        assertEquals(isZero, true);
    }

    /**
     * Test for isZero(False).
     */
    @Test
    public void isZeroFalse() {
        NaturalNumber n = this.constructorTest(TWENTY_THREE);
        boolean isZero = n.isZero();
        assertEquals(isZero, false);
    }
}
