import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Zeyad Mansour
 * @author Samuel Ellerbrock
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Removes the magic number warning.
     */

    public static final int TWELVE = 12;

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
     * Test for constructor with no argument.
     */

    @Test
    public final void constructorEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(n, nExpected);
    }

    /**
     * Test for constructor with int argument.
     */
    @Test
    public final void constructorInt() {
        NaturalNumber n = this.constructorTest(2);
        NaturalNumber nExpected = this.constructorRef(2);

        assertEquals(n, nExpected);
    }

    /**
     * Test for constructor with string argument.
     */

    @Test
    public final void constructorString() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef("5");

        assertEquals(n, nExpected);
    }

    /**
     * Test for constructor with natural number argument.
     */

    @Test
    public final void constructorNaturalNumber() {
        NaturalNumber num = this.constructorRef(2);
        NaturalNumber n = this.constructorTest(num);
        NaturalNumber nExpected = this.constructorRef(num);

        assertEquals(n, nExpected);
    }

    /**
     * Test for multiplyBy10 method with an empty natural number.
     */

    @Test
    public final void multiplyBy10t1() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        n.multiplyBy10(2);
        nExpected.multiplyBy10(2);

        assertEquals(n, nExpected);
    }

    /**
     * Test for multiplyBy10 method with a single digit natural number.
     */

    @Test
    public final void multiplyBy10t2() {
        NaturalNumber n = this.constructorTest(2);
        NaturalNumber nExpected = this.constructorRef(2);
        n.multiplyBy10(2);
        nExpected.multiplyBy10(2);

        assertEquals(n, nExpected);
    }

    /**
     * Test for multiplyBy10 method with a mulitple digit natural number.
     */

    @Test
    public final void multiplyBy10t3() {
        NaturalNumber n = this.constructorTest(TWELVE);
        NaturalNumber nExpected = this.constructorRef(TWELVE);
        n.multiplyBy10(2);
        nExpected.multiplyBy10(2);

        assertEquals(n, nExpected);
    }

    /**
     * Test for divideBy10 method with an empty natural number.
     */

    @Test
    public final void divideBy10t1() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        int i = n.divideBy10();
        int iExpected = nExpected.divideBy10();

        assertEquals(i, iExpected);
    }

    /**
     * Test for divideBy10 method with a single digit natural number.
     */

    @Test
    public final void divideBy10t2() {
        NaturalNumber n = this.constructorTest(2);
        NaturalNumber nExpected = this.constructorRef(2);
        int i = n.divideBy10();
        int iExpected = nExpected.divideBy10();

        assertEquals(i, iExpected);
    }

    /**
     * Test for divideBy10 method with multiple digit natural number.
     */

    @Test
    public final void divideBy10t3() {
        NaturalNumber n = this.constructorTest(TWELVE);
        NaturalNumber nExpected = this.constructorRef(TWELVE);
        int i = n.divideBy10();
        int iExpected = nExpected.divideBy10();

        assertEquals(i, iExpected);
    }

    /**
     * Test for isZero method when it is empty.
     */

    @Test
    public final void isZero1() {
        NaturalNumber num = this.constructorTest();
        NaturalNumber numExpected = this.constructorRef();
        boolean n = num.isZero();
        boolean nExpected = numExpected.isZero();

        assertEquals(n, nExpected);
    }

    /**
     * Test for isZero method when it is zero.
     */

    @Test
    public final void isZero2() {
        NaturalNumber num = this.constructorTest(0);
        NaturalNumber numExpected = this.constructorRef(0);
        boolean n = num.isZero();
        boolean nExpected = numExpected.isZero();

        assertEquals(n, nExpected);
    }

    /**
     * Test for isZero method when it is not zero.
     */

    @Test
    public final void isZero3() {
        NaturalNumber num = this.constructorTest(TWELVE);
        NaturalNumber numExpected = this.constructorRef(TWELVE);
        boolean n = num.isZero();
        boolean nExpected = numExpected.isZero();

        assertEquals(n, nExpected);
    }

}
