package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WhitespaceTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testTokenizeEmptySourceCode() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("");
        assertNotNull(tokens);
        assertEquals(0, tokens.size());
    }

    @Test
    void testTokenizeSpaces() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("   ");
        assertEquals(0, tokens.size());
    }

    @Test
    void testTokenizeNewLines() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("\n\n\n");
        assertEquals(0, tokens.size());
    }
}