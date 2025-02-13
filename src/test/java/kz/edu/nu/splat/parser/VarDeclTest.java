package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.LexException;
import kz.edu.nu.splat.lexer.Lexer;
import kz.edu.nu.splat.parser.elements.ProgramAST;
import kz.edu.nu.splat.parser.elements.VariableDecl;
import kz.edu.nu.splat.parser.elements.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static kz.edu.nu.splat.parser.ParseException.DECLARATION_EXPECTED;
import static org.junit.jupiter.api.Assertions.*;

public class VarDeclTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testParseIntegerVarDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program x : Integer; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(VariableDecl.class, ast.getDecls().get(0));
        VariableDecl decl = (VariableDecl) ast.getDecls().get(0);
        assertAll(
                () -> assertEquals("x", decl.getLabel()),
                () -> assertEquals(Type.INTEGER, decl.getType())
        );
    }

    @Test
    void testParseStringVarDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program y : String; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(VariableDecl.class, ast.getDecls().get(0));
        VariableDecl decl = (VariableDecl) ast.getDecls().get(0);
        assertEquals("y", decl.getLabel());
        assertEquals(Type.STRING, decl.getType());
    }

    @Test
    void testParseBooleanVarDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program z : Boolean; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(VariableDecl.class, ast.getDecls().get(0));
        VariableDecl decl = (VariableDecl) ast.getDecls().get(0);
        assertEquals("z", decl.getLabel());
        assertEquals(Type.BOOLEAN, decl.getType());
    }

    @Test
    void testParseVarDeclWithoutLabel() throws LexException, IOException {
        String sourceCode = "program Boolean; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(DECLARATION_EXPECTED, exception.getMessage());
    }

    @Test
    void testParseVarDeclWithoutType() throws LexException, IOException {
        String sourceCode = "program x; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(DECLARATION_EXPECTED, exception.getMessage());
    }

    @Test
    void testParseVarDeclWithoutColon() throws LexException, IOException {
        String sourceCode = "program z Boolean; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(DECLARATION_EXPECTED, exception.getMessage());
    }

    @Test
    void testParseVarDeclWithoutSemicolon() throws LexException, IOException {
        String sourceCode = "program z : Boolean begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected ';', got 'begin'.", exception.getMessage());
    }
}