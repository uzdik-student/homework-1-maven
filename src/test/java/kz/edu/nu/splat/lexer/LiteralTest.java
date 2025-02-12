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

public class LiteralTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"true", "false"})
    void testTokenizeBoolLiteral(String bool) throws LexException, IOException {
        List<Token> tokens = lexer.tokenize(bool);
        assertEquals(1, tokens.size());
        assertEquals(bool, tokens.get(0).value);
    }

    @Test
    void testTokenizeIntLiteral() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("123");
        assertEquals(1, tokens.size());
        assertEquals("123", tokens.get(0).value);
    }

    @Test
    void testTokenizeStringLiteral() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("\"Hello world\"");
        assertEquals(1, tokens.size());
        assertEquals("\"Hello world\"", tokens.get(0).value);
    }

    @Test
    void testTokenizeEmptyStringLiteral() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("\"\"");
        assertEquals(1, tokens.size());
        assertEquals("\"\"", tokens.get(0).value);
    }
}