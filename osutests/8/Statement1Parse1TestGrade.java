import components.statement.Statement;
import components.statement.Statement1;

/**
 * Customized JUnit test fixture for {@code Statement1Parse1}.
 */
public class Statement1Parse1TestGrade extends StatementTestGrade {

    @Override
    protected final Statement constructorTest() {
        return new Statement1Parse1();
    }

    @Override
    protected final Statement constructorRef() {
        return new Statement1();
    }

}
