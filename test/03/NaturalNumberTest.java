import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Parker Boyd
 * @author Leya Ghebremeskel
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
     *
     * Test Cases for constructors.
     *
     */

    /**
     * Tests the Natural Number constructor with no argument.
     */
    @Test
    public final void testNoArgConstructor() {

        NaturalNumber test = this.constructorTest();
        NaturalNumber expected = this.constructorRef();

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number constructor's new instance method.
     *
     */

    @Test
    public final void testConstructorNewInstance() {

        NaturalNumber test = this.constructorTest();
        NaturalNumber testExpected = this.constructorRef();

        NaturalNumber expected = this.constructorRef();
        NaturalNumber expected2 = test.newInstance();

        assertEquals(test, testExpected);
        assertEquals(expected, expected2);

    }

    /**
     * Tests the Natural Number constructor's new instance method with a small
     * number (2).
     *
     */

    @Test
    public final void testConstructorNewInstanceSmall() {

        NaturalNumber test = this.constructorTest(2);
        NaturalNumber testExpected = this.constructorRef(2);

        NaturalNumber expected = this.constructorRef();
        NaturalNumber expected2 = test.newInstance();

        assertEquals(test, testExpected);
        assertEquals(expected, expected2);

    }

    /**
     * Tests the Natural Number constructor's new instance method with a large
     * number.
     *
     */

    @Test
    public final void testConstructorNewInstanceLarge() {

        final int large = 927369220;

        NaturalNumber test = this.constructorTest(large);
        NaturalNumber testExpected = this.constructorRef(large);

        NaturalNumber expected = this.constructorRef();
        NaturalNumber expected2 = test.newInstance();

        assertEquals(test, testExpected);
        assertEquals(expected, expected2);

    }

    /**
     * Tests the Natural Number int constructor.
     */
    @Test
    public final void testConstructorIntEmpty() {

        NaturalNumber test = this.constructorTest();

        NaturalNumber expected = this.constructorRef();

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number int constructor for 0.
     */
    @Test
    public final void testConstructorIntZero() {

        NaturalNumber test = this.constructorTest(0);

        NaturalNumber expected = this.constructorRef(0);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number int constructor for a small number(2).
     */
    @Test
    public final void testConstructorInt() {

        NaturalNumber test = this.constructorTest(2);

        NaturalNumber expected = this.constructorRef(2);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number int constructor for a large number (6482700).
     */
    @Test
    public final void testConstructorIntLarge() {
        final int huge = 6482700;

        NaturalNumber test = this.constructorTest(huge);

        NaturalNumber expected = this.constructorRef(huge);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number string constructor.
     */
    @Test
    public final void testConstructorStringEmpty() {

        NaturalNumber test = this.constructorTest();

        NaturalNumber expected = this.constructorRef();

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number string constructor for a small number (2).
     */
    @Test
    public final void testConstructorString() {

        NaturalNumber test = this.constructorTest("2");

        NaturalNumber expected = this.constructorRef("2");

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number string constructor for a large number (202320).
     */
    @Test
    public final void testConstructorStringLarge() {
        final int large = 202320;

        NaturalNumber test = this.constructorTest("202320");

        NaturalNumber expected = this.constructorRef(large);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number string constructor for 0.
     */
    @Test
    public final void testConstructorStringZero() {

        NaturalNumber test = this.constructorTest("0");

        NaturalNumber expected = this.constructorRef("0");

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number constructor based on another Natural Number.
     */
    @Test
    public final void testConstructorNNEmpty() {

        NaturalNumber test = this.constructorTest();
        NaturalNumber empty = new NaturalNumber1L();

        NaturalNumber expected = this.constructorRef(empty);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number constructor based on another Natural Number for
     * a small number (2).
     */
    @Test
    public final void testConstructorNN() {

        NaturalNumber test = this.constructorTest(2);
        NaturalNumber two = new NaturalNumber1L(2);

        NaturalNumber expected = this.constructorRef(two);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number constructor based on another Natural Number for
     * a large number (1000020).
     */
    @Test
    public final void testConstructorNNLarge() {

        NaturalNumber test = this.constructorTest("1000020");
        final int huge = 1000020;
        NaturalNumber hugeNN = new NaturalNumber1L(huge);

        NaturalNumber expected = this.constructorRef(hugeNN);

        assertEquals(test, expected);

    }

    /**
     * Tests the Natural Number constructor based on another Natural Number for
     * 0.
     */
    @Test
    public final void testConstructorNNZero() {

        NaturalNumber test = this.constructorTest(0);
        NaturalNumber zero = new NaturalNumber1L(0);

        NaturalNumber expected = this.constructorRef(zero);

        assertEquals(test, expected);

    }

    /**
     *
     * Test Cases for isZero.
     *
     */

    /**
     * Tests the isZero method and manipulates the variable using multiplyBy10.
     */
    @Test
    public final void testisZeroTrue() {
        boolean expected = true;

        NaturalNumber test = this.constructorTest();
        test.multiplyBy10(1);
        test.divideBy10();

        boolean result = test.isZero();

        assertEquals(result, expected);

    }

    /**
     * Tests the isZero method by simply initializing.
     */
    @Test
    public final void testisZeroFalse() {
        boolean expected = false;

        NaturalNumber test = this.constructorTest();
        test.multiplyBy10(2);

        boolean result = test.isZero();

        assertEquals(result, expected);

    }

    /**
     * Tests the isZero method and manipulates the variable using divideBy10.
     */
    @Test
    public final void testisZeroDivide() {
        boolean expected = false;
        final int fortyFive = 45;

        NaturalNumber test = this.constructorTest(fortyFive);
        test.divideBy10();

        boolean result = test.isZero();

        assertEquals(result, expected);

    }

    /**
     * Tests the isZero method and manipulates the variable using divideBy10.
     */
    @Test
    public final void testisZeroDivideTrue() {
        boolean expected = true;
        final int fortyFive = 45;

        NaturalNumber test = this.constructorTest(fortyFive);
        test.divideBy10();
        test.divideBy10();

        boolean result = test.isZero();

        assertEquals(result, expected);

    }

    /**
     *
     * Test Cases for MultiplyBy10.
     *
     */

    /**
     * Tests the multiplyBy10 method using 3 and 5.
     */
    @Test

    public final void testMultiplybyTen35() {
        final int three = 3;
        final int five = 5;
        final int result = 35;

        NaturalNumber test = this.constructorTest(three);

        NaturalNumber expected = this.constructorRef(result);

        test.multiplyBy10(five);

        assertEquals(test, expected);

    }

    /**
     * Tests the multiplyBy10 method using 9.
     */
    @Test
    public final void testMultiplybyTen1() {
        final int nine = 9;
        final int result = 19;

        NaturalNumber test = this.constructorTest(1);

        NaturalNumber expected = this.constructorRef(result);

        test.multiplyBy10(nine);

        assertEquals(test, expected);

    }

    /**
     * Tests the multiplyBy10 method using 892730 and 4.
     */
    @Test
    public final void testMultiplybyTen892730() {
        final int huge = 892730;
        final int four = 4;
        final int result = 8927304;

        NaturalNumber test = this.constructorTest(huge);

        NaturalNumber expected = this.constructorRef(result);

        test.multiplyBy10(four);

        assertEquals(test, expected);

    }

    /**
     * Tests the multiplyBy10 method using 0.
     */
    @Test
    public final void testMultiplybyTen0() {

        NaturalNumber test = this.constructorTest();

        NaturalNumber expected = this.constructorRef(2);

        test.multiplyBy10(2);

        assertEquals(test, expected);

    }

    /**
     *
     * Test Cases for DivideBy10.
     *
     */

    /**
     * Tests the divideBy10 method using 50.
     */
    @Test
    public final void testDivideByTen50() {
        final int fifty = 50;
        final int five = 5;

        NaturalNumber test = this.constructorTest(fifty);

        NaturalNumber expected = this.constructorRef(five);

        int remainder = test.divideBy10();

        assertEquals(test, expected);

        assertEquals(remainder, 0);

    }

    /**
     * Tests the divideBy10 method using 67892.
     */
    @Test
    public final void testDivideByTen67892() {

        final int large = 67892;
        final int smaller = 6789;

        NaturalNumber test = this.constructorTest(large);

        NaturalNumber expected = this.constructorRef(smaller);

        int remainder = test.divideBy10();

        assertEquals(test, expected);

        assertEquals(remainder, 2);

    }

    /**
     * Tests the divideBy10 method using 84.
     */
    @Test
    public final void testDivideByTen84() {

        final int eightyFour = 84;
        final int eight = 8;
        final int four = 4;

        NaturalNumber test = this.constructorTest(eightyFour);

        NaturalNumber expected = this.constructorRef(eight);

        int remainder = test.divideBy10();

        assertEquals(test, expected);

        assertEquals(remainder, four);

    }

    /**
     * Tests the divideBy10 method using 3.
     */
    @Test
    public final void testDivideByTen3() {

        final int three = 3;

        NaturalNumber test = this.constructorTest(three);

        NaturalNumber expected = this.constructorRef();

        int remainder = test.divideBy10();

        assertEquals(test, expected);

        assertEquals(remainder, three);

    }

}
