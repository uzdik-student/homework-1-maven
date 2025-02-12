package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticBinOpTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testTokenizeAddition() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 + 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals("+", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeSubtraction() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 - 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals("-", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeMultiplication() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 * 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals("*", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeDivision() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 / 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals("/", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testTokenizeModulus() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("4 % 2");
        assertEquals(3, tokens.size());
        assertEquals("4", tokens.get(0).value);
        assertEquals("%", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }
}