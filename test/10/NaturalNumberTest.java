import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Sean Asquith
 * @author Fabrizzio Ramon
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

    /*
     * Test cases for the no argument constructor
     */

    @Test
    public void noArgumentConstructorTest() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();

        assertEquals(ref, test);
    }

    /*
     * Test case for string constructor
     */

    @Test
    public void stringConstructorTest() {
        NaturalNumber test = this.constructorTest("1234");
        NaturalNumber ref = this.constructorRef("1234");

        assertEquals(ref, test);
    }

    /*
     * Test case for string constructor - with string values of 0
     */

    @Test
    public void stringConstructorTestZero() {
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber ref = this.constructorRef("0");

        assertEquals(ref, test);

    }

    /*
     * Test case for int constructor
     */

    @Test
    public void intConstructorTest() {
        NaturalNumber test = this.constructorTest(123);
        NaturalNumber ref = this.constructorRef(123);

        assertEquals(ref, test);
    }

    /*
     * Test case for naturalNumber constructor
     */

    @Test
    public void naturalNumberConstructorTest() {
        NaturalNumber nn = this.constructorRef(123);

        NaturalNumber test = this.constructorTest(nn);
        NaturalNumber ref = this.constructorRef(nn);

        assertEquals(ref, test);
    }

    /*
     * Test case for multiplyBy10 - no argument constructor
     */

    @Test
    public void mulipltyBy10Test() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();

        test.multiplyBy10(9);
        ref.multiplyBy10(9);

        assertEquals(ref, test);
    }

    /*
     * Test case for multiplyBy10 - no argument constructor and add 0
     */

    @Test
    public void mulipltyBy10Test2() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber ref = this.constructorRef();

        test.multiplyBy10(0);
        ref.multiplyBy10(0);

        assertEquals(ref, test);
    }

    /*
     * Test case for multiplyBy10 - with int vaules
     */

    @Test
    public void multiplyBy10Test3() {
        NaturalNumber test = this.constructorTest(10);
        NaturalNumber expected = this.constructorRef(100);

        test.multiplyBy10(0);

        assertEquals(expected, test);
    }

    /*
     * Test case for divideBy10 (routine)
     */

    @Test
    public void divideBy10Test1() {
        NaturalNumber test = this.constructorTest(9);
        NaturalNumber expected = this.constructorRef();

        int rem = test.divideBy10();

        assertEquals(expected, test);
        assertEquals(9, rem);

    }

    /*
     * Test case for divideBy10 (boundary)
     */

    @Test
    public void divideBy10Test2() {
        NaturalNumber test = this.constructorTest(993637238);
        NaturalNumber expected = this.constructorRef(99363723);

        int rem = test.divideBy10();

        assertEquals(expected, test);
        assertEquals(rem, 8);

    }

    /*
     * Test case for isZero (boundary)
     */

    @Test
    public void divideBy10isZeroTest() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber expected = this.constructorRef();

        boolean check = test.isZero();
        boolean check2 = expected.isZero();

        assertEquals(test, expected);
        assertTrue(check2);
        assertTrue(check);

    }
    /*
     * Test case for isZero (routine)
     */

    @Test
    public void isZeroTest1() {
        NaturalNumber test = this.constructorTest();

        boolean check = test.isZero();

        assertTrue(check);

    }

    /*
     * Test case for isZero
     */
    @Test
    public void isZeroTest2() {
        NaturalNumber test = this.constructorTest(343);

        boolean check = test.isZero();

        assertEquals(check, false);
    }
}
