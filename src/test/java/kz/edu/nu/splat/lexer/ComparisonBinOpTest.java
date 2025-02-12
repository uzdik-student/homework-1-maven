package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonBinOpTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testTokenizeGreaterThan() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 > 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals(">", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeLessThan() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("2 < 4");
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).value);
        assertEquals("<", tokens.get(1).value);
        assertEquals("4", tokens.get(2).value);
    }

    @Test
    void testTokenizeEqual() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("2 == 2");
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).value);
        assertEquals("==", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeGreaterThanOrEqualTo() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 >= 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals(">=", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeLessThanOrEqualTo() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("2 <= 4");
        assertEquals(3, tokens.size());
        assertEquals("2", tokens.get(0).value);
        assertEquals("<=", tokens.get(1).value);
        assertEquals("4", tokens.get(2).value);
    }
}