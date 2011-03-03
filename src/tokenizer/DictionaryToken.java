package tokenizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DictionaryToken extends Token {

	protected List<WordType> types;
	
	public DictionaryToken(String word) {
		this.word = word;
		this.types = new ArrayList<WordType>();
	}
	
	public WordType getType(int pos) {
		if ((pos < 0) || (pos >= types.size())) {
			return null;
		}
		return types.get(pos);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer(word);
		sb.append('{');
		Iterator<WordType> i = types.iterator();
		boolean first = true;
		while (i.hasNext()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(i.next().toString());
		}
		sb.append('}');
		return sb.toString();
	}
	
	public boolean is(String comp) {
		if (comp == null) {
			return false;
		}
		if (comp.startsWith("'")) {
			return (comp.equalsIgnoreCase("'" + word + "'"));
		}
		
		boolean ret = false;
		Iterator<WordType> i = types.iterator();
		while (i.hasNext()) {
			WordType type = i.next();
			boolean t;
			if (t = type.is(comp)) {
				ret = t;
			}
		}
		
		return ret;
	}
	
	protected void addType(WordType wt) {
		types.add(wt);
	}
}
