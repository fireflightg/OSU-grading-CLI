import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L; // Reference implementation

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 *
 * @author Meena Kalil
 * @author Manraj Hansra
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {
        return new NaturalNumber1L(); // Using NaturalNumber1L as reference implementation
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        NaturalNumber ref = new NaturalNumber1L();
        ref.setFromInt(i);
        return ref;
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        NaturalNumber ref = new NaturalNumber1L();
        // Assuming there is no fromString method, set the value using setFromString
        ref.setFromString(s);
        return ref;
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        NaturalNumber ref = new NaturalNumber1L();
        ref.copyFrom(n);
        return ref;
    }
}
