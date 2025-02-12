package kz.edu.nu.splat.parser.elements;

import kz.edu.nu.splat.lexer.Token;
import kz.edu.nu.splat.parser.elements.enums.Type;

import java.util.List;

public class FunctionDecl extends Declaration {

	/**
	 * For storing the return type of the current function
	 * by using an illegal label name as the key, e.g., “0result”
	 * this will prevent any possibilities of name clashes
	 * with any already existing variable or parameter names
	 */
	public static final String FUNC_LABEL = "0result";

	// Need to add some fields
	private final List<Param> params;
	private final Type retType;
	private final List<VariableDecl> localVarDecls;
	private final List<Statement> stmts;

	// Need to add extra arguments for setting fields in the constructor 
	public FunctionDecl(Token tok, String label, List<Param> params, Type retType, List<VariableDecl> localVarDecls, List<Statement> stmts) {
		super(tok, label);
        this.params = params;
        this.retType = retType;
        this.localVarDecls = localVarDecls;
        this.stmts = stmts;
    }

	// Getters?
	public Type getRetType() {
		return retType;
	}

	public List<VariableDecl> getLocalVarDecls() {
		return localVarDecls;
	}

	public List<Statement> getStmts() {
		return stmts;
	}

	public List<Param> getParams() {
		return params;
	}

	// Fix this as well
	@Override
	public String toString() {
		return "FunctionDecl{" +
				"params=" + params +
				", retType=" + retType +
				", locVarDecls=" + localVarDecls +
				", stmts=" + stmts +
				'}';
	}
}
