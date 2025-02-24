package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.LexException;
import kz.edu.nu.splat.lexer.Lexer;
import kz.edu.nu.splat.parser.elements.Statement;
import kz.edu.nu.splat.parser.elements.stmt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParseStmtTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testParseStmtWhile() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "while true do end while;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(WhileStmt.class, statement);
    }

    @Test
    void testParseStmtIf() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "if true then end if;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(IfStmt.class, statement);
    }

    @Test
    void testParseStmtPrint() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "print 1;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(PrintStmt.class, statement);
    }

    @Test
    void testParseStmtPrintLine() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "print_line;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(PrintLineStmt.class, statement);
    }

    @Test
    void testParseStmtReturnExpr() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "return true;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(ReturnStmt.class, statement);
    }

    @Test
    void testParseStmtFuncCall() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "factorial(5);";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(FuncCallStmt.class, statement);
    }

    @Test
    void testParseStmtAssign() throws LexException, IOException, ParseException {
        // Prepare test data
        String sourceCode = "x := 1;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        // Execute test method
        Statement statement = parser.parseStmt();

        // Assertions
        assertNotNull(statement);
        assertInstanceOf(AssignStmt.class, statement);
    }
}