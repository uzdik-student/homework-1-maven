package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.*;
import kz.edu.nu.splat.parser.elements.enums.BinaryOp;
import kz.edu.nu.splat.parser.elements.enums.Type;
import kz.edu.nu.splat.parser.elements.enums.UnaryOp;
import kz.edu.nu.splat.parser.elements.expr.*;
import kz.edu.nu.splat.parser.elements.stmt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static kz.edu.nu.splat.parser.ParseException.DECLARATION_EXPECTED;
import static kz.edu.nu.splat.parser.ParseException.UNEXPECTED_EOF;

public class Parser {

	/**
	 * Regex for keywords
	 */
	private final Pattern keywords = Pattern.compile("program|begin|end|is|while|do|if|then|else|print|print_line|return|and|or|not|void|Integer|Boolean|String|true|false");

	/**
	 * List of tokens
	 */
	private final List<Token> tokens;

	private final String LEFT_PARENTHESIS = "(";
	private final String RIGHT_PARENTHESIS = ")";
	private final String UNARY_NOT = "not";
	private final String UNARY_MINUS = "-";
	
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	/**
	 * Compares the next token to an expected value, and throws
	 * an exception if they don't match.  This removes the front-most
	 * (next) token  
	 * 
	 * @param expected value of the next token
	 * @throws ParseException if the actual token doesn't match what 
	 * 			was expected
	 */
	private void checkNext(String expected) throws ParseException {

		Token tok = tokens.remove(0);
		
		if (!tok.getValue().equals(expected)) {
			throw new ParseException("Expected '"+ expected + "', got '" 
					+ tok.getValue()+ "'.", tok);
		}
	}
	
	/**
	 * Returns a boolean indicating whether or not the next token matches
	 * the expected String value.  This does not remove the token from the
	 * token list.
	 * 
	 * @param expected value of the next token
	 * @return true iff the token value matches the expected string
	 */
	private boolean peekNext(String expected) {
		return tokens.get(0).getValue().equals(expected);
	}
	
	/**
	 * Returns a boolean indicating whether or not the token directly after
	 * the front most token matches the expected String value.  This does 
	 * not remove any tokens from the token list.
	 * 
	 * @param expected value of the token directly after the next token
	 * @return true iff the value matches the expected string
	 */
	private boolean peekTwoAhead(String expected) {
		return tokens.get(1).getValue().equals(expected);
	}
	
	
	/*
	 *  <program> ::= program <decls> begin <stmts> end ;
	 */
	public ProgramAST parse() throws ParseException {
		
		try {
			// Needed for 'program' token position info
			Token startTok = tokens.get(0);
			
			checkNext("program");
			
			List<Declaration> decls = parseDecls();
			
			checkNext("begin");
			
			List<Statement> stmts = parseStmts();
			
			checkNext("end");
			checkNext(";");
	
			return new ProgramAST(decls, stmts, startTok);
			
		// This might happen if we do a tokens.get(), and nothing is there!
		} catch (IndexOutOfBoundsException ex) {
			
			throw new ParseException(UNEXPECTED_EOF, -1, -1);
		}
	}
	
	/*
	 *  <decls> ::= (  <decl>  )*
	 */
	private List<Declaration> parseDecls() throws ParseException {
		
		List<Declaration> decls = new ArrayList<Declaration>();
		
		while (!peekNext("begin")) {
			Declaration decl = parseDecl();
			decls.add(decl);
		}
		
		return decls;
	}
	
	/*
	 * <decl> ::= <var-decl> | <func-decl>
	 */
	private Declaration parseDecl() throws ParseException {

		if (peekTwoAhead(":")) {
			return parseVarDecl();
		} else if (peekTwoAhead("(")) {
			return parseFuncDecl();
		} else {
			Token tok = tokens.get(0);
			throw new ParseException(DECLARATION_EXPECTED, tok);
		}
	}
	
	/*
	 * <func-decl> ::= <label> ( <params> ) : <ret-type> is 
	 * 						<loc-var-decls> begin <stmts> end ;
	 */
	private FunctionDecl parseFuncDecl() throws ParseException {
		Token startTok = tokens.remove(0);
		String label = startTok.getValue();
		checkNext("(");
		List<Param> params = parseParams();
		checkNext(")");
		checkNext(":");
		Type returnType = parseType();
		checkNext("is");
		List<VariableDecl> localVarDecls = parseLocVarDecls();
		checkNext("begin");
		List<Statement> stmts = parseStmts();
		checkNext("end");
		checkNext(";");

		return new FunctionDecl(startTok, label, params, returnType, localVarDecls, stmts);
	}

