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

    @Test
    public void constructorTestNoArg() {
        final int zero = 0;
        NaturalNumber n = this.constructorTest();
        assertEquals(zero, n.toInt());
    }

    @Test
    public void constructorTestInt1() {
        final int two = 2;
        NaturalNumber n = this.constructorTest(two);
        assertEquals(two, n.toInt());
    }

    @Test
    public void constructorTestInt2() {
        final int zero = 0;
        NaturalNumber n = this.constructorTest(zero);
        assertEquals(zero, n.toInt());
    }

    @Test
    public void constructorTestString1() {
        final String three = "3";
        final int intThree = 3;
        NaturalNumber n = this.constructorTest(three);
        assertEquals(intThree, n.toInt());
    }

    @Test
    public void constructorTestString2() {
        final String zero = "0";
        final int intZero = 0;
        NaturalNumber n = this.constructorTest(zero);
        assertEquals(intZero, n.toInt());
    }

    @Test
    public void constructorTestNN() {
        final int four = 4;
        NaturalNumber nnFour = new NaturalNumber3(four);
        NaturalNumber n = this.constructorTest(nnFour);
        assertEquals(four, n.toInt());
    }

    @Test
    public void constructorTestNN2() {
        final int zero = 0;
        NaturalNumber nnZero = new NaturalNumber3(zero);
        NaturalNumber n = this.constructorTest(nnZero);
        assertEquals(zero, n.toInt());
    }

    @Test
    public void multiplyBy10Test() {
        final int two = 2;
        NaturalNumber n = new NaturalNumber3();
        n.multiplyBy10(two);
        assertEquals(two, n.toInt());
    }

    @Test
    public void multiplyBy10Test1() {
        final String two = "2";
        final int twoint = 2;
        final int twentytwo = 22;
        NaturalNumber n = new NaturalNumber3(two);
        n.multiplyBy10(twoint);
        assertEquals(twentytwo, n.toInt());
    }

    @Test
    public void divideBy10Test1() {
        final int num = 346;
        final int six = 6;
        final int thirtyfour = 34;
        NaturalNumber n = new NaturalNumber3(num);
        int remainder = n.divideBy10();
        assertEquals(six, remainder);
        assertEquals(n.toInt(), thirtyfour);
    }

    @Test
    public void divideBy10Test2() {
        final int four = 4;
        final int zero = 0;
        NaturalNumber n = new NaturalNumber3(four);
        int remainder = n.divideBy10();
        assertEquals(four, remainder);
        assertEquals(n.toInt(), zero);
    }

    @Test
    public void divideBy10Test3() {
        final int zero = 0;
        NaturalNumber n = new NaturalNumber3(zero);
        int remainder = n.divideBy10();
        assertEquals(zero, remainder);
        assertEquals(n.toInt(), zero);
    }

    @Test
    public void isZero1() {
        NaturalNumber n = new NaturalNumber3();
        assertTrue(n.isZero());
    }

    @Test
    public void isZero2() {
        final int four = 4;
        NaturalNumber n = new NaturalNumber3(four);
        assertTrue(!n.isZero());
    }

}
