package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.LexException;
import kz.edu.nu.splat.lexer.Lexer;
import kz.edu.nu.splat.parser.elements.ProgramAST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static kz.edu.nu.splat.parser.ParseException.DECLARATION_EXPECTED;
import static kz.edu.nu.splat.parser.ParseException.UNEXPECTED_EOF;
import static org.junit.jupiter.api.Assertions.*;

public class MainProgramTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testParseValidProgram() throws LexException, IOException, ParseException {
        String sourceCode = "program begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(0, ast.getDecls().size());
        assertEquals(0, ast.getStmts().size());
    }

    @Test
    void testParseNotStartsWithProgram() throws LexException, IOException {
        String sourceCode = "begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected 'program', got 'begin'.", exception.getMessage());
    }

    @Test
    void testParseNoBegin() throws LexException, IOException {
        String sourceCode = "program end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(DECLARATION_EXPECTED, exception.getMessage());
    }

    @Test
    void testParseNoEnd() throws LexException, IOException {
        String sourceCode = "program begin ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(UNEXPECTED_EOF, exception.getMessage());
    }

    @Test
    void testParseNotEndingWithSemicolon() throws LexException, IOException {
        String sourceCode = "program begin end";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(UNEXPECTED_EOF, exception.getMessage());
    }
}