package tokenizer;

public abstract class Token {
	
	String word;
	
	abstract boolean is(String comp);
	
	public String getText() {
		return word;
	}
	
}
