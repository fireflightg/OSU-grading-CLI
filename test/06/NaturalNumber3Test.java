import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber3;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        NaturalNumber n = new NaturalNumber3();
        return n;
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        NaturalNumber n = new NaturalNumber3(i);
        return n;
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        NaturalNumber n = new NaturalNumber3(s);
        return n;
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        NaturalNumber n2 = new NaturalNumber3(n);
        return n2;
    }

    @Override
    protected final NaturalNumber constructorRef() {
        NaturalNumber n = new NaturalNumber1L();
        return n;
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        NaturalNumber n = new NaturalNumber1L(i);
        return n;
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        NaturalNumber n = new NaturalNumber1L(s);
        return n;
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        NaturalNumber n2 = new NaturalNumber1L(n);
        return n2;
    }

}
