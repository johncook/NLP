package parser;

import java.io.Reader;
import java.io.StringReader;

import tokenizer.CharacterTokenizer;
import tokenizer.Token;
import tokenizer.Tokenizer;

public class Parser {
	
	Tokenizer tokenizer;
	Token lastToken = null;
	
	public Parser() {
		this(new CharacterTokenizer());
	}
	
	public Parser(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}
	
	public void parse(String s) {
		Reader reader = new StringReader(s);
		parse(reader);
	}
	
	public void parse(Reader reader) {
		tokenizer.setReader(reader);
		Token tok;
		
		while (tokenizer.hasMore()) {
			tok = tokenizer.next();
			tokenizer.consume();
			
			System.out.println(":>" + tok);
		}
	}
}
