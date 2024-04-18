import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Kritarsh Negi, Ujwal Chilla
 *
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = "";
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {
        this.createNewRep();

    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";
        this.createNewRep();
        this.rep = this.rep.concat("" + i);

        if (i == 0) {
            this.createNewRep();
        } else {
            this.rep = i + "";
        }

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";
        this.createNewRep();
        this.rep = this.rep.concat(s);

        if (s.equals("0")) {
            this.createNewRep();
        } else {
            this.rep = s;
        }

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        this.createNewRep();
        this.rep = this.rep.concat(n.toString());

        if (n.isZero()) {
            this.createNewRep();
        } else {
            this.rep = n.toString();
        }

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */
    /**
     * Multiplies the current NaturalNumber3 instance by 10 and adds k.
     *
     * This method converts the string representation of the number into an
     * integer array, then performs multiplication by 10 on the integer
     * representation and adds k.
     *
     * @param k
     *            The number to be added after multiplication. It should be
     *            between 0 and 9 (inclusive).
     */
    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        if (!this.rep.isEmpty()) {
            this.rep = this.rep + k + "";
        } else {
            this.rep = k + "";
        }

    }

    /**
     * Divides the current NaturalNumber3 instance by 10.
     *
     * This method converts the string representation of the number into an
     * integer array, then performs division by 10 on the integer
     * representation.
     *
     * @return The remainder of the division operation.
     */
    @Override
    public final int divideBy10() {

        String temp = this.rep;
        char[] charArray = temp.toCharArray();
        int[] intArray = new int[charArray.length];
        for (int k = 0; k < intArray.length; k++) {
            intArray[k] = Character.getNumericValue(charArray[k]);
        }
        int one = 0;
        int two = 0;
        for (int i = 0; i < intArray.length; i++) {
            two = intArray[i] + one * 10;
            // Multiply each digit by 10 and add the carry from the previous digit
            one = two;
        }
        int b = two % 10; // Get the remainder of the division by 10
        int c = two / 10; // Get the quotient of the division by 10

        this.rep = "" + c; // Update the string representation

        return b; // Return the remainder
    }

    /**
     * Checks if the current NaturalNumber3 instance represents zero.
     *
     * This method converts the string representation of the number into an
     * integer array, then sums up the digits to check if the total is zero.
     *
     * @return true if the NaturalNumber3 instance represents zero, false
     *         otherwise
     */
    @Override
    public final boolean isZero() {

        // This line added just to make the component compilable.
        String temp = this.rep;
        char[] charArray = temp.toCharArray();
        int[] intArray = new int[charArray.length];
        for (int k = 0; k < intArray.length; k++) {
            intArray[k] = Character.getNumericValue(charArray[k]);
        }
        int one = 0;
        int two = 0;
        for (int i = 0; i < intArray.length; i++) {
            two = intArray[i] + one * 10;
            // Multiply each digit by 10 and add the carry from the previous digit
            one = two;
        }
        return two == 0; // Check if the total is zero
    }

}
