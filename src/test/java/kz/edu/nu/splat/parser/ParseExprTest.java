package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.LexException;
import kz.edu.nu.splat.lexer.Lexer;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.expr.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParseExprTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testParseExprLabel() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "x;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(LabelExpr.class, expression);
    }

    @Test
    void testParseExprIntLiteral() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "123;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(LiteralIntExpr.class, expression);
    }

    @Test
    void testParseExprStringLiteral() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "\"abc\";";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(LiteralStringExpr.class, expression);
    }

    @Test
    void testParseExprBooleanLiteral() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "true;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(LiteralBoolExpr.class, expression);
    }

    @Test
    void testParseExprMinusUnary() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "(-1);";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(UnaryExpr.class, expression);
    }

    @Test
    void testParseExprNotUnary() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "(not true);";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(UnaryExpr.class, expression);
    }

    @Test
    void testParseExprBinary() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "(1+2);";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Expression expression = parser.parseExpr();

        // Assertions
        assertNotNull(expression);
        assertInstanceOf(BinaryExpr.class, expression);
    }
}