package kz.edu.nu.splat.parser.elements;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.enums.Type;

/**
 * This class represents a parameter
 * <param> ::= <label> : <type>
 *
 * @author Arman Sydikov
 */
public class Param extends VariableDecl {

	public Param(Token tok, String label, Type type) {
		super(tok, label, type);
	}

	@Override
	public String toString() {
		return "Param{}";
	}
}
