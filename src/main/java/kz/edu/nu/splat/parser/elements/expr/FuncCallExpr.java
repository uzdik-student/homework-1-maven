package kz.edu.nu.splat.parser.elements.expr;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;

import java.util.List;

/**
 * This class represents a function call expression
 * <expr> ::= <label> ( <args> )
 *
 * @author Arman Sydikov
 */
public class FuncCallExpr extends Expression {

	private final String label;
    private final List<Expression> arguments;

	public FuncCallExpr(Token tok, String label, List<Expression> arguments) {
		super(tok);
        this.label = label;
        this.arguments = arguments;
    }

    public String getLabel() {
        return label;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "FuncCallExpr{" +
                "label='" + label + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
