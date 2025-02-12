package kz.edu.nu.splat.parser.elements;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.enums.Type;

public class VariableDecl extends Declaration {
	private final Type type;
	
	// Need to add extra arguments for setting fields in the constructor 
	public VariableDecl(Token tok, String label, Type type) {
		super(tok, label);
		this.type = type;
	}

	// Getters?
	public Type getType() {
		return type;
	}

	// Fix this as well
	@Override
	public String toString() {
		return "VariableDecl{" +
				"type=" + type +
				'}';
	}
}
