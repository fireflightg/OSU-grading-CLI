import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.program.Program;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Program}'s constructor and kernel methods.
 *
 * @author Derek Schneider
 *
 */
public abstract class ProgramTestGrade {

    /**
     * Folder name for program test files to be put in (for point of control)
     */
    private static final String testFolder = "test/";
    private static final String programTestFolder = testFolder
            + "ProgramTestFiles/";

    /**
     * The names of a files containing a (possibly invalid) BL programs.
     */
    private static final String FILE_NAME_1 = "test/ProgramTestFiles/program1.bl",
            FILE_NAME_2 = "test/ProgramTestFiles/program2.bl",
            FILE_NAME_VALID_MORE = programTestFolder + "ValidMore.bl",
            FILE_NAME_ProgramNameNotValidIdentifier = programTestFolder
                    + "ProgramNameNotValidIdentifier.bl",
            FILE_NAME_ProgramNameIsKeyword = programTestFolder
                    + "ProgramNameIsKeyword.bl",
            FILE_NAME_FirstKeywordNotProgram = programTestFolder
                    + "FirstKeywordNotProgram.bl",
            FILE_NAME_ProgramNameDoesNotMatch = programTestFolder
                    + "ProgramNameDoesNotMatch.bl",
            FILE_NAME_ProgramBeginMissing = programTestFolder
                    + "ProgramBeginMissing.bl",
            FILE_NAME_ProgramBeginMisspelled = programTestFolder
                    + "ProgramBeginMisspelled.bl",
            FILE_NAME_ISMissing = programTestFolder + "ProgramISMissing.bl",
            FILE_NAME_ProgramISMisspelled = programTestFolder
                    + "ProgramISMisspelled.bl",
            FILE_NAME_ProgramENDMissing = programTestFolder
                    + "ProgramENDMissing.bl",
            FILE_NAME_ProgramINSTRUCTIONMissing = programTestFolder
                    + "ProgramINSTRUCTIONMissing.bl",
            FILE_NAME_ProgramINSTRUCTIONMisspelled = programTestFolder
                    + "ProgramINSTRUCTIONMisspelled.bl",
            FILE_NAME_ProgramINSTRUCTIONIsKeyword = programTestFolder
                    + "ProgramINSTRUCTIONIsKeyword.bl",
            FILE_NAME_ProgramNameIsPrimitiveInstruction = programTestFolder
                    + "ProgramNameIsPrimitiveInstruction.bl",
            FILE_NAME_ProgramInstructionNameIsMissing = programTestFolder
                    + "ProgramInstructionNameIsMissing.bl",
            FILE_NAME_ProgramInstructionIsKeywordMissing = programTestFolder
                    + "ProgramInstructionIsKeywordMissing.bl",
            FILE_NAME_ProgramInstructionISisKeyword = programTestFolder
                    + "ProgramInstructionISisKeyword.bl",
            FILE_NAME_ProgramInstructionNameDoesNotMatch = programTestFolder
                    + "ProgramInstructionNameDoesNotMatch.bl",
            FILE_NAME_ProgramInstructionENDMissing = programTestFolder
                    + "ProgramInstructionENDMissing.bl",
            FILE_NAME_ProgramInstructionDuplicates = programTestFolder
                    + "ProgramInstructionDuplicates.bl",
            FILE_NAME_ProgramInstructionNameIsPrimitive = programTestFolder
                    + "ProgramInstructionNameIsPrimitive.bl";

    /**
     * Invokes the {@code Program} constructor for the implementation under test
     * and returns the result.
     *
     * @return the new program
     * @ensures <pre>
     * {@code constructorTest = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))}
     * </pre>
     */
    protected abstract Program constructorTest();

    /**
     * Invokes the {@code Program} constructor for the reference implementation
     * and returns the result.
     *
     * @return the new program
     * @ensures <pre>
     * {@code constructorRef = ("Unnamed", {}, compose((BLOCK, ?, ?), <>))}
     * </pre>
     */
    protected abstract Program constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input. (Somewhat thorough)
     */
    @Test
    public final void testParseValid1() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_VALID_MORE);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_VALID_MORE);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /**
     * Test of parse on syntactically valid input.
     *
     * Program name is a primitive instruction which is valid!
     */
    @Test
    public final void testProgramNameIsPrimitiveInstruction() {
        /*
         * Setup
         */
        Program pRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramNameIsPrimitiveInstruction);
        pRef.parse(file);
        file.close();
        Program pTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_ProgramNameIsPrimitiveInstruction);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        pTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(pRef, pTest);
    }

    /*
     * ---------- Invalid Input Tests ----------
     */

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: The program name is an invalid identifier
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramNameNotValidIdentifier() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramNameNotValidIdentifier);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: The program name is a BL keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramNameIsKeyword() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_ProgramNameIsKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: The first keyword is not PROGRAM
     */
    @Test(expected = RuntimeException.class)
    public final void testFirstKeywordNotProgram() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_FirstKeywordNotProgram);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: IS missing
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramISMissing() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_ISMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: IS misspelled
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramISMisspelled() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_ProgramISMisspelled);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: BEGIN keyword missing
     */
    @Test(expected = RuntimeException.class)
    public final void testBEGINMissing() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_ProgramBeginMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: BEGIN keyword misspelled
     */
    @Test(expected = RuntimeException.class)
    public final void testBEGINMisspelled() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramBeginMisspelled);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Name of program does not match
     */
    @Test(expected = RuntimeException.class)
    public final void testProgramNameDoesNotMatch() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramNameDoesNotMatch);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: END keyword missing
     */
    @Test(expected = RuntimeException.class)
    public final void testMissingEndKeyword() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_ProgramENDMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /*
     * Invalid inputs relating to instruction
     */

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: INSTRUCTION keyword missing
     */
    @Test(expected = RuntimeException.class)
    public final void testINSTRUCTIONMissing() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramINSTRUCTIONMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: INSTRUCTION keyword misspelled
     */
    @Test(expected = RuntimeException.class)
    public final void testINSTRUCTIONMisspelled() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramINSTRUCTIONMisspelled);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Instruction name is a keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNameIsKeyword() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramINSTRUCTIONIsKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Instruction name is missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNameIsMissing() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionNameIsMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Instruction name is a primitive instruction
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNameIsPrimitive() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionNameIsPrimitive);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: IS Keyword Missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionISKeywordMissing() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionIsKeywordMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: IS is another keyword
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionISisKeyword() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionISisKeyword);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Instruction name does not match
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionNameDoesNotMatch() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionNameDoesNotMatch);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Instruction END Keyword Missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionENDMissing() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionENDMissing);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }

    /**
     * Test of parse on syntactically invalid input.
     *
     * Invalid Input: Two Instructions with the same name
     */
    @Test(expected = RuntimeException.class)
    public final void testInstructionDuplicates() {
        /*
         * Setup
         */
        Program pTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_ProgramInstructionDuplicates);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in a syntax error being found
         */
        pTest.parse(tokens);
    }
}
