import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods to be used in grading the NaturalNumber3 projects. Uses
 * NaturalNumber2 as the reference implementation.
 *
 * @author Paolo Bucci (.2)
 * @modified Derek Schneider (.641)
 * @revised 10/8/2013
 */
public final class NaturalNumber3Grade {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor and returns the
     * result.
     *
     * @return the new number
     * @ensures <pre>
     * {@code constructor = 0}
     * </pre>
     */
    private NaturalNumber constructorTest() {
        return new NaturalNumber3();
    }

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor and returns the
     * result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires <pre>
     * {@code i >= 0}
     * </pre>
     * @ensures <pre>
     * {@code constructor = i}
     * </pre>
     */
    private NaturalNumber constructorTest(int i) {
        return new NaturalNumber3(i);
    }

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor and returns the
     * result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires <pre>
     * {@code there exists n: NATURAL (s = TO_STRING(n))}
     * </pre>
     * @ensures <pre>
     * {@code s = TO_STRING(constructor)}
     * </pre>
     */
    private NaturalNumber constructorTest(String s) {
        return new NaturalNumber3(s);
    }

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor and returns the
     * result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures <pre>
     * {@code constructor = n}
     * </pre>
     */
    private NaturalNumber constructorTest(NaturalNumber n) {
        return new NaturalNumber3(n);
    }

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor and returns the
     * result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires <pre>
     * {@code there exists n: NATURAL (s = TO_STRING(n))}
     * </pre>
     * @ensures <pre>
     * {@code s = TO_STRING(constructor)}
     * </pre>
     */
    private NaturalNumber constructorRef(String s) {
        return new NaturalNumber2(s);
    }

