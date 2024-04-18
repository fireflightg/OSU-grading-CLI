import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Sophia Rakowsky, Weixuan Qu, Hana Winchester
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
    public void testNoArgConstructor() {
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef();
        assertEquals(nnExpected, nn);
    }

    //Int constructor Tests
    @Test
    public void testIntConstructor1() {
        NaturalNumber nn = this.constructorTest(1);
        NaturalNumber nnExpected = this.constructorRef(1);
        assertEquals(nnExpected, nn);
    }

    @Test
    public void testIntConstructor567890() {
        NaturalNumber nn = this.constructorTest(567890);
        NaturalNumber nnExpected = this.constructorRef(567890);
        assertEquals(nnExpected, nn);
    }


    //String constructor tests
    @Test
    public void testStringConstructor1() {
        NaturalNumber nn = this.constructorTest("1");
        NaturalNumber nnExpected = this.constructorRef("1");
        assertEquals(nnExpected, nn);
    }

    @Test
    public void testStringConstructor567890() {
        NaturalNumber nn = this.constructorTest("567890");
        NaturalNumber nnExpected = this.constructorRef("567890");
        assertEquals(nnExpected, nn);
    }

    @Test
    public void testStringConstructor0() {
        NaturalNumber nn = this.constructorTest("0");
        NaturalNumber nnExpected = this.constructorRef("0");
        assertEquals(nnExpected, nn);
    }

    //NN constructor tests
    @Test
    public void testNNConstructor1() {
        NaturalNumber nnTemp = this.constructorTest("1");
        NaturalNumber nn = this.constructorTest(nnTemp);
        NaturalNumber nnExpected = this.constructorRef(nnTemp);
        assertEquals(nnExpected, nn);
    }

    @Test
    public void testNNConstructor2345() {
        NaturalNumber nnTemp = this.constructorTest("2345");
        NaturalNumber nn = this.constructorTest(nnTemp);
        NaturalNumber nnExpected = this.constructorRef(nnTemp);
        assertEquals(nnExpected, nn);
    }


    //multiplyBy10 Tests
    @Test
    //multiplyBy10(1) Test with empty start
    public void test0MultiplyBy10With1() {
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef(1);
        nn.multiplyBy10(1);
        assertEquals("Should be 1", nnExpected, nn);
    }

    @Test
    //multiplyBy10(0) Test with empty start
    public void test0MultiplyBy10With0() {
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef();
        nn.multiplyBy10(0);
        assertEquals("Should be 0", nnExpected, nn);
    }

    @Test
    //multiplyBy10 Test with 0
    public void test2MultiplyBy10with0() {
        NaturalNumber nn = this.constructorTest(2);
        NaturalNumber nnExpected = this.constructorRef(20);
        nn.multiplyBy10(0);
        assertEquals("Should be 20", nnExpected, nn);
    }

    @Test
    //multiplyBy10 with 1
    public void test2MultiplyBy10with1() {
        NaturalNumber nn = this.constructorTest(2);
        NaturalNumber nnExpected = this.constructorRef(21);
        nn.multiplyBy10(1);
        assertEquals("Should be 21", nnExpected, nn);
    }

    @Test
    //multiply by positive digit
    public void test3MultiplyBy10with2() {
        NaturalNumber nn = this.constructorTest(3);
        NaturalNumber nnExpected = this.constructorRef(32);
        nn.multiplyBy10(2);
        assertEquals("Result should be 32.", nnExpected, nn);
    }

    @Test
    //multiply by the max digit, 9
    public void test3MultiplyBy10with9() {
        NaturalNumber nn = this.constructorTest(3);
        NaturalNumber nnExpected = this.constructorRef(39);
        nn.multiplyBy10(9);
        assertEquals("Should be 39", nnExpected, nn);
    }


    // dividBy10 Tests
    @Test
    public void dividedby10test0() {
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef();
        int last = nn.divideBy10();
        assertEquals(0, last);
        assertEquals(nnExpected, nn);
    }

    @Test
    public void dividedby10test8() {
        NaturalNumber nn = this.constructorTest("8");
        NaturalNumber nnExpected = this.constructorRef();
        int last = nn.divideBy10();
        assertEquals(8, last);
        assertEquals(nnExpected, nn);
    }

    @Test
    public void dividedby10test12() {
        NaturalNumber nn = this.constructorTest("12");
        NaturalNumber nnExpected = this.constructorRef("1");
        int last = nn.divideBy10();
        assertEquals(2, last);
        assertEquals(nnExpected, nn);
    }

    @Test
    public void dividedby10test123456() {
        NaturalNumber nn = this.constructorTest("123456");
        NaturalNumber nnExpected = this.constructorRef("12345");
        int last = nn.divideBy10();
        assertEquals(6, last);
        assertEquals(nnExpected, nn);
    }

    @Test
    public void dividedby10test98765489789() {
        NaturalNumber nn = this.constructorTest("98765489789");
        NaturalNumber nnExpected = this.constructorRef("9876548978");
        int last = nn.divideBy10();
        assertEquals(9, last);
        assertEquals(nnExpected, nn);
    }


    // isZero Tests
    @Test
    public void testIsZero0() {
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef();
        assertEquals(nnExpected, nn);
        assertTrue(nn.isZero());
    }

    @Test
    public void testIsZero234() {
        NaturalNumber nn = this.constructorTest(234);
        NaturalNumber nnExpected = this.constructorRef(234);
        assertEquals(nnExpected, nn);
        assertFalse(nn.isZero());
    }


}
