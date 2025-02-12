package kz.edu.nu.splat.parser.elements.stmt;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.Statement;

/**
 * This class represents a return statement
 * <stmt> ::= return <expr> ; | return ;
 *
 * @author Arman Sydikov
 */
public class ReturnStmt extends Statement {

	private final Expression expr;

	public ReturnStmt(Token tok, Expression expr) {
		super(tok);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

	@Override
	public String toString() {
		return "ReturnStmt{" +
				"expr=" + expr +
				'}';
	}
}
