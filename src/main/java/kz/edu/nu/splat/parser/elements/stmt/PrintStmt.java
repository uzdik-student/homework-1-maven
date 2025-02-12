package kz.edu.nu.splat.parser.elements.stmt;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.Statement;

/**
 * This class represents a print statement
 * <stmt> ::= print <expr> ;
 *
 * @author Arman Sydikov
 */
public class PrintStmt extends Statement {

	private final Expression expr;

	public PrintStmt(Token tok, Expression expr) {
		super(tok);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

	@Override
	public String toString() {
		return "PrintStmt{" +
				"expr=" + expr +
				'}';
	}
}
