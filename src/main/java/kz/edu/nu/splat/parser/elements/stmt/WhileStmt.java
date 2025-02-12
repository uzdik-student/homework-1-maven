package kz.edu.nu.splat.parser.elements.stmt;


import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.Statement;

import java.util.List;

/**
 * This class represents a while statement
 * <stmt> ::= while <expr> do <stmts> end while ;
 *
 * @author Arman Sydikov
 */
public class WhileStmt extends Statement {

	private final Expression condition;
	private final List<Statement> stmts;

	public WhileStmt(Token tok, Expression condition, List<Statement> stmts) {
		super(tok);
        this.condition = condition;
        this.stmts = stmts;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Statement> getStmts() {
        return stmts;
    }

	@Override
	public String toString() {
		return "WhileStmt{" +
				"condition=" + condition +
				", stmts=" + stmts +
				'}';
	}
}
