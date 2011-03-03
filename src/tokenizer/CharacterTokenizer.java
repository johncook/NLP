package tokenizer;

import java.io.IOException;
import java.io.Reader;

public class CharacterTokenizer implements Tokenizer {

	Reader in;
	Token token;
	
	public CharacterTokenizer() {
		this.in = null;
		this.token = null;
	}
	
	public CharacterTokenizer(Reader in) {
		setReader(in);
	}

	public void setReader(Reader in) {
		this.in = in;
		token = null;
		getToken();
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

	protected void getToken() {
		if (in == null) {
			token = null;
			return;
		}
		
		int i;
		try {
			i = in.read();
		} catch (IOException e) {
			token = null;
			return;
		}
		if (i == -1) {
			token = null;
			return;
		}
		
		token = new CharacterToken((char)i);
	}
}
