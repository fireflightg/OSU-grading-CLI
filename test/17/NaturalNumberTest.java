import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Meena Kalil
 * @author Manraj Hansra
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Define constants for magic numbers.
     */
    private static final int TEST_VALUE_1 = 123;

    /**
     * Define constants for magic numbers.
     */
    private static final String TEST_STRING_1 = "987";

    /**
     * Define constants for magic numbers.
     */
    private static final int TEST_VALUE_2 = 789;

    /**
     * Define constants for magic numbers.
     */
    private static final String TEST_STRING_2 = "654";

    /**
     * Define constants for magic numbers.
     */
    private static final int TEST_MULTIPLIER = 2;

    /**
     * Define constants for magic numbers.
     */
    private static final int TEST_DIVIDEND = 456;

    /**
     * Define constants for magic numbers.
     */
    private static final int EXPECTED_REMAINDER = 6;

    /**
     * Define constants for magic numbers.
     */
    private static final int TEST_DIVIDEND_ZERO = 7;

    /**
     * Define constants for magic numbers.
     */
    private static final int EXPECTED_REMAINDER_ZERO = 7;

    /**
     * Define constants for magic numbers.
     */
    private static final int TEST_MULTIPLIER_LARGE = 3;

    /**
     * Define constants for magic numbers.
     */
    private static final int LARGE_NUMBER = 9999999;

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
     * Test cases for constructorTest() methods.
     */
    @Test
    public void testConstructorTestDefault() {
        NaturalNumber n = this.constructorTest();
        assertEquals("0", n.toString());
    }

    /**
     * Test cases for constructorTest() methods.
     */
    @Test
    public void testConstructorTestWithInt() {
        NaturalNumber n = this.constructorTest(TEST_VALUE_1);
        assertEquals(Integer.toString(TEST_VALUE_1), n.toString());
    }

    /**
     * Test cases for constructorTest() methods.
     */
    @Test
    public void testConstructorTestWithString() {
        NaturalNumber n = this.constructorTest(TEST_STRING_1);
        assertEquals(TEST_STRING_1, n.toString());
    }

    /**
     * Test cases for constructorTest() methods.
     */
    @Test
    public void testConstructorTestWithNaturalNumber() {
        NaturalNumber source = new NaturalNumber1L(TEST_DIVIDEND);
        NaturalNumber n = this.constructorTest(source);
        assertEquals(source.toString(), n.toString());
    }

    /**
     * Test cases for constructorRef() methods.
     */
    @Test
    public void testConstructorRefDefault() {
        NaturalNumber n = this.constructorRef();
        assertEquals("0", n.toString());
    }

    /**
     * Test cases for constructorRef() methods.
     */
    @Test
    public void testConstructorRefWithInt() {
        NaturalNumber n = this.constructorRef(TEST_VALUE_2);
        assertEquals(Integer.toString(TEST_VALUE_2), n.toString());
    }

    /**
     * Test cases for constructorRef() methods.
     */
    @Test
    public void testConstructorRefWithString() {
        NaturalNumber n = this.constructorRef(TEST_STRING_2);
        assertEquals(TEST_STRING_2, n.toString());
    }

    /**
     * Test cases for constructorRef() methods.
     */
    @Test
    public void testConstructorRefWithNaturalNumber() {
        NaturalNumber source = new NaturalNumber1L(TEST_DIVIDEND);
        NaturalNumber n = this.constructorRef(source);
        assertEquals(source.toString(), n.toString());
    }

    /**
     * Test cases for multiplyBy10 method.
     */
    @Test
    public void testMultiplyBy10() {
        NaturalNumber n = this.constructorTest(TEST_VALUE_1);
        n.multiplyBy10(TEST_MULTIPLIER);
        assertEquals("12300", n.toString());
    }

    /**
     * Test cases for multiplyBy10 method.
     */
    @Test
    public void testMultiplyBy10WithZero() {
        NaturalNumber n = this.constructorTest();
        n.multiplyBy10(TEST_MULTIPLIER);
        assertEquals("0", n.toString());
    }

    /**
     * Test cases for divideBy10 method.
     */
    @Test
    public void testDivideBy10() {
        NaturalNumber n = this.constructorTest(TEST_DIVIDEND);
        int remainder = n.divideBy10();
        assertEquals("45", n.toString());
        assertEquals(EXPECTED_REMAINDER, remainder);
    }

    /**
     * Test cases for divideBy10 method.
     */
    @Test
    public void testDivideBy10WithZero() {
        NaturalNumber n = this.constructorTest();
        int remainder = n.divideBy10();
        assertEquals("0", n.toString());
        assertEquals(0, remainder);
    }

    /**
     * Test cases for isZero method.
     */
    @Test
    public void testIsZeroWithNonZero() {
        NaturalNumber n = this.constructorTest(TEST_VALUE_2);
        assertFalse(n.isZero());
    }

    /**
     * Test cases for isZero method.
     */
    @Test
    public void testIsZeroWithZero() {
        NaturalNumber n = this.constructorTest();
        assertTrue(n.isZero());
    }

    // Additional test cases

    /**
     * Test cases for multiplyBy10 method.
     */
    @Test
    public void testMultiplyBy10WithLargeNumber() {
        NaturalNumber n = this.constructorTest(LARGE_NUMBER);
        n.multiplyBy10(TEST_MULTIPLIER_LARGE);
        assertEquals("99999990000", n.toString());
    }

    /**
     * Test cases for divideBy10 method.
     */
    @Test
    public void testDivideBy10UntilZero() {
        NaturalNumber n = this.constructorTest(TEST_VALUE_1);
        while (!n.isZero()) {
            n.divideBy10();
        }
        assertTrue(n.isZero());
    }

    /**
     * Test cases for divideBy10 method.
     */
    @Test
    public void testDivideBy10WithSingleDigit() {
        NaturalNumber n = this.constructorTest(TEST_DIVIDEND_ZERO);
        int remainder = n.divideBy10();
        assertEquals("0", n.toString());
        assertEquals(EXPECTED_REMAINDER_ZERO, remainder);
    }
}
