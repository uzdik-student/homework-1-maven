package kz.edu.nu.splat.parser.elements.stmt;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.Expression;
import kz.edu.nu.splat.parser.elements.Statement;

import java.util.List;

/**
 * This class represents a function call statement
 * <stmt> ::= <label> ( <args> ) ;
 *
 * @author Arman Sydikov
 */
public class FuncCallStmt extends Statement {

	private final String label;
    private final List<Expression> arguments;

	public FuncCallStmt(Token tok, String label, List<Expression> arguments) {
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
        return "FuncCallStmt{" +
                "label='" + label + '\'' +
                ", arguments=" + arguments +
                '}';
    }
}
