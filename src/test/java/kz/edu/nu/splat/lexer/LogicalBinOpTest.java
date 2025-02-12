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

public class LogicalBinOpTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testTokenizeAnd() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("true and true");
        assertEquals(3, tokens.size());
        assertEquals("true", tokens.get(0).value);
        assertEquals("and", tokens.get(1).value);
        assertEquals("true", tokens.get(2).value);
    }

    @Test
    void testTokenizeOr() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("false or true");
        assertEquals(3, tokens.size());
        assertEquals("false", tokens.get(0).value);
        assertEquals("or", tokens.get(1).value);
        assertEquals("true", tokens.get(2).value);
    }
}