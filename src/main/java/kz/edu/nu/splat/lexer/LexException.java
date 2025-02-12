package kz.edu.nu.splat.lexer;


import kz.edu.nu.splat.SplatException;

public class LexException extends SplatException {

	public LexException(String msg, int line, int column) {
		super(msg, line, column);
	}
	
}
