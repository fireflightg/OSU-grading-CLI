import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        //create from blank
        return new NaturalNumber3();
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        //create from int
        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        //create from string
        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        //create from Natural Number
        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {
        //create Natural Number using NaturalNumber1L
        return new NaturalNumber1L();
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        //create Natural Number using NaturalNumber1L for int
        return new NaturalNumber1L(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        //create Natural Number using NaturalNumber1L for string
        return new NaturalNumber1L(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        //create Natural Number using NaturalNumber1L for natural number
        return new NaturalNumber1L(n);
    }

}
