package kz.edu.nu.splat.parser.elements.enums;

/**
 * This class represents a binary operator
 * <bin-op> ::= and | or | > | < | == | >= | <= | + | - | * | / | %
 *
 * @author Arman Sydikov
 */
public enum BinaryOp {
	AND,
	OR,
	GREATER_THAN,
	LESS_THAN,
	EQUALITY,
	GREATER_THAN_OR_EQUAL_TO,
	LESS_THAN_OR_EQUAL_TO,
	ADDITION,
	SUBTRACTION,
	MULTIPLICATION,
	DIVISION,
	MODULUS
}
