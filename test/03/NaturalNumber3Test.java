import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
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

        NaturalNumber reference = new NaturalNumber1L();

        return reference;
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        NaturalNumber reference = new NaturalNumber1L(i);

        return reference;
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        NaturalNumber reference = new NaturalNumber1L(s);

        return reference;
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        NaturalNumber reference = new NaturalNumber1L(n);

        return reference;
    }

}