	/*
	 * <var-decl> ::= <label> : <type> ;
	 */
	private VariableDecl parseVarDecl() throws ParseException {
		Token token = tokens.remove(0);
		String label = parseLabel(token);
		checkNext(":");
		Type type = parseType();
		checkNext(";");

		return new VariableDecl(token, label, type);
	}
	
	/*
	 * <stmts> ::= (  <stmt>  )*
	 */
	private List<Statement> parseStmts() throws ParseException {
		List<Statement> stmts = new ArrayList<>();

		while (!peekNext("end")) {
			Statement stmt = parseStmt();
			stmts.add(stmt);
		}

		return stmts;
	}

	private List<VariableDecl> parseLocVarDecls() throws ParseException {
		List<VariableDecl> decls = new ArrayList<>();

		while (!peekNext("begin")) {
			VariableDecl decl = parseVarDecl();
			decls.add(decl);
		}

		return decls;
	}

	private List<Param> parseParams() throws ParseException {
		List<Param> params = new ArrayList<>();

		// If the next token is not ")", then there are parameters to parse
		if (!peekNext(")")) {
			do {
				if (peekNext(",")) {
					tokens.remove(0);
				}

				Token token = tokens.remove(0);
				String label = token.getValue();
				checkNext(":");
				Type type = parseType();

				params.add(new Param(token, label, type));
			} while (peekNext(","));
		}

		return params;
	}

	private Statement parseStmt() throws ParseException {
		if (peekNext("while")) {
			return parseWhileStmt();
		} else if (peekNext("if")) {
			// Parse if statement
			return parseIfStmt();
		} else if (peekNext("print")) {
			// Parse print statement
			return parsePrintStmt();
		} else if (peekNext("print_line")) {
			// Parse print_line statement
			Token token = tokens.get(0);
			checkNext("print_line");
			checkNext(";");
			return new PrintLineStmt(token);
		} else if (peekNext("return")) {
			// Parse return statement
			return parseReturnStmt();
		} else if (peekTwoAhead("(")) {
			// Parse function call
			return parseFuncCall();
		} else {
			// Parse assignment statement
			return parseAssignStmt();
		}
	}

	private Statement parseFuncCall() throws ParseException {
		Token token = tokens.get(0);
		FuncCallExpr expr = parseFuncCallExpr();
		checkNext(";");
		return new FuncCallStmt(token, expr.getLabel(), expr.getArguments());
	}

	private FuncCallExpr parseFuncCallExpr() throws ParseException {
		Token token = tokens.remove(0);
		String label = token.getValue();
		checkNext("(");
		List<Expression> arguments = parseArguments();
		checkNext(")");
		return new FuncCallExpr(token, label, arguments);
	}

	private List<Expression> parseArguments() throws ParseException {
		List<Expression> arguments = new ArrayList<>();

		// If the next token is not ")", then there are arguments to parse
		if (!peekNext(")")) {
			do {
				if (peekNext(",")) {
					tokens.remove(0);
				}

				Expression argument = parseExpr();
				arguments.add(argument);
			} while (peekNext(","));
		}

		return arguments;
	}

	private Statement parseIfStmt() throws ParseException {
		Token token = tokens.get(0);
		checkNext("if");
		Expression condition = parseExpr();
		checkNext("then");
		List<Statement> thenStmts = new ArrayList<>();
		while (!peekNext("end") && !peekNext("else")) {
			Statement stmt = parseStmt();
			thenStmts.add(stmt);
		}
		List<Statement> elseStmts = new ArrayList<>();
		if (peekNext("else")) {
			checkNext("else");
			elseStmts = parseStmts();
		}
		checkNext("end");
		checkNext("if");
		checkNext(";");

		return new IfStmt(token, condition, thenStmts, elseStmts);
	}

	private Statement parsePrintStmt() throws ParseException {
		Token token = tokens.get(0);
		checkNext("print");
		Expression expr = parseExpr();
		checkNext(";");
		return new PrintStmt(token, expr);
	}

	private Statement parseReturnStmt() throws ParseException {
		Token token = tokens.get(0);
		checkNext("return");
		if (peekNext(";")) {
			checkNext(";");
			return new ReturnStmt(token, null);
		}

		Expression expr = parseExpr();
		checkNext(";");
		return new ReturnStmt(token, expr);
	}

	private WhileStmt parseWhileStmt() throws ParseException {
		Token tok = tokens.get(0);
		checkNext("while");
		Expression condition = parseExpr();
		checkNext("do");
		List<Statement> body = parseStmts();
		checkNext("end");
		checkNext("while");
		checkNext(";");

		return new WhileStmt(tok, condition, body);
	}

