package tokenizer;

public class CharacterToken extends Token {
	
	char text;
	
	public CharacterToken(char c) {
		text = c;
	}

	public String getText() {
		return "" + text;
	}
	
	public String toString() {
		return "" + text;
	}

	public boolean is(String comp) {
		if (comp.startsWith("'")) {
			return (comp.equals("'" + text + "'"));
		}
		if (comp.equalsIgnoreCase("letter")) {
			return Character.isLetter(text);
		}
		if (comp.equalsIgnoreCase("digit")) {
			return Character.isDigit(text);
		}
		if (comp.equalsIgnoreCase("whitespace")) {
			return Character.isWhitespace(text);
		}
		if (comp.equalsIgnoreCase("symbol")) {
			return (!Character.isLetterOrDigit(text)) && (!Character.isWhitespace(text));
		}
		return false;
	}

}
