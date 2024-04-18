import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author lu zhang
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
    @Test
    public final void testConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExp = this.constructorRef();
        assertEquals(nExp, n);
    }

    @Test
    public final void testIntContructor1() {
        final int a = 10;
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp, n);
    }

    @Test
    public final void testIntConstructor2() {
        final int a = 0;
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp, n);
    }

    @Test
    public final void testStringConstructor1() {
        final String a = "123";
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp, n);
    }

    @Test
    public final void testStringConstructor2() {
        final String a = "0";
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp, n);
    }

    @Test
    public final void testLongStringConstructor() {
        final String a = "12345678910";
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp, n);
    }

    @Test
    public final void testNNConstructor1() {
        final int a = 123;
        NaturalNumber nnInputTest = this.constructorTest(a);
        NaturalNumber nnInputRef = this.constructorRef(a);
        NaturalNumber n = this.constructorTest(nnInputTest);
        NaturalNumber nExp = this.constructorRef(nnInputRef);
        assertEquals(nExp, n);
    }

    @Test
    public final void testNNConstructor2() {
        final int a = 0;
        NaturalNumber nnInputTest = this.constructorTest(a);
        NaturalNumber nnInputRef = this.constructorRef(a);
        NaturalNumber n = this.constructorTest(nnInputTest);
        NaturalNumber nExp = this.constructorRef(nnInputRef);
        assertEquals(nnInputRef, nnInputTest);
        assertEquals(nExp, n);
    }

    @Test
    public final void testMultiplyBy10_1() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExp = this.constructorRef();
        final int a = 0;
        n.multiplyBy10(a);
        nExp.multiplyBy10(a);
        assertEquals(nExp, n);
    }

    @Test
    public final void testMultiplyBy10_2() {
        final int a = 123;
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        final int b = 5;
        n.multiplyBy10(b);
        nExp.multiplyBy10(b);
        assertEquals(nExp, n);
    }

    @Test
    public final void testDivideBy10_1() {
        final int a = 1234;
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        int num = n.divideBy10();
        int numExp = nExp.divideBy10();
        assertEquals(numExp, num);
        assertEquals(nExp, n);
    }

    @Test
    public final void testDivideBy10_2() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExp = this.constructorRef();
        int num = n.divideBy10();
        int numExp = nExp.divideBy10();
        assertEquals(numExp, num);
        assertEquals(nExp, n);
    }

    @Test
    public final void testIsZero1() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExp = this.constructorRef();
        assertEquals(nExp.isZero(), n.isZero());
        assertEquals(nExp, n);
    }

    @Test
    public final void testIsZero2() {
        final int a = 1234;
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp.isZero(), n.isZero());
        assertEquals(nExp, n);
    }

    @Test
    public final void testIsZero3() {
        final int a = 1234;
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExp = this.constructorRef(a);
        assertEquals(nExp.isZero(), !n.isZero());
    }

    @Test
    public final void testIsZero4() {
        final int a = 1234;
        NaturalNumber n = this.constructorTest(a);
        NaturalNumber nExp = this.constructorRef();
        assertEquals(nExp.isZero(), !n.isZero());
    }
}