	private Expression parseExpr() throws ParseException {
		Token token = tokens.get(0);

		if (peekNext(LEFT_PARENTHESIS)) {
			if (peekTwoAhead(UNARY_MINUS) || peekTwoAhead(UNARY_NOT)) {
				return parseUnaryExpr(token);
			} else {
				return parseBinaryExpr(token);
			}
		} else if (peekTwoAhead(LEFT_PARENTHESIS)) {
			return parseFuncCallExpr();
		} else if (peekLiteralInt()) {
			return parseLiteralInt();
		} else if (peekLiteralBool()) {
			return parseLiteralBool();
		} else if (peekLiteralString()) {
			return parseLiteralString();
		} else {
			return parseLabel();
		}
	}

	private BinaryExpr parseBinaryExpr(Token token) throws ParseException {
		checkNext(LEFT_PARENTHESIS);
		Expression left = parseExpr();
		BinaryOp operator = parseBinaryOp();
		Expression right = parseExpr();
		checkNext(RIGHT_PARENTHESIS);
		return new BinaryExpr(token, left, operator, right);
	}

	private BinaryOp parseBinaryOp() throws ParseException {
		Token token = tokens.remove(0);
		switch (token.getValue()) {
			case "and": return BinaryOp.AND;
			case "or": return BinaryOp.OR;
			case ">": return BinaryOp.GREATER_THAN;
			case "<": return BinaryOp.LESS_THAN;
			case "==": return BinaryOp.EQUALITY;
			case ">=": return BinaryOp.GREATER_THAN_OR_EQUAL_TO;
			case "<=": return BinaryOp.LESS_THAN_OR_EQUAL_TO;
			case "+": return BinaryOp.ADDITION;
			case "-": return BinaryOp.SUBTRACTION;
			case "*": return BinaryOp.MULTIPLICATION;
			case "/": return BinaryOp.DIVISION;
			case "%": return BinaryOp.MODULUS;
			default: throw new ParseException("unknown binary operator", token);
		}
    }

	private UnaryExpr parseUnaryExpr(Token token) throws ParseException {
		checkNext(LEFT_PARENTHESIS);
		UnaryOp operator = parseUnaryOp();
		Expression expr = parseExpr();
		checkNext(RIGHT_PARENTHESIS);
		return new UnaryExpr(token, operator, expr);
	}

	private UnaryOp parseUnaryOp() throws ParseException {
		Token token = tokens.remove(0);
		switch (token.getValue()) {
			case UNARY_NOT: return UnaryOp.NOT;
			case UNARY_MINUS: return UnaryOp.MINUS;
			default: throw new ParseException("unknown unary operator", token);
		}
	}

	private boolean peekLiteralInt() {
		Token token = tokens.get(0);
		String value = token.getValue();
		return value.matches("\\d+");
	}

	private boolean peekLiteralBool() {
		Token token = tokens.get(0);
		String value = token.getValue();
		return value.equals("true") || value.equals("false");
	}

	private boolean peekLiteralString() {
		Token token = tokens.get(0);
		String value = token.getValue();
		return value.startsWith("\"") && value.endsWith("\"");
	}

	private Expression parseLiteralInt() {
		Token token = tokens.remove(0);
		String value = token.getValue();
		return new LiteralIntExpr(token, Type.INTEGER, Integer.parseInt(value));
	}

	private Expression parseLiteralBool() {
		Token token = tokens.remove(0);
		String value = token.getValue();
		return new LiteralBoolExpr(token, Type.BOOLEAN, Boolean.parseBoolean(value));
	}

	private Expression parseLiteralString() {
		Token token = tokens.remove(0);
		String value = token.getValue();
		return new LiteralStringExpr(token, Type.STRING, value.replaceAll("\"", ""));
	}

	private Expression parseLabel() throws ParseException {
		Token token = tokens.remove(0);
		String value = token.getValue();

		return new LabelExpr(token, value);
	}

	private Statement parseAssignStmt() throws ParseException {
		Token token = tokens.remove(0);
		String label = token.getValue();
		checkNext(":=");
		Expression expr = parseExpr();
		checkNext(";");

		return new AssignStmt(token, label, expr);
	}

	private String parseLabel(Token token) throws ParseException {
		String label = token.getValue();
		Matcher matcher = keywords.matcher(label);
		if (matcher.matches()) {
			throw new ParseException("Label can not contain a keyword", token);
		}
		return label;
	}

	private Type parseType() throws ParseException {
		Token tok = tokens.remove(0);

		switch (tok.getValue()) {
			case "Integer": return Type.INTEGER;
			case "Boolean": return Type.BOOLEAN;
			case "String": return Type.STRING;
			case "void": return Type.VOID;
			default: throw new ParseException("Type expected", tok);
		}
	}

}
