package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.enums.BinaryOp;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a binary expression
 * <expr> ::= ( <expr> <bin-op> <expr> )
 *
 * @author Arman Sydikov
 */
public class BinaryExpr extends Expression {

    private final Expression left;
    private final BinaryOp operator;
    private final Expression right;

    private final List<BinaryOp> logicalOperators = Arrays.asList(
            BinaryOp.AND,
            BinaryOp.OR
    );

    private final List<BinaryOp> comparisonOperators = Arrays.asList(
            BinaryOp.GREATER_THAN,
            BinaryOp.GREATER_THAN_OR_EQUAL_TO,
            BinaryOp.LESS_THAN,
            BinaryOp.LESS_THAN_OR_EQUAL_TO
    );

    private final List<BinaryOp> arithmeticOperators = Arrays.asList(
            BinaryOp.ADDITION,
            BinaryOp.SUBTRACTION,
            BinaryOp.MULTIPLICATION,
            BinaryOp.DIVISION,
            BinaryOp.MODULUS
    );

    public BinaryExpr(Token tok, Expression left, BinaryOp operator, Expression right) {
        super(tok);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public BinaryOp getOperator() {
        return operator;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "BinaryExpr{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                '}';
    }
}
