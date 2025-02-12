package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Literal;
import kz.edu.nu.splat.parser.elements.enums.Type;

/**
 * This class represents a literal string expression
 * <expr> ::= <literal>
 * <string-literal> ::= "…sequence of characters and space that do not contain double-quotes, backslashes, or newlines... "
 *
 * @author Arman Sydikov
 */
public class LiteralStringExpr extends Literal {

    private final String value;

    public LiteralStringExpr(Token tok, Type type, String value) {
        super(tok, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return "LiteralStringExpr{" +
                "value='" + value + '\'' +
                '}';
    }
}
