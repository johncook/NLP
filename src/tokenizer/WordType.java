package tokenizer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WordType {
	
	public static final short ADJECTIVE			= 0x0001;
	public static final short ADVERB 			= 0x0002;
	public static final short ARTICLE			= 0x0004;
	public static final short AUX_VERB			= 0x0008;
	public static final short CONJUNCTION		= 0x0010;
	public static final short NOUN				= 0x0020;
	public static final short PREPOSITION		= 0x0040;
	public static final short PRONOUN			= 0x0080;
	public static final short PROPPER_NOUN		= 0x0100;
	public static final short VERB_INTRANSITIVE	= 0x0200;
	public static final short VERB_TRANSITIVE	= 0x0400;
	public static final short PUNCTUATION		= 0x4000;
	
	public static final short UNCOUNTABLE		= 0x000;
	public static final short SINGULAR			= 0x001;
	public static final short PLURAL			= 0x002;
	
	public static final short NEUTER			= 0x000;
	public static final short MASCULINE			= 0x004;
	public static final short FEMININE			= 0x008;
	
	public static final short FIRST_PERSON		= 0x000;
	public static final short SECOND_PERSON		= 0x010;
	public static final short THIRD_PERSON		= 0x020;
	
	//SUBJECT / OBJECT
	
	public static final short PRESENT			= 0x000;
	public static final short PAST				= 0x100;
	public static final short FUTURE			= 0x200;
	
	private static Set<WordType> wordTypes = new HashSet<WordType>();
	
	protected short type;
	protected short sub_type;
	
	private WordType(short type, short sub_type) {
		this.type = type;
		this.sub_type = sub_type;
	}
	
	public static WordType get(short type, short sub_type) {
		WordType wt = null;
		Iterator<WordType> i = wordTypes.iterator();
		while (i.hasNext()) {
			wt = i.next();
			if (wt.type == type && wt.sub_type == sub_type) {
				return wt;
			}
		}
		wt = new WordType(type, sub_type);
		wordTypes.add(wt);
		return wt;
	}
	
	public String toString() {
		switch (type) {
		case (ADJECTIVE):
			return "ADJ";
		case (ADVERB):
			return "ADV";
		case (ARTICLE):
			return "ART";
		case (CONJUNCTION):
			return "CONJ";
		case (NOUN):
			return "NOUN";
		case (PREPOSITION):
			return "PREP";
		case (PRONOUN):
			return "PRON";
		case (PROPPER_NOUN):
			return "PROPN";
		case (VERB_INTRANSITIVE):
			return "V.INT";
		case (VERB_TRANSITIVE):
			return "V.TRAN";
		case (PUNCTUATION):
			return "PUN|" + (char)sub_type;
		default:
			return "0x" + Integer.toHexString(type) + ":0x" + Integer.toHexString(sub_type);
		}
	}
	
	public boolean is(String comp) {
		if (this.toString().equals(comp.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
}
