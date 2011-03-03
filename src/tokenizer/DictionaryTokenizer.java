package tokenizer;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DictionaryTokenizer implements Tokenizer {

	Tokenizer in;
	Token token;
	Map<String, DictionaryToken> dictionary;
	
	public DictionaryTokenizer() {
		this.in = new WordTokenizer();
		this.token = null;
		this.dictionary = new TreeMap<String, DictionaryToken>();
		
		//this.addWord(".", WordType.PUNCTUATION, '.');
	}
	
	public DictionaryTokenizer(Reader in) {
		this();
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
		this.in.setReader(in);
		this.token = null;
		getToken();
	}
	
	public void getToken() {
		token = null;
		if (in == null) {
			return;
		}
		
		Token t = in.next();
		if (t == null) return;
		//token = t;
		
		in.consume();
		token = dictionary.get(t.toString().toLowerCase());
		if (token == null) {
			int type = (t instanceof WordToken) ? ((WordToken)t).getType() : -1;
			if (type >= 0)
				token = addWord(t.toString(), WordType.PUNCTUATION, type);
			else
				token = addWord(t.toString(), 0, 0);
		}
	}
	
	public DictionaryToken addWord(String word, int type) {
		return addWord(word, (short)type, 0);
	}
	
	public DictionaryToken addWord(String word, short type) {
		return addWord(word, type, 0);
	}
	
	public DictionaryToken addWord(String word, int type, int sub_type) {
		return addWord(word, (short)type, (short)sub_type);
	}
	
	public DictionaryToken addWord(String word, short type, short sub_type) {
		DictionaryToken t = dictionary.get(word.toLowerCase());
		if (t == null) {
			t = new DictionaryToken(word.toLowerCase());
			dictionary.put(word.toLowerCase(), t);
		}
		t.addType(WordType.get(type, sub_type));
		return t;
	}
	
	public void showDictionary(Writer out) {
		PrintWriter p = new PrintWriter(out);
		Set<String> keys = dictionary.keySet();
		Iterator<String> i = keys.iterator();
		while (i.hasNext()) {
			p.println("DT: " + dictionary.get(i.next()));
		}
		p.flush();
	}
}
