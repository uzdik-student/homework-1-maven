package kz.edu.nu.splat.parser;

import kz.edu.nu.splat.SplatException;
import kz.edu.nu.splat.lexer.Token;

public class ParseException extends SplatException {
	
	public ParseException(String msg, Token tok) {
		super(msg, tok.getLine(), tok.getColumn());
	}
	
	public ParseException(String msg, int line, int column) {
		super(msg, line, column);
	}
}
