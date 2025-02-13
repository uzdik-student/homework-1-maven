package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.LexException;
import kz.edu.nu.splat.lexer.Lexer;
import kz.edu.nu.splat.lexer.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static kz.edu.nu.splat.parser.ParseException.UNEXPECTED_EOF;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testParseNotStartsWithProgram() throws LexException, IOException {
        String sourceCode = "begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        assertThrows(ParseException.class, parser::parse);
    }

    @Test
    void testParseNotEndingWithSemicolon() throws LexException, IOException, ParseException {
        String sourceCode = "program begin end";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(UNEXPECTED_EOF, exception.getMessage());
    }
}