package kz.edu.nu.splat.lexer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidSymbolTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "\"",
            "{",
            "}",
            "=",
            "!",
            "±",
            "'",
            "\\"
    })
    void testTokenizeInvalidSymbol(String symbol) {
        assertThrows(LexException.class, () -> lexer.tokenize(symbol));
    }

    @Test
    void testTokenizeMultilineQuotationMarks() {
        assertThrows(LexException.class, () -> lexer.tokenize("\" \n \""));
    }

}