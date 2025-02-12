package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Literal;
import kz.edu.nu.splat.parser.elements.enums.Type;

/**
 * This class represents a literal int expression
 * <expr> ::= <literal>
 * <int-literal> ::= …sequence of decimal digits...
 *
 * @author Arman Sydikov
 */
public class LiteralIntExpr extends Literal {

    private final Integer value;

    public LiteralIntExpr(Token tok, Type type, Integer value) {
        super(tok, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return "LiteralIntExpr{" +
                "value=" + value +
                '}';
    }
}
