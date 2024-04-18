import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Lexie Metsika and Lexie Wheeler
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

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    //test for default constructor
    @Test
    public final void testDefultConstructor() {
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber n = this.constructorTest();
        assertEquals(nExpected, n);

    }

    //test for int constructor
    @Test
    public final void testIntConstructor() {
        NaturalNumber n = this.constructorRef(5);
        assertEquals(5, n.toInt());

    }

    //test for string constructor
    @Test
    public final void testStringConstructor() {
        NaturalNumber n = this.constructorRef("5");
        assertEquals(5, n.toInt());
    }

    //test for NN constructor
    @Test
    public final void testNNConstructor() {
        NaturalNumber n = this.constructorRef("5");
        NaturalNumber s = this.constructorTest(n);
        assertEquals(5, s.toInt());

    }

    // multiplyby10 with a single digit
    @Test
    public final void testMultpilyBy10() {

        NaturalNumber nExpected = this.constructorRef(10);
        NaturalNumber n = this.constructorTest(1);
        n.multiplyBy10(0);
        assertEquals(nExpected, n);

    }

    //multiplyBy10 with a 0
    @Test
    public final void testMultiplyBy10w0() {
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber n = this.constructorTest();
        nExpected.multiplyBy10(0);
        assertEquals(nExpected, n);
    }

//divide by 10 test case with a single digit
    @Test
    public final void testDivideby10() {

        NaturalNumber nExpected = this.constructorRef(50);
        NaturalNumber n = this.constructorTest(5);
        int remainder = 0;
        remainder = nExpected.divideBy10();
        assertEquals(nExpected, n);
        assertEquals(remainder, 0);

    }

// test for divide by 10 with 0
    @Test
    public final void testDivideBy10w0() {
        NaturalNumber nExpected = this.constructorRef();
        NaturalNumber r = this.constructorTest();
        int remainder = 0;
        remainder = nExpected.divideBy10();
        assertEquals(nExpected, r);
        assertEquals(remainder, 0);
    }

    //Test for isZero from default constructor
    @Test
    public final void isZeroTest() {

        NaturalNumber n = this.constructorRef();
        boolean x = n.isZero();
        assertEquals(x, true);

    }

    //Test for isZero from NN with int constructor
    @Test
    public final void isZeroInt() {
        NaturalNumber n = this.constructorRef(0);
        boolean x = n.isZero();
        assertEquals(x, true);

    }

    //Test for isZero from NN with string constructor
    @Test
    public final void isZeroString() {
        NaturalNumber n = this.constructorRef("0");
        boolean x = n.isZero();
        assertEquals(x, true);
    }

    //Test for isZero from NN with NN constructor
    @Test
    public final void isZeroNN() {
        NaturalNumber nExpected = this.constructorRef(0);
        NaturalNumber n = this.constructorTest(nExpected);
        boolean x = n.isZero();
        assertEquals(x, true);

    }
}
