package kz.edu.nu.splat.lexer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for lexical analysis
 * @author Arman Sydikov
 */
public class Lexer {

	// Regular expressions for tokens
	private final Pattern[] patterns = {
			// reserved keywords
			Pattern.compile("\\b(program|begin|end|is|while|do|if|then|else|print|print_line|return|and|or|not|void|Integer|Boolean|String|true|false)\\b"),
			// <label>
			Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*"),
			// <int-literal>
			Pattern.compile("\\d+"),
			// <string-literal>
			Pattern.compile("\"[^\"]*\""),
			Pattern.compile("\\s+"),
			Pattern.compile("\\("),
			Pattern.compile("\\)"),
			// <bin-op>
			Pattern.compile("=="),
			Pattern.compile(":="),
			Pattern.compile("<="),
			Pattern.compile("<"),
			Pattern.compile(">="),
			Pattern.compile(">"),
			Pattern.compile(";"),
			Pattern.compile(":"),
			Pattern.compile(","),
			Pattern.compile("\\+"),
			Pattern.compile("-"),
			Pattern.compile("/"),
			Pattern.compile("\\*"),
			Pattern.compile("%"),
	};

	private final File progFile;

	public Lexer(File progFile) {
		this.progFile = progFile;
	}

	public List<Token> tokenize() throws LexException, IOException {
		List<Token> tokens = new ArrayList<>();

		// Open *.splat file
		try (BufferedReader reader = new BufferedReader(new FileReader(progFile))) {

			// Current line number
			int lineNumber = 1;

			// Read file line by line
			String line;
			while ((line = reader.readLine()) != null) {
				// Get tokens from line
				tokens.addAll(getTokens(line, lineNumber++));
			}
		}

		return tokens;
	}

	public List<Token> tokenize(String sourceCode) throws LexException, IOException {
		List<Token> tokens = new ArrayList<>();

		// Open *.splat file
		try (BufferedReader reader = new BufferedReader(new StringReader(sourceCode))) {

			// Current line number
			int lineNumber = 1;

			// Read file line by line
			String line;
			while ((line = reader.readLine()) != null) {
				// Get tokens from line
				tokens.addAll(getTokens(line, lineNumber++));
			}
		}

		return tokens;
	}

	private List<Token> getTokens(String line, int lineNumber) throws LexException {
		List<Token> tokens = new ArrayList<>();

		// Initially put cursor at the beginning of the line
		int cursor = 0;

		// Iterate over the line
		while (cursor < line.length()) {
			// Initially no pattern was matched
			boolean matched = false;

			// Get remainder starting from cursor position
			String remainder = line.substring(cursor);

			// Iterate over all patterns to find a match
			for (Pattern pattern : patterns) {
				Matcher matcher = pattern.matcher(remainder);

				// We found a match which starts from the current cursor position
				if (matcher.find() && matcher.start() == 0) {
					// At least one pattern matched
					matched = true;

					String found = matcher.group();
					if (!found.trim().isEmpty()) {
						tokens.add(new Token(found, lineNumber, cursor+1));
					}
					cursor += found.length();
					break;
				}
			}

			// If no tokens have been found
			if (!matched) {
				throw new LexException("Illegal token", lineNumber, cursor+1);
			}
		}

		return tokens;
	}
}
