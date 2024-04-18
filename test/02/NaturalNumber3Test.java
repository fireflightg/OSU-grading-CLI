import components.naturalnumber.NaturalNumber;

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

        NaturalNumber num = new NaturalNumber3(n);
        return num;
    }

    @Override
    protected final NaturalNumber constructorRef() {

        NaturalNumber n = new NaturalNumber3();
        return n;
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        NaturalNumber n = new NaturalNumber3(i);
        return n;
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        NaturalNumber n = new NaturalNumber3(s);
        return n;
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        NaturalNumber num = new NaturalNumber3(n);
        return num;
    }

}
