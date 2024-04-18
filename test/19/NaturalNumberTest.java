import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author A. Abumaike, L. Oden, K. Abukar
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
     * Test int constructor with zero.
     */
    @Test
    public void testConstructorIntEdge() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber testExpected = this.constructorRef(0);
        assertEquals(testExpected, test);

    }

    /**
     * Test int constructor with max int value.
     */
    @Test
    public void testConstructorIntHard() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber testExpected = this.constructorRef(Integer.MAX_VALUE);

        assertEquals(testExpected, test);

    }

    /**
     * Test int constructor with a normal int value.
     */
    @Test
    public void testConstructorIntEasy() {
        final int number = 12345;
        NaturalNumber test = this.constructorTest(number);
        NaturalNumber testExpected = this.constructorRef(number);
        assertEquals(testExpected, test);

    }

    /**
     * Test string constructor with huge string value.
     */
    @Test
    public void testConstructorStringHard() {
        NaturalNumber test = this
                .constructorTest("123456789012345678901234567890");
        NaturalNumber testExpected = this
                .constructorRef("123456789012345678901234567890");
        assertEquals(testExpected, test);

    }

    /**
     * Test empty string constructor.
     */
    @Test
    public void testConstructorStringEdge() {
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber testExpected = this.constructorRef("0");
        assertEquals(testExpected, test);

    }

    /**
     * Test NaturalNumber constructor with 0.
     */
    @Test
    public void testConstructorNNEdge() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber testExpected = this.constructorRef();
        assertEquals(testExpected, test);

    }

    /**
     * Test NaturalNumber constructor with max int value.
     */
    @Test
    public void testConstructorNNHard() {
        NaturalNumber test = this
                .constructorTest(new NaturalNumber3(Integer.MAX_VALUE));
        NaturalNumber testExpected = this
                .constructorRef(new NaturalNumber3(Integer.MAX_VALUE));
        assertEquals(testExpected, test);

    }

    /**
     * Test isZero method with zero.
     */
    @Test
    public void testIsZeroIntEdge() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber testExpected = this.constructorRef(0);
        assertEquals(testExpected.isZero(), test.isZero());
    }

    /**
     * Test isZero with max int value.
     */
    @Test
    public void testIsZeroIntHard() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber testExpected = this.constructorRef(Integer.MAX_VALUE);
        assertEquals(testExpected.isZero(), test.isZero());
    }

    /**
     * Test isZero with empty constructor.
     */
    @Test
    public void testIsZeroIntEasy() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber testExpected = this.constructorRef();

        assertEquals(testExpected.isZero(), test.isZero());
    }

    /**
     * Test isZero with String constructor (edge case).
     */
    @Test
    public void testIsZeroStringEdge() {
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber testExpected = this.constructorRef("0");
        assertEquals(testExpected.isZero(), test.isZero());
    }

    /**
     * Test isZero with String constructor with large number (Hard).
     */
    @Test
    public void testIsZeroStringHard() {
        NaturalNumber test = this.constructorTest("7500000");
        NaturalNumber testExpected = this.constructorRef("7500000");
        assertEquals(testExpected.isZero(), test.isZero());
    }

    /**
     * Test isZero with large NaturalNumber, using strings in the NaturalNumber
     * constructor.
     */
    @Test
    public void testIsZeroNNHard() {
        NaturalNumber test = this.constructorTest(new NaturalNumber3(
                "12345678901234567890123456789012345678901234567890"));
        NaturalNumber testExpected = this.constructorRef(
                "12345678901234567890123456789012345678901234567890");
        assertEquals(testExpected.isZero(), test.isZero());
    }

    /**
     * Test multiplyBy10 with int constructor.
     */
    @Test
    public void testMultiplyBy10IntEasy() {
        final int a = 439;
        final int b = 4;
        NaturalNumber test = this.constructorTest(a);
        NaturalNumber testExpected = this.constructorRef(a);
        testExpected.multiplyBy10(b);
        test.multiplyBy10(b);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with int constructor.
     */
    @Test
    public void testMultiplyBy10IntHard() {
        final int a = 231232;
        final int b = 5;
        NaturalNumber test = this.constructorTest(a);
        NaturalNumber testExpected = this.constructorRef(a);
        testExpected.multiplyBy10(b);
        test.multiplyBy10(b);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with int constructor.
     */
    @Test
    public void testMultiplyBy10IntEdge() {
        final int seven = 7;
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber testExpected = this.constructorRef(0);
        testExpected.multiplyBy10(seven);
        test.multiplyBy10(seven);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with int constructor.
     */
    @Test
    public void testMultiplyBy10IntEdge2() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber testExpected = this.constructorRef(0);
        testExpected.multiplyBy10(0);
        test.multiplyBy10(0);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with NaturalNumber constructor.
     */
    @Test
    public void testMultiplyBy10NNHard() {
        final int a = 8;
        NaturalNumber test = this
                .constructorTest(new NaturalNumber3(Integer.MAX_VALUE));
        NaturalNumber testExpected = this
                .constructorRef(new NaturalNumber3(Integer.MAX_VALUE));
        testExpected.multiplyBy10(a);
        test.multiplyBy10(a);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with NaturalNumber constructor.
     */
    @Test
    public void multiplyBy10NNEdge() {
        final int a = 6;
        NaturalNumber test = this.constructorTest(new NaturalNumber3());
        NaturalNumber testExpected = this.constructorRef(new NaturalNumber3());
        testExpected.multiplyBy10(a);
        test.multiplyBy10(a);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with NaturalNumber constructor.
     */
    @Test
    public void testMultiplyBy10NNHard2() {
        final int nine = 9;
        NaturalNumber test = this.constructorTest(new NaturalNumber3(
                "123456123455231312345612345523131234561234552313"));
        NaturalNumber testExpected = this.constructorRef(new NaturalNumber3(
                "123456123455231312345612345523131234561234552313"));
        testExpected.multiplyBy10(nine);
        test.multiplyBy10(nine);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with String constructor.
     */
    @Test
    public void testMultiplyBy10StringEdge() {
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber testExpected = this.constructorRef("0");
        testExpected.multiplyBy10(0);
        test.multiplyBy10(0);
        assertEquals(testExpected, test);
    }

    /**
     * Test multiplyBy10 with String constructor.
     */
    @Test
    public void testMultiplyBy10StringHard() {
        final int four = 4;
        final int seven = 7;
        NaturalNumber test = this.constructorTest("12303212");
        NaturalNumber testExpected = this.constructorRef("12303212");
        testExpected.multiplyBy10(four);
        test.multiplyBy10(four);
        testExpected.multiplyBy10(seven);
        test.multiplyBy10(seven);
        assertEquals(testExpected, test);
    }

    /**
     * Test divideBy10 with int constructor.
     */
    public void testDivideBy10IntEdge() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber testExpected = this.constructorRef(0);
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);

    }

    /**
     * Test divideBy10 with int constructor.
     */
    public void testDivideBy10IntEasy() {
        final int number = 12345;
        NaturalNumber test = this.constructorTest(number);
        NaturalNumber testExpected = this.constructorRef(number);
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);

    }

    /**
     * Test divideBy10 with int constructor.
     */
    public void testDivideBy10IntHard() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber testExpected = this.constructorRef(Integer.MAX_VALUE);
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);

    }

    /**
     * Test divideBy10 with string constructor.
     */
    public void testDivideBy10StringEdge() {
        NaturalNumber test = this.constructorTest("");
        NaturalNumber testExpected = this.constructorRef("");
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);

    }

    /**
     * Test divideBy10 with string constructor.
     */
    public void testDivideBy10StringHard() {
        NaturalNumber test = this
                .constructorTest("123221321313241535712391462387519231723");
        NaturalNumber testExpected = this
                .constructorRef("123221321313241535712391462387519231723");
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);

    }

    /**
     * Test divideBy10 with NaturalNumber constructor.
     */
    public void testDivideBy10NNEdge() {
        NaturalNumber test = this.constructorTest(new NaturalNumber3());
        NaturalNumber testExpected = this.constructorRef(new NaturalNumber3());
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);

    }

    /**
     * Test divideBy10 with NaturalNumber constructor.
     */
    public void testDivideBy10NNHard() {
        NaturalNumber test = this
                .constructorTest(new NaturalNumber3(Integer.MAX_VALUE));
        NaturalNumber testExpected = this
                .constructorRef(new NaturalNumber3(Integer.MAX_VALUE));
        int returned = test.divideBy10();
        int returnedExpected = testExpected.divideBy10();
        assertEquals(testExpected, test);
        assertEquals(returnedExpected, returned);
    }
}
