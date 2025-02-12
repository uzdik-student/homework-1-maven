package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LexerTest {

    Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testFileNotFoundException() {
        // Instantiate test class
        Lexer lexer = new Lexer(new File("nonexistent.splat"));

        // Execute test method
        assertThrows(FileNotFoundException.class, lexer::tokenize);
    }

    @ParameterizedTest
    @ValueSource(strings = {"program", "begin", "end", "is", "while", "do", "if", "then", "else", "print", "print_line", "return", "and", "or", "not", "void", "Integer", "Boolean", "String", "true", "false"})
    void testTokenizeWithKeyword(String keyword) throws LexException, IOException {
        List<Token> tokens = lexer.tokenize(keyword);

        assertEquals(1, tokens.size());
        assertEquals(keyword, tokens.get(0).value);
    }

    @Test
    void testTokenizeWithVariable() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("variable");
        assertEquals(1, tokens.size());
        assertEquals("variable", tokens.get(0).value);
    }

    @Test
    void testTokenizeWithSingleQuotationMark() {
        assertThrows(LexException.class, () -> lexer.tokenize("\""));
    }

    @Test
    void testTokenizeWithSingleCurlyBracket() {
        assertThrows(LexException.class, () -> lexer.tokenize("{"));
    }

    @Test
    void testTokenizeWithSingleEqualSymbol() {
        assertThrows(LexException.class, () -> lexer.tokenize("="));
    }

    @Test
    void testTokenizeWithExclamationMark() {
        assertThrows(LexException.class, () -> lexer.tokenize("!"));
    }

    @Test
    void testTokenizeWithPlusMinusSymbol() {
        assertThrows(LexException.class, () -> lexer.tokenize("±"));
    }

    @Test
    void testTokenizeWithApostrophe() {
        assertThrows(LexException.class, () -> lexer.tokenize("'"));
    }

    @Test
    void testTokenizeWithBackslash() {
        assertThrows(LexException.class, () -> lexer.tokenize("\\"));
    }
}