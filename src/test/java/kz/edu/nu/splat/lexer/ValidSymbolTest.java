package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidSymbolTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ";",
            ":",
            ","
    })
    void testTokenizeValidSymbol(String symbol) throws LexException, IOException {
        List<Token> tokens = lexer.tokenize(symbol);
        assertEquals(1, tokens.size());
        assertEquals(symbol, tokens.get(0).value);
    }

    @Test
    void testTokenizeOpenParentheses() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("()");
        assertEquals(2, tokens.size());
        assertEquals("(", tokens.get(0).value);
        assertEquals(")", tokens.get(1).value);
    }
}