import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

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

    /**
     * Test case for empty constructor.
     */
    @Test
    public void emptyConstructorTest() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest();
        NaturalNumber expectedNN = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for integer constructor.
     */
    @Test
    public void intConstructorTest0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest(0);
        NaturalNumber expectedNN = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for integer constructor using different integer value.
     */
    @Test
    public void intConstructorTest1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest(1);
        NaturalNumber expectedNN = this.constructorRef(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for integer constructor using max integer value.
     */
    @Test
    public void intConstructorTestMaxValue() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber expectedNN = this.constructorRef(Integer.MAX_VALUE);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for string constructor.
     */
    @Test
    public void stringConstructorTest0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest("0");
        NaturalNumber expectedNN = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for string constructor with leading zeros.
     */
    @Test
    public void stringConstructorTest1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest("1");
        NaturalNumber expectedNN = this.constructorRef("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for string constructor with leading zeros.
     */
    @Test
    public void stringConstructorTest01() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest("100");
        NaturalNumber expectedNN = this.constructorRef("100");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for NaturalNumber constructor.
     */
    @Test
    public void nNConstructorTest0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest(new NaturalNumber1L(0));
        NaturalNumber expectedNN = this.constructorRef(new NaturalNumber1L(0));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for NaturalNumber constructor.
     */
    @Test
    public void nNConstructorTest1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest(new NaturalNumber1L(1));
        NaturalNumber expectedNN = this.constructorRef(new NaturalNumber1L(1));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for NaturalNumber constructor.
     */
    @Test
    public void nNConstructorTest1000() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this.constructorTest(new NaturalNumber1L(1000));
        NaturalNumber expectedNN = this
                .constructorRef(new NaturalNumber1L(1000));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for NaturalNumber constructor.
     */
    @Test
    public void nNConstructorTest01() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = this
                .constructorTest(new NaturalNumber1L("7234682739107462"));
        NaturalNumber expectedNN = this
                .constructorRef(new NaturalNumber1L("7234682739107462"));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }

    /**
     * Test case for divideBy10.
     */
    @Test
    public void nNDivideBy101() {
        /*
         * Set up variables and call method under test
         */

        NaturalNumber nN = this.constructorTest(new NaturalNumber1L("105"));
        NaturalNumber expectedNN = this
                .constructorRef(new NaturalNumber1L("10"));
        int test = nN.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
        assertEquals(5, test);
    }

    /**
     * Test case for divideBy10.
     */
    @Test
    public void nNDivideBy102() {
        /*
         * Set up variables and call method under test
         */

        NaturalNumber nN = this.constructorTest(new NaturalNumber1L("1"));
        NaturalNumber expectedNN = this.constructorRef(new NaturalNumber1L());
        int test = nN.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
        assertEquals(1, test);
    }

    /**
     * Test case for divideBy10.
     */
    @Test
    public void nNDivideBy103() {
        /*
         * Set up variables and call method under test
         */

        NaturalNumber nN = this
                .constructorTest(new NaturalNumber1L("1052737469183"));
        NaturalNumber expectedNN = this
                .constructorRef(new NaturalNumber1L("105273746918"));
        int test = nN.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
        assertEquals(3, test);
    }

    /**
     * Test case for isZero.
     */
    @Test
    public void nNIsZero0() {
        /*
         * Set up variables and call method under test
         */

        NaturalNumber nN = this.constructorTest(new NaturalNumber1L());
        NaturalNumber expectedNN = this.constructorRef(new NaturalNumber1L());
        boolean test = nN.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
        assertEquals(true, test);
    }

    /**
     * Test case for isZero.
     */
    @Test
    public void nNIsZero1() {
        /*
         * Set up variables and call method under test
         */

        NaturalNumber nN = this.constructorTest(new NaturalNumber1L(1));
        NaturalNumber expectedNN = this.constructorRef(new NaturalNumber1L(1));
        boolean test = nN.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
        assertEquals(false, test);
    }
    
    /**
     * Test case for NaturalNumber multiplyBy10.
     */
    @Test
    public void nNMultiplyBy100() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = constructorTest(new NaturalNumber1L());
        NaturalNumber expectedNN = constructorRef(new NaturalNumber1L());
        nN.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }
    
    /**
     * Test case for NaturalNumber multiplyBy10.
     */
    @Test
    public void nNMultiplyBy101() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = constructorTest(new NaturalNumber1L());
        NaturalNumber expectedNN = constructorRef(new NaturalNumber1L(1));
        nN.multiplyBy10(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }
    
    /**
     * Test case for NaturalNumber multiplyBy10.
     */
    @Test
    public void nNMultiplyBy102() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = constructorTest(new NaturalNumber1L(1));
        NaturalNumber expectedNN = constructorRef(new NaturalNumber1L(11));
        nN.multiplyBy10(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }
    
    /**
     * Test case for NaturalNumber multiplyBy10.
     */
    @Test
    public void nNMultiplyBy103() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = constructorTest(new NaturalNumber1L(1));
        NaturalNumber expectedNN = constructorRef(new NaturalNumber1L(10));
        nN.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }
    
    /**
     * Test case for NaturalNumber multiplyBy10.
     */
    @Test
    public void nNMultiplyBy104() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = constructorTest(new NaturalNumber1L(12));
        NaturalNumber expectedNN = constructorRef(new NaturalNumber1L(123));
        nN.multiplyBy10(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }
    
    /**
     * Test case for NaturalNumber multiplyBy10.
     */
    @Test
    public void nNMultiplyBy10Max() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nN = constructorTest(new NaturalNumber1L("2147483647"));
        NaturalNumber expectedNN = constructorRef(new NaturalNumber1L("21474836473"));
        nN.multiplyBy10(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedNN, nN);
    }
}
