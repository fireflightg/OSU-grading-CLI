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
 * @author Sean Asquith
 * @author Fabrizzio Ramon
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
        /*
         * Initialize an empty String
         */
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

        //checking if its zero if so create new constructor
        if (i == 0) {
            this.createNewRep();
        } else {
            this.createNewRep();
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

        this.createNewRep();
        /*
         * Set this.rep to s if s is not 0.
         */
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

        if (n.isZero()) {
            this.createNewRep();
        } else {
            this.createNewRep();
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

        /*
         * Concatenate k to the end of this.rep. If this.rep is empty, don't
         * concatenate a 0.
         */
        if (this.rep.equals("")) {
            if (k != 0) {
                this.rep += k;
            }
        } else {
            this.rep += k;
        }
    }

    @Override
    public final int divideBy10() {
        int remainder = 0;

        /*
         * If this.rep has a length of 1, the remainder will be this.rep and
         * this.rep should be reset.
         */
        if (this.rep.length() == 1) {
            remainder = Integer.parseInt(this.rep);
            this.createNewRep();
        } else if (this.rep.length() > 1) {
            /*
             * If this.rep has a length greater than 1, the remainder will be
             * the last character and the remainder should be taken off this.rep
             */

            //Get the last character from this.rep
            char lastChar = this.rep.charAt(this.rep.length() - 1);
            String lastCharacter = Character.toString(lastChar);
            remainder = Integer.parseInt(lastCharacter);

            //Remove the last character from this.rep
            int length = this.rep.length();
            String newVal = "";
            for (int i = 0; i < length - 1; i++) {
                String character = Character.toString(this.rep.charAt(i));
                newVal += character;
            }
            this.rep = newVal;
        }

        return remainder;
    }

    @Override
    public final boolean isZero() {
        boolean check = this.rep.isEmpty();
        return check;
    }
}