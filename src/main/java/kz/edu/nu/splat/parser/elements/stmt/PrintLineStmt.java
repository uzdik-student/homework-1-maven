package kz.edu.nu.splat.parser.elements.stmt;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Statement;

/**
 * This class represents a print line statement
 * <stmt> ::= print_line ;
 *
 * @author Arman Sydikov
 */
public class PrintLineStmt extends Statement {

	public PrintLineStmt(Token tok) {
		super(tok);
    }

	@Override
	public String toString() {
		return "PrintLineStmt{}";
	}
}
