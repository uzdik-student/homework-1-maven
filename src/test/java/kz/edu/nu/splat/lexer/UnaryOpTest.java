package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnaryOpTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testTokenizeNegativeNumber() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("-123");
        assertEquals(2, tokens.size());
        assertEquals("-", tokens.get(0).value);
        assertEquals("123", tokens.get(1).value);
    }

    @Test
    void testTokenizeNot() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("not true");
        assertEquals(2, tokens.size());
        assertEquals("not", tokens.get(0).value);
        assertEquals("true", tokens.get(1).value);
    }
}