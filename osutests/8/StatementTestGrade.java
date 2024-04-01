import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Derek Schneider
 *
 */
public abstract class StatementTestGrade {
    /**
     * Name of folder where Statement files are stored (point of control)
     */
    private static final String testFolder = "test/";
    private static final String statementFolder = testFolder
            + "StatementTestFiles/";
    private static final String ifFolder = statementFolder + "IF/";
    private static final String ifElseFolder = statementFolder + "IF_ELSE/";
    private static final String whileFolder = statementFolder + "WHILE/";
    private static final String callFolder = statementFolder + "CALL/";
    private static final String blockFolder = statementFolder + "BLOCK/";

    /**
     * The name of a file containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "test/statement1.bl",
            FILE_NAME_2 = "test/statement2.bl",
            FILE_NAME_IF_ELSE_VALID = ifElseFolder + "IF_ELSE_Valid.bl",
            FILE_NAME_IF_VALID = ifFolder + "IF_Valid.bl",
            FILE_NAME_WHILE_VALID = whileFolder + "WHILE_Valid.bl",
            FILE_NAME_VALID_EXAMPLE = statementFolder + "Valid_Example.bl",
            FILE_NAME_PARSE_IF_MissingIFKeyword = ifFolder
                    + "IF_MissingIFKeyword.bl",
            FILE_NAME_PARSE_IF_ConditionIsKeyword = ifFolder
                    + "IF_ConditionIsKeyword.bl",
            FILE_NAME_PARSE_IF_InvalidCondition = ifFolder
                    + "IF_InvalidCondition.bl",
            FILE_NAME_PARSE_IF_MissingTHENKeyword = ifFolder
                    + "IF_MissingTHENKeyword.bl",
            FILE_NAME_PARSE_IF_MisspelledTHENKeyword = ifFolder
                    + "IF_MisspelledTHENKeyword.bl",
            FILE_NAME_PARSE_IF_THENIsOtherKeyword = ifFolder
                    + "IF_THENIsOtherKeyword.bl",
            FILE_NAME_PARSE_IF_MissingENDKeyword = ifFolder
                    + "IF_MissingENDKeyword.bl",
            FILE_NAME_PARSE_IF_MisspelledENDKeyword = ifFolder
                    + "IF_MisspelledENDKeyword.bl",
            FILE_NAME_PARSE_IF_MissingIFAtEnd = ifFolder
                    + "IF_MissingIFAtEnd.bl",
            FILE_NAME_PARSE_IF_MisspelledIFAtEnd = ifFolder
                    + "IF_MisspelledIFAtEnd.bl",
            FILE_NAME_PARSE_IF_WrongEndingKeyword = ifFolder
                    + "IF_WrongEndingKeyword.bl",
            FILE_NAME_PARSE_IF_ENDIsOtherKeyword = ifFolder
                    + "IF_ENDIsOtherKeyword.bl",
            FILE_NAME_PARSE_IFELSE_MissingIFKeyword = ifElseFolder
                    + "IF_ELSE_MissingIFKeyword.bl",
            FILE_NAME_PARSE_IFELSE_ConditionIsKeyword = ifElseFolder
                    + "IF_ELSE_ConditionIsKeyword.bl",
            FILE_NAME_PARSE_IFELSE_InvalidCondition = ifElseFolder
                    + "IF_ELSE_InvalidCondition.bl",
            FILE_NAME_PARSE_IFELSE_MissingTHENKeyword = ifElseFolder
                    + "IF_ELSE_MissingTHENKeyword.bl",
            FILE_NAME_PARSE_IFELSE_MisspelledTHENKeyword = ifElseFolder
                    + "IF_ELSE_MisspelledTHENKeyword.bl",
            FILE_NAME_PARSE_IFELSE_THENIsOtherKeyword = ifElseFolder
                    + "IF_ELSE_THENIsOtherKeyword.bl",
            FILE_NAME_PARSE_IFELSE_MissingENDKeyword = ifElseFolder
                    + "IF_ELSE_MissingENDKeyword.bl",
            FILE_NAME_PARSE_IFELSE_MisspelledENDKeyword = ifElseFolder
                    + "IF_ELSE_MisspelledENDKeyword.bl",
            FILE_NAME_PARSE_IFELSE_MissingIFAtEnd = ifElseFolder
                    + "IF_ELSE_MissingIFAtEnd.bl",
            FILE_NAME_PARSE_IFELSE_MisspelledIFAtEnd = ifElseFolder
                    + "IF_ELSE_MisspelledIFAtEnd.bl",
            FILE_NAME_PARSE_IFELSE_WrongEndingKeyword = ifElseFolder
                    + "IF_ELSE_WrongEndingKeyword.bl",
            FILE_NAME_PARSE_IFELSE_ENDIsOtherKeyword = ifElseFolder
                    + "IF_ELSE_ENDIsOtherKeyword.bl",
            FILE_NAME_PARSE_WHILE_MissingWHILEKeyword = whileFolder
                    + "WHILE_MissingWHILEKeyword.bl",
            FILE_NAME_PARSE_WHILE_ConditionIsKeyword = whileFolder
                    + "WHILE_ConditionIsKeyword.bl",
            FILE_NAME_PARSE_WHILE_InvalidCondition = whileFolder
                    + "WHILE_InvalidCondition.bl",
            FILE_NAME_PARSE_WHILE_MissingDOKeyword = whileFolder
                    + "WHILE_MissingDOKeyword.bl",
            FILE_NAME_PARSE_WHILE_MisspelledDOKeyword = whileFolder
                    + "WHILE_MisspelledDOKeyword.bl",
            FILE_NAME_PARSE_WHILE_DOIsOtherKeyword = whileFolder
                    + "WHILE_DOIsOtherKeyword.bl",
            FILE_NAME_PARSE_WHILE_MissingENDKeyword = whileFolder
                    + "WHILE_MissingENDKeyword.bl",
            FILE_NAME_PARSE_WHILE_MisspelledENDKeyword = whileFolder
                    + "WHILE_MisspelledENDKeyword.bl",
            FILE_NAME_PARSE_WHILE_MissingWHILEAtEnd = whileFolder
                    + "WHILE_MissingWHILEAtEnd.bl",
            FILE_NAME_PARSE_WHILE_MisspelledWHILEAtEnd = whileFolder
                    + "WHILE_MisspelledWHILEAtEnd.bl",
            FILE_NAME_PARSE_WHILE_WrongEndingKeyword = whileFolder
                    + "WHILE_WrongEndingKeyword.bl",
            FILE_NAME_PARSE_WHILE_ENDIsOtherKeyword = whileFolder
                    + "WHILE_ENDIsOtherKeyword.bl",
            FILE_NAME_PARSE_CALL_IsKeyword = callFolder + "CALL_IsKeyword.bl",
            FILE_NAME_PARSE_CALL_IsCondition = callFolder
                    + "CALL_IsCondition.bl",
            FILE_NAME_PARSE_CALL_IsNotValidIdentifier = callFolder
                    + "CALL_IsNotValidIdentifier.bl",
            FILE_NAME_PARSE_BLOCK_Complex = blockFolder + "BLOCK_Complex.bl",
            FILE_NAME_PARSE_BLOCK_FirstNotValidIdentifier = blockFolder
                    + "BLOCK_FirstNotValidIdentifier.bl",
            FILE_NAME_PARSE_BLOCK_ReplacementOfThisNonEmptyBlock = FILE_NAME_PARSE_BLOCK_Complex,
            FILE_NAME_PARSE_BLOCK_ReplacementOfThisNonBlockStatement = FILE_NAME_PARSE_BLOCK_Complex,
            FILE_NAME_PARSE_BLOCK_MissingEND = blockFolder
                    + "BLOCK_MissingEND.bl",
            FILE_NAME_PARSE_BLOCK_MissingENDNested = blockFolder
                    + "BLOCK_MissingENDNested.bl",
            FILE_NAME_PARSE_BLOCK_MaximallyLong = blockFolder
                    + "BLOCK_MaximallyLong.bl",
            FILE_NAME_PARSE_BLOCK_LowerCaseEND = blockFolder
                    + "BLOCK_LowerCaseEND.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures <pre>
     * {@code constructorTest = compose((BLOCK, ?, ?), <>)}
     * </pre>
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures <pre>
     * {@code constructorRef = compose((BLOCK, ?, ?), <>)}
     * </pre>
     */
    protected abstract Statement constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /*
     * Valid Test cases for parse
     */

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseWHILEValid() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_WHILE_VALID);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_WHILE_VALID);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseIFELSEValid() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_IF_ELSE_VALID);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_IF_ELSE_VALID);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testIFParseValid() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_IF_VALID);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_IF_VALID);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /*
     * Valid Tests for Parse Block
     */

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseBlockOnValidExample() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_VALID_EXAMPLE);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parseBlock(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_VALID_EXAMPLE);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parseBlock(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /*
     * ---------- Invalid Input Cases for parse ----------
     */

    /*
     * --- IF ---
     */

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing IF keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfMissingIFKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MissingIFKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Keyword instead of condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfConditionIsKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_ConditionIsKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Invalid Condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfInvalidCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_InvalidCondition);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing THEN
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfMissingTHENKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MissingTHENKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Misspelled THEN
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfMisspelledTHENKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MisspelledTHENKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: THEN is some other Keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfTHENIsOtherKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_THENIsOtherKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing the END at end of if
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfENDMissing() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MissingENDKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: END is other keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfENDIsOtherKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_ENDIsOtherKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Misspelled END at end of if
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfENDMisspelled() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MisspelledENDKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing IF at end of IF
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfMissingIFAtEnd() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MissingIFAtEnd);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Misspelled IF at end of if
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfMisspelledIFAtEnd() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_MisspelledIFAtEnd);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Other keyword than IF at end
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfWrongEndingKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IF_WrongEndingKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /*
     * --- IF ELSE ---
     */

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing IF keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseMissingIFKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MissingIFKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Keyword instead of condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseConditionIsKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_ConditionIsKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Invalid Condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseInvalidCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_InvalidCondition);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing THEN
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseMissingTHENKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MissingTHENKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Misspelled THEN
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseMisspelledTHENKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MisspelledTHENKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: THEN is some other Keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseTHENIsOtherKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_THENIsOtherKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing the END at end of if
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseENDMissing() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MissingENDKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: END is other keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseENDIsOtherKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_ENDIsOtherKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Misspelled END at end of if
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseENDMisspelled() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MisspelledENDKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Missing IF at end of IF
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseMissingIFAtEnd() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MissingIFAtEnd);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Misspelled IF at end of if
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseMisspelledIFAtEnd() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_MisspelledIFAtEnd);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for IF.
     *
     * Invalid Input: Other keyword than IF at end
     */
    @Test(expected = RuntimeException.class)
    public final void testParseIfElseWrongEndingKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_IFELSE_WrongEndingKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /*
     * --- WHILE ---
     */
    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Missing WHILE keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileMissingWHILEKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MissingWHILEKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Keyword instead of condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileConditionIsKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_ConditionIsKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Invalid Condition
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileInvalidCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_InvalidCondition);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Missing DO
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileMissingDOKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MissingDOKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Misspelled DO
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileMisspelledDOKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MisspelledDOKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: DO is some other Keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileDOIsOtherKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_DOIsOtherKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Missing the END at end of while
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileENDMissing() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MissingENDKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: END is other keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileENDIsOtherKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_ENDIsOtherKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Misspelled END at end of while
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileENDMisspelled() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MisspelledENDKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Missing WHILE at end of while
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileMissingWHILEAtEnd() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MissingWHILEAtEnd);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Misspelled WHILE at end of while
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileMisspelledWHILEAtEnd() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_MisspelledWHILEAtEnd);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Other keyword than WHILE at end
     */
    @Test(expected = RuntimeException.class)
    public final void testParseWhileWrongEndingKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_WHILE_WrongEndingKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /*
     * --- CALL ---
     */

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Call is a keyword rather than a valid call
     */
    @Test(expected = RuntimeException.class)
    public final void testParseCallIsKeyword() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_PARSE_CALL_IsKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Call is a condition rather than a valid call
     */
    @Test(expected = RuntimeException.class)
    public final void testParseCallIsCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_CALL_IsCondition);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input for WHILE.
     *
     * Invalid Input: Call is not a valid identifier
     */
    @Test(expected = RuntimeException.class)
    public final void testParseCallIsNotValidIdentifier() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_CALL_IsNotValidIdentifier);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /*
     * ---------- PARSE BLOCK VALID ---------
     */

    /**
     * Test of parseBlock on syntactically valid input (Somewhat complex).
     */
    public final void testParseBlockComplex() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_PARSE_BLOCK_Complex);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parseBlock(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_PARSE_BLOCK_Complex);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parseBlock(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parseBlock on syntactically valid input (Testing replacing the
     * parameter with a non-empty block).
     */
    @Test
    public final void testParseBlockForReplacementOfThisNonEmptyBlock() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        Statement ifStatement = this.constructorRef();
        ifStatement.assembleIf(Condition.RANDOM, ifStatement.newInstance());
        sRef.addToBlock(0, ifStatement);
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_ReplacementOfThisNonEmptyBlock);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parseBlock(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        ifStatement.assembleIf(Condition.RANDOM, ifStatement.newInstance());
        sTest.addToBlock(0, ifStatement);
        file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_ReplacementOfThisNonEmptyBlock);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parseBlock(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parseBlock on syntactically valid input (Testing replacing the
     * parameter with a call Statement).
     */
    @Test
    public final void testParseBlockForReplacementOfThisNonBlockStatement() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        sRef.assembleCall("move");
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_ReplacementOfThisNonBlockStatement);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parseBlock(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        sTest.assembleCall("move");
        file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_ReplacementOfThisNonBlockStatement);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parseBlock(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /*
     * ---------- PARSE BLOCK INVALID ----------m
     */

    /**
     * Test of parseBlock on syntactically invalid input.
     *
     * Invalid input: first keyword is not a valid identifier
     *
     * Note: parseBlock will parse the maximally long number of BL Statements,
     * which in this test is 0. So, the result should be an empty block and
     * should NOT report an error.
     */
    @Test
    public final void testParseFirstNotValidIdentifier() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_FirstNotValidIdentifier);
        Queue<String> tokens = Tokenizer.tokens(file);
        /*
         * Call
         */
        sRef.parseBlock(tokens);
        file.close();

        // sRef should be an empty block
        assertEquals(sRef, sRef.newInstance());
    }

    /**
     * Test of parseBlock on syntactically invalid input (Testing the maximally
     * long property of parseBlock).
     */
    @Test(expected = RuntimeException.class)
    public final void testParseBlockForMaximallyLong() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        sRef.assembleCall("move");
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_MaximallyLong);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parseBlock(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_PARSE_BLOCK_MaximallyLong);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parseBlock(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parseBlock on syntactically invalid input.
     *
     * Invalid input: Missing END keyword for block
     */
    @Test(expected = RuntimeException.class)
    public final void testParseBlockMissingEND() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_MissingEND);
        Queue<String> tokens = Tokenizer.tokens(file);
        /*
         * Call
         */
        sRef.parseBlock(tokens);
        file.close();
    }

    /**
     * Test of parseBlock on syntactically invalid input.
     *
     * Invalid input: Missing END keyword for block nested
     */
    @Test(expected = RuntimeException.class)
    public final void testParseBlockMissingENDNested() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_MissingENDNested);
        Queue<String> tokens = Tokenizer.tokens(file);
        /*
         * Call
         */
        sRef.parseBlock(tokens);
        file.close();
    }

    /**
     * Test of parseBlock on syntactically valid input.
     */
    @Test
    public final void testParseBlocklowerCaseEND() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_PARSE_BLOCK_LowerCaseEND);
        Queue<String> tokensRef = Tokenizer.tokens(file);
        file.close();
        /*
         * Call
         */
        sRef.parseBlock(tokensRef);

        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_PARSE_BLOCK_LowerCaseEND);
        Queue<String> tokensTest = Tokenizer.tokens(file);
        file.close();

        sTest.parseBlock(tokensTest);

        assertEquals(sRef, sTest);
    }
}
