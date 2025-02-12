package kz.edu.nu.splat.parser.elements;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.enums.Type;

/**
 * This class represents a literal
 * <literal> ::= <int-literal> | <bool-literal> | <string-literal>
 *
 * @author Arman Sydikov
 */
public abstract class Literal extends Expression {

	private final Type type;

    public Literal(Token tok, Type type) {
        super(tok);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
