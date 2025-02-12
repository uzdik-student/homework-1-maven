package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;

/**
 * This class represents a label expression
 * <expr> ::= <label>
 *
 * @author Arman Sydikov
 */
public class LabelExpr extends Expression {

    private final String label;

    public LabelExpr(Token tok, String label) {
        super(tok);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "LabelExpr{" +
                "label='" + label + '\'' +
                '}';
    }
}
