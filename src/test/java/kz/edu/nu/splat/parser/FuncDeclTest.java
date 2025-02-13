package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.LexException;
import kz.edu.nu.splat.lexer.Lexer;
import kz.edu.nu.splat.parser.elements.FunctionDecl;
import kz.edu.nu.splat.parser.elements.ProgramAST;
import kz.edu.nu.splat.parser.elements.VariableDecl;
import kz.edu.nu.splat.parser.elements.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static kz.edu.nu.splat.parser.ParseException.DECLARATION_EXPECTED;
import static org.junit.jupiter.api.Assertions.*;

public class FuncDeclTest {

    private Lexer lexer;

    @BeforeEach
    void setUp() {
        lexer = new Lexer(null);
    }

    @Test
    void testParseVoidFuncDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program a() : void is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(FunctionDecl.class, ast.getDecls().get(0));
        FunctionDecl decl = (FunctionDecl) ast.getDecls().get(0);
        assertEquals("a", decl.getLabel());
        assertEquals(Type.VOID, decl.getRetType());
    }

    @Test
    void testParseIntegerFuncDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program a() : Integer is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(FunctionDecl.class, ast.getDecls().get(0));
        FunctionDecl decl = (FunctionDecl) ast.getDecls().get(0);
        assertEquals("a", decl.getLabel());
        assertEquals(Type.INTEGER, decl.getRetType());
    }

    @Test
    void testParseBooleanFuncDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program a() : Boolean is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(FunctionDecl.class, ast.getDecls().get(0));
        FunctionDecl decl = (FunctionDecl) ast.getDecls().get(0);
        assertEquals("a", decl.getLabel());
        assertEquals(Type.BOOLEAN, decl.getRetType());
    }

    @Test
    void testParseStringFuncDecl() throws LexException, IOException, ParseException {
        String sourceCode = "program a() : String is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        ProgramAST ast = parser.parse();

        assertNotNull(ast);
        assertEquals(1, ast.getDecls().size());
        assertInstanceOf(FunctionDecl.class, ast.getDecls().get(0));
        FunctionDecl decl = (FunctionDecl) ast.getDecls().get(0);
        assertEquals("a", decl.getLabel());
        assertEquals(Type.STRING, decl.getRetType());
    }

    @Test
    void testParseFuncDeclWithoutLabel() throws LexException, IOException {
        String sourceCode = "program () : String is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals(DECLARATION_EXPECTED, exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutBrackets() throws LexException, IOException {
        String sourceCode = "program a : String is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected ';', got 'is'.", exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutColon() throws LexException, IOException {
        String sourceCode = "program a() String is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected ':', got 'String'.", exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutType() throws LexException, IOException {
        String sourceCode = "program a() : is begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Type expected", exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutIs() throws LexException, IOException {
        String sourceCode = "program a() : String begin end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected 'is', got 'begin'.", exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutBegin() throws LexException, IOException {
        String sourceCode = "program a() : String is end; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Label can not contain a keyword", exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutEnd() throws LexException, IOException {
        String sourceCode = "program a() : String is begin; begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected ':=', got 'begin'.", exception.getMessage());
    }

    @Test
    void testParseFuncDeclWithoutSemicolon() throws LexException, IOException {
        String sourceCode = "program a() : String is begin end begin end ;";
        Parser parser = new Parser(lexer.tokenize(sourceCode));

        Exception exception = assertThrows(ParseException.class, parser::parse);
        assertEquals("Expected ';', got 'begin'.", exception.getMessage());
    }
}