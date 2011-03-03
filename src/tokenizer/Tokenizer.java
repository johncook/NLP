package tokenizer;

import java.io.Reader;

public interface Tokenizer {

	public boolean hasMore();
	
	public Token next();
	
	public void consume();
	
	public void setReader(Reader in);
	
}
