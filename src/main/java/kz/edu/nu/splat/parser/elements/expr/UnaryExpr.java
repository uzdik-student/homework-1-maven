package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.enums.UnaryOp;

/**
 * This class represents a unary expression
 * <expr> ::= ( <unary-op> <expr> )
 *
 * @author Arman Sydikov
 */
public class UnaryExpr extends Expression {

    private final UnaryOp operator;
    private final Expression expr;

    public UnaryExpr(Token tok, UnaryOp operator, Expression expr) {
        super(tok);
        this.operator = operator;
        this.expr = expr;
    }

    public UnaryOp getOperator() {
        return operator;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public String toString() {
        return "UnaryExpr{" +
                "operator=" + operator +
                ", expr=" + expr +
                '}';
    }
}
