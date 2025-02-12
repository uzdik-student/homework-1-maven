package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Literal;
import kz.edu.nu.splat.parser.elements.enums.Type;

/**
 * This class represents a literal bool expression
 * <expr> ::= <literal>
 * <bool-literal> ::= true | false
 *
 * @author Arman Sydikov
 */
public class LiteralBoolExpr extends Literal {

    private final Boolean value;

    public LiteralBoolExpr(Token tok, Type type, Boolean value) {
        super(tok, type);
        this.value = value;
    }

    @Override
    public String toString() {
        return "LiteralBoolExpr{" +
                "value=" + value +
                '}';
    }
}
