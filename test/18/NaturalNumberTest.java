import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    //Constructor Tests

    @Test
    public void testConstructorEmpty() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(n, nExpected);
    }

    @Test
    public void testConstructorInt() {
        int i = 34;
        NaturalNumber n = this.constructorTest(i);
        NaturalNumber nExpected = this.constructorRef(i);
        assertEquals(n, nExpected);
    }

    @Test
    public void testConstructorString() {
        String s = "34";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber nExpected = this.constructorRef(s);
        assertEquals(n, nExpected);
    }

    @Test
    public void testConstructorNN() {

        String s = "34";
        NaturalNumber test = this.constructorTest(s);
        NaturalNumber test2 = this.constructorRef(s);
        NaturalNumber n = this.constructorTest(test);
        NaturalNumber nExpected = this.constructorRef(test2);
        assertEquals(n, nExpected);
        assertEquals(test, test2);

    }

    //Multiply By 10 tests

    @Test
    public void testMultiplyBy10Empty() {
        NaturalNumber n = this.constructorTest();
        final int i = 4;
        NaturalNumber nExpected = this.constructorRef(i);
        n.multiplyBy10(i);
        assertEquals(nExpected, n);
    }

    @Test
    public void testMultiplyBy10() {
        NaturalNumber n = this.constructorTest(3);
        final int i = 4;
        NaturalNumber nExpected = this.constructorRef(34);
        n.multiplyBy10(i);
        assertEquals(nExpected, n);
    }

    //Divide By 10 tests

    @Test
    public void testDivideBy10() {
        NaturalNumber n = this.constructorTest(345);
        NaturalNumber nExpected1 = this.constructorRef(34);
        final int nExpected2 = 5;
        int n2 = n.divideBy10();
        assertEquals(nExpected1, n);
        assertEquals(nExpected2, n2);

    }

    @Test
    public void testDivideBy10Zero() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected1 = this.constructorRef(0);
        final int nExpected2 = 0;
        int n2 = n.divideBy10();
        assertEquals(nExpected1, n);
        assertEquals(nExpected2, n2);
    }

    //Is Zero test
    @Test
    public void testIsZeroTrue() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(n, nExpected);
        assertTrue(n.isZero());
        assertTrue(nExpected.isZero());
    }

    @Test
    public void testIsZeroFalse() {
        NaturalNumber n = this.constructorTest(6);
        NaturalNumber nExpected = this.constructorRef(6);
        assertEquals(n, nExpected);
        assertTrue(!n.isZero());
        assertTrue(!nExpected.isZero());
    }

}
