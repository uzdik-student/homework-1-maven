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

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"program", "begin", "end", "is", "while", "do", "if", "then", "else", "print", "print_line", "return", "and", "or", "not", "void", "Integer", "Boolean", "String", "true", "false"})
    void testTokenizeKeyword(String keyword) throws LexException, IOException {
        List<Token> tokens = lexer.tokenize(keyword);

        assertEquals(1, tokens.size());
        assertEquals(keyword, tokens.get(0).value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "A", "_", "_Xyz0123456789_"})
    void testTokenizeLabel(String label) throws LexException, IOException {
        List<Token> tokens = lexer.tokenize(label);
        assertEquals(1, tokens.size());
        assertEquals(label, tokens.get(0).value);
    }

    @Test
    void testTokenizeAssign() throws LexException, IOException {
        List<Token> tokens = lexer.tokenize("a := 2");
        assertEquals(3, tokens.size());
        assertEquals("a", tokens.get(0).value);
        assertEquals(":=", tokens.get(1).value);
        assertEquals("2", tokens.get(2).value);
    }

    @Test
    void testFileNotFoundException() {
        // Instantiate test class
        Lexer lexer = new Lexer(new File("nonexistent.splat"));

        // Execute test method
        assertThrows(FileNotFoundException.class, lexer::tokenize);
    }
}