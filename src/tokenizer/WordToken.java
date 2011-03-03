package tokenizer;

public class WordToken extends Token {

	private int type;

	public WordToken(String word) {
		this.word = word;
		type = -1;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		System.out.println(this + " " + type);
		return type;
	}
	
	public boolean is(String comp) {
		if (comp.startsWith("'")) {
			return (comp.equalsIgnoreCase("'" + word + "'"));
		}
		if (comp.equalsIgnoreCase("word")) {
			return true;
		}
		return false;
	}

	public String toString() {
		return word;
	}
}
