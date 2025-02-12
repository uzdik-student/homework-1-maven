package kz.edu.nu.splat.parser.elements;

import kz.edu.nu.splat.lexer.Token;

import java.util.List;

public class ProgramAST extends ASTElement {

	private List<Declaration> decls;
	private List<Statement> stmts;
	
	public ProgramAST(List<Declaration> decls,
					  List<Statement> stmts,
					  Token tok) {
		
		super(tok);
		this.decls = decls;
		this.stmts = stmts;
	}

	public List<Declaration> getDecls() {
		return decls;
	}
	
	public List<Statement> getStmts() {
		return stmts;
	}
	
	public String toString() {
		String result = "program \n";
		for (Declaration decl : decls) {
			result = result + "   " + decl + "\n";
		}
		result = result + "begin \n";
		for (Statement stmt : stmts) {
			result = result + "   " + stmt + "\n";
		}
		result = result	+ "end;";
		
		return result;
	}
}