    @Test
    public final void testDefaultConstructor() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n2 = this.constructorRef("0");
        assertEquals(n2, n1);
    }

    @Test
    public final void testIntConstructorZero() {
        NaturalNumber n1 = this.constructorTest(0);
        NaturalNumber n2 = this.constructorRef("0");
        assertEquals(n2, n1);
    }

    @Test
    public final void testIntConstructorSingleDigit() {
        NaturalNumber n1 = this.constructorTest(6);
        NaturalNumber n2 = this.constructorRef("6");
        assertEquals(n2, n1);
    }

    @Test
    public final void testIntConstructorMultipleDigits() {
        NaturalNumber n1 = this.constructorTest(6789043);
        NaturalNumber n2 = this.constructorRef("6789043");
        assertEquals(n2, n1);
    }

    @Test
    public final void testIntConstructorMaxValue() {
        NaturalNumber n1 = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber n2 = this
                .constructorRef(Integer.toString(Integer.MAX_VALUE));
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorZero() {
        NaturalNumber n1 = this.constructorTest("0");
        NaturalNumber n2 = this.constructorRef("0");
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorSingleDigit() {
        NaturalNumber n1 = this.constructorTest("6");
        NaturalNumber n2 = this.constructorRef("6");
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorMultipleDigits() {
        NaturalNumber n1 = this.constructorTest("6789043");
        NaturalNumber n2 = this.constructorRef("6789043");
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorMaxIntValue() {
        NaturalNumber n1 = this
                .constructorTest(Integer.toString(Integer.MAX_VALUE));
        NaturalNumber n2 = this
                .constructorRef(Integer.toString(Integer.MAX_VALUE));
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorMaxIntValuePlusOne() {
        NaturalNumber n1 = this
                .constructorTest(Long.toString((long) Integer.MAX_VALUE + 1));
        NaturalNumber n2 = this
                .constructorRef(Long.toString((long) Integer.MAX_VALUE + 1));
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorMaxLongValue() {
        NaturalNumber n1 = this.constructorTest(Long.toString(Long.MAX_VALUE));
        NaturalNumber n2 = this.constructorRef(Long.toString(Long.MAX_VALUE));
        assertEquals(n2, n1);
    }

    @Test
    public final void testStringConstructorBiggerThanMaxLongValue() {
        NaturalNumber n1 = this.constructorTest("98765432101234567898");
        NaturalNumber n2 = this.constructorRef("98765432101234567898");
        assertEquals(n2, n1);
    }

    @Test
    public final void testCopyConstructorZero() {
        NaturalNumber tmp = this.constructorRef("0");
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this.constructorRef("0");
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testCopyConstructorSingleDigit() {
        NaturalNumber tmp = this.constructorRef("6");
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this.constructorRef("6");
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testCopyConstructorMultipleDigits() {
        NaturalNumber tmp = this.constructorRef("6789043");
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this.constructorRef("6789043");
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testCopyConstructorMaxIntValue() {
        NaturalNumber tmp = this
                .constructorRef(Integer.toString(Integer.MAX_VALUE));
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this
                .constructorRef(Integer.toString(Integer.MAX_VALUE));
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testCopyConstructorMaxIntValuePlusOne() {
        NaturalNumber tmp = this
                .constructorRef(Long.toString((long) Integer.MAX_VALUE + 1));
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this
                .constructorRef(Long.toString((long) Integer.MAX_VALUE + 1));
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testCopyConstructorMaxLongValue() {
        NaturalNumber tmp = this.constructorRef(Long.toString(Long.MAX_VALUE));
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this.constructorRef(Long.toString(Long.MAX_VALUE));
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testCopyConstructorBiggerThanMaxLongValue() {
        NaturalNumber tmp = this.constructorRef("98765432101234567898");
        NaturalNumber n1 = this.constructorTest(tmp);
        NaturalNumber n2 = this.constructorRef("98765432101234567898");
        assertEquals(n2, n1);
        assertEquals(n2, tmp);
    }

    @Test
    public final void testMultiplyBy10ZeroZero() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n2 = this.constructorRef("0");
        n1.multiplyBy10(0);
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10ZeroNonZero() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n2 = this.constructorRef("7");
        n1.multiplyBy10(7);
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10SingleDigitZero() {
        NaturalNumber n1 = this.constructorTest(3);
        NaturalNumber n2 = this.constructorRef("30");
        n1.multiplyBy10(0);
        //        assertEquals(false, n1.isZero());
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10SingleDigitNonZero() {
        NaturalNumber n1 = this.constructorTest(3);
        NaturalNumber n2 = this.constructorRef("37");
        n1.multiplyBy10(7);
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10MultipleDigitsZero() {
        NaturalNumber n1 = this.constructorTest(345);
        NaturalNumber n2 = this.constructorRef("3450");
        n1.multiplyBy10(0);
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10MultipleDigitsNonZero() {
        NaturalNumber n1 = this.constructorTest(345);
        NaturalNumber n2 = this.constructorRef("3457");
        n1.multiplyBy10(7);
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10BigZero() {
        NaturalNumber n1 = this
                .constructorTest("12345678909876543210123456789");
        NaturalNumber n2 = this
                .constructorRef("123456789098765432101234567890");
        n1.multiplyBy10(0);
        assertEquals(n2, n1);
    }

    @Test
    public final void testMultiplyBy10BigNonZero() {
        NaturalNumber n1 = this
                .constructorTest("12345678909876543210123456789");
        NaturalNumber n2 = this
                .constructorRef("123456789098765432101234567897");
        n1.multiplyBy10(7);
        assertEquals(n2, n1);
    }

    @Test
    public final void testDivideBy10ZeroZero() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n2 = this.constructorRef("0");
        int rem = n1.divideBy10();
        assertEquals(n2, n1);
        assertEquals(0, rem);
    }

    @Test
    public final void testDivideBy10SingleDigitZero() {
        NaturalNumber n1 = this.constructorTest(9);
        NaturalNumber n2 = this.constructorRef("0");
        int rem = n1.divideBy10();
        assertEquals(n2, n1);
        assertEquals(9, rem);
    }

    @Test
    public final void testDivideBy10MultipleDigitsZero() {
        NaturalNumber n1 = this.constructorTest(130);
        NaturalNumber n2 = this.constructorRef("13");
        int rem = n1.divideBy10();
        assertEquals(n2, n1);
        assertEquals(0, rem);
    }

    @Test
    public final void testDivideBy10MultipleDigitsNonZero() {
        NaturalNumber n1 = this.constructorTest(1308);
        NaturalNumber n2 = this.constructorRef("130");
        int rem = n1.divideBy10();
        assertEquals(n2, n1);
        assertEquals(8, rem);
    }

    @Test
    public final void testDivideBy10BigZero() {
        NaturalNumber n1 = this.constructorTest("1357908642975312468000");
        NaturalNumber n2 = this.constructorRef("135790864297531246800");
        int rem = n1.divideBy10();
        assertEquals(n2, n1);
        assertEquals(0, rem);
    }

    @Test
    public final void testDivideBy10BigNonZero() {
        NaturalNumber n1 = this.constructorTest("1357908642975312468009");
        NaturalNumber n2 = this.constructorRef("135790864297531246800");
        int rem = n1.divideBy10();
        assertEquals(n2, n1);
        assertEquals(9, rem);
    }

    @Test
    public final void testIsZeroTrue() {
        NaturalNumber n1 = this.constructorTest();
        NaturalNumber n2 = this.constructorRef("0");
        boolean result = n1.isZero();
        assertEquals(n2, n1);
        assertEquals(true, result);
    }

    @Test
    public final void testIsZeroSingleDigitFalse() {
        NaturalNumber n1 = this.constructorTest(3);
        NaturalNumber n2 = this.constructorRef("3");
        boolean result = n1.isZero();
        assertEquals(n2, n1);
        assertEquals(false, result);
    }

    @Test
    public final void testIsZeroMultipleDigitsFalse() {
        NaturalNumber n1 = this.constructorTest(98765);
        NaturalNumber n2 = this.constructorRef("98765");
        boolean result = n1.isZero();
        assertEquals(n2, n1);
        assertEquals(false, result);
    }

    @Test
    public final void testIsZeroMultipleOf10False() {
        NaturalNumber n1 = this.constructorTest(98760);
        NaturalNumber n2 = this.constructorRef("98760");
        boolean result = n1.isZero();
        assertEquals(n2, n1);
        assertEquals(false, result);
    }

    @Test
    public final void testIsZeroBigFalse() {
        NaturalNumber n1 = this
                .constructorTest("999999999999999999999999999999");
        NaturalNumber n2 = this
                .constructorRef("999999999999999999999999999999");
        boolean result = n1.isZero();
        assertEquals(n2, n1);
        assertEquals(false, result);
    }

}
