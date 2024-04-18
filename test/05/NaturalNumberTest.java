import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Jack Olson, Isaac Gardner
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
     */
    @Test
    public void testNoArgConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber m = this.constructorRef();
        assertEquals(n, m);
    }

    /**
     *
     */
    @Test
    public void testConstructorStr() {
        String s = "7";
        NaturalNumber n = this.constructorTest(s);
        NaturalNumber m = this.constructorRef(s);
        assertEquals(n, m);
    }

    /**
     *
     */
    @Test
    public void testConstructorInt() {
        int x = 2;
        NaturalNumber n = this.constructorTest(x);
        NaturalNumber m = this.constructorRef(x);
        assertEquals(n, m);
    }

    /**
     *
     */
    @Test
    public void testConstructorNatNum() {
        NaturalNumber y = this.constructorRef();
        y.multiplyBy10(2);
        NaturalNumber n = this.constructorTest(y);
        NaturalNumber m = this.constructorRef(y);
        assertEquals(n, m);
    }

    /**
     *
     */
    @Test
    public void testNatNumNullMB10With2() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber m = this.constructorRef(2);
        n.multiplyBy10(2);
        assertEquals(m, n);
    }

    /**
    *
    */
    @Test
    public void testNatNum9MB10With2() {
        NaturalNumber n = this.constructorTest(9);
        NaturalNumber m = this.constructorRef(92);
        n.multiplyBy10(2);
        assertEquals(m, n);
    }

    /**
    *
    */
    @Test
    public void testDivBy10WithEmpty() {
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber m = this.constructorRef(0);
        int onesPlace = n.divideBy10();
        int onesPlaceExp = 0;
        assertEquals(m, n);
        assertEquals(onesPlaceExp, onesPlace);
    }

    /**
    *
    */
    @Test
    public void testDivBy10With92() {
        NaturalNumber n = this.constructorTest(92);
        NaturalNumber m = this.constructorRef(9);
        int onesPlace = n.divideBy10();
        int onesPlaceExp = 2;
        assertEquals(m, n);
        assertEquals(onesPlaceExp, onesPlace);
    }

    /**
    *
    */
    @Test
    public void testIsZeroWithZero() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber m = this.constructorRef();
        boolean result = n.isZero();
        boolean resultExp = true;
        assertEquals(m, n);
        assertEquals(resultExp, result);
    }

    /**
    *
    */
    @Test
    public void testIsZeroWithNonZero() {
        NaturalNumber n = this.constructorTest(142);
        NaturalNumber m = this.constructorRef(142);
        boolean result = n.isZero();
        boolean resultExp = false;
        assertEquals(m, n);
        assertEquals(resultExp, result);
    }

}
