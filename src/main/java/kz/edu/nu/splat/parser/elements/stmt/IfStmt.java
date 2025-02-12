package kz.edu.nu.splat.parser.elements.stmt;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.Statement;

import java.util.List;

/**
 * This class represents an if statement
 * <stmt> ::= if <expr> then <stmts> else <stmts> end if ;
 *          | if <expr> then <stmts> end if ;
 *
 * @author Arman Sydikov
 */
public class IfStmt extends Statement {

	private final Expression condition;
	private final List<Statement> thenStmts;
	private final List<Statement> elseStmts;

	public IfStmt(Token tok, Expression condition, List<Statement> thenStmts, List<Statement> elseStmts) {
		super(tok);
        this.condition = condition;
        this.thenStmts = thenStmts;
        this.elseStmts = elseStmts;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getThenStmts() {
        return thenStmts;
    }

    public List<Statement> getElseStmts() {
        return elseStmts;
    }

	@Override
	public String toString() {
		return "IfStmt{" +
				"condition=" + condition +
				", thenStmts=" + thenStmts +
				", elseStmts=" + elseStmts +
				'}';
	}
}
