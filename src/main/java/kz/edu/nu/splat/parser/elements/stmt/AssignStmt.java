package kz.edu.nu.splat.parser.elements.stmt;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.Statement;

/**
 * This class represents an assignment statement
 * <stmt> ::= <label> := <expr> ;
 *
 * @author Arman Sydikov
 */
public class AssignStmt extends Statement {

	private final String label;
	private final Expression expr;

	public AssignStmt(Token tok, String label, Expression expr) {
		super(tok);
        this.label = label;
        this.expr = expr;
    }

    public String getLabel() {
        return label;
    }

    public Expression getExpr() {
        return expr;
    }

	@Override
	public String toString() {
		return "AssignStmt{" +
				"label='" + label + '\'' +
				", expr=" + expr +
				'}';
	}

}
