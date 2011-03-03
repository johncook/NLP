package tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class WordTokenizer implements Tokenizer {
	
	StreamTokenizer in;
	Token token;
	
	public WordTokenizer() {
		this.in = null;
		this.token = null;
	}
	
	public WordTokenizer(Reader in) {
		setReader(in);
	}
	
	public void consume() {
		getToken();
	}
	
	public boolean hasMore() {
		return !(token == null);
	}
	
	public Token next() {
		return token;
	}
	
	public void setReader(Reader in) {
		this.in = new StreamTokenizer(new BufferedReader(in));
		this.in.eolIsSignificant(false);
		this.in.slashSlashComments(false);
		this.in.slashStarComments(false);
		this.in.ordinaryChars('-', '.');
		this.in.ordinaryChars('0', '9');
		this.in.wordChars('0', '9');
		this.in.ordinaryChar('\'');
		this.in.wordChars('\'', '\'');
		token = null;
		getToken();
	}
	
	protected void getToken() {
		if (in == null) {
			token = null;
			return;
		}
		
		try {
			int type = in.nextToken();
			switch (type) {
			case StreamTokenizer.TT_EOF:
				token = null;
				return;
			case StreamTokenizer.TT_WORD:
				token = new WordToken(in.sval);
				return;
			case StreamTokenizer.TT_NUMBER:
				token = new WordToken(Double.toString(in.nval));
				return;
			default:
				token = new WordToken(Character.toString((char)type));
				((WordToken) token).setType(type);
				return;
			}
		} catch (IOException e) {
			token = null;
			return;
		}
	}
}
