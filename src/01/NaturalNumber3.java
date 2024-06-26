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
 * @author Zhengyang Peng, Zeyu Huang
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
        // At first, initialize this.rep
        this.createNewRep();
        // if n is not 0, assign this rep to "n"
        if (i != 0) {
            this.rep = Integer.toString(i);

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
        // At first, initialize this.rep
        this.createNewRep();
        // if n is not 0, assign this rep to "n"
        if (!s.equals("0")) {
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

        // At first, initialize this.rep
        this.createNewRep();
        // if n is not 0, assign this rep to "n"
        if (!n.isZero()) {
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

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";
        // switch k to string
        String number = Integer.toString(k);
        if (!this.rep.equals("0")) {
            this.rep += number;
        } else {
            this.rep = number;
        }

    }

    @Override
    public final int divideBy10() {
        // Initialize a string to hold the remainder
        String remainder;
        // Check if the string representation is empty
        if (this.rep.equals("")) {
            // If the string is empty, set the remainder to "0"
            remainder = "0";
        } else {
            // If the string is not empty, extract the last character as the remainder
            remainder = this.rep.substring(this.rep.length() - 1);
            // Remove the last character from the string representation
            String subString = this.rep.substring(0, this.rep.length() - 1);
            this.rep = subString; // Update the string representation without the last character
        }
        // Convert the remainder to an integer and return it
        return Integer.parseInt(remainder);
    }

    @Override
    public final boolean isZero() {

        // if this.rep is empty, return true; else return false
        return this.rep.equals("");
    }

}
