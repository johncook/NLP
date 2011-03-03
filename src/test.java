import parser.DictionaryParser;
import parser.Rule;
import tokenizer.DictionaryTokenizer;
import tokenizer.Tokenizer;
import tokenizer.WordType;

public class test {
	
	public static void main(String[] args) {
		
		Tokenizer tokenizer = new DictionaryTokenizer();
		
		DictionaryTokenizer dt = (DictionaryTokenizer)tokenizer;
		dt.addWord("a", WordType.ARTICLE, WordType.SINGULAR);
		dt.addWord("an", WordType.ARTICLE, WordType.SINGULAR);
		dt.addWord("the", WordType.ARTICLE, WordType.SINGULAR);
		dt.addWord("this", WordType.PRONOUN, WordType.SINGULAR);
		dt.addWord("this", WordType.ADJECTIVE, WordType.SINGULAR);
		dt.addWord("this", WordType.ADVERB);
		dt.addWord("string", WordType.NOUN, WordType.SINGULAR);
		dt.addWord("is", WordType.VERB_TRANSITIVE, WordType.THIRD_PERSON);
		dt.addWord("short", WordType.ADJECTIVE);
		dt.addWord("very", WordType.ADJECTIVE);
		dt.addWord("fall", WordType.ADJECTIVE);
		dt.addWord("fall", WordType.NOUN);
		dt.addWord("fall", WordType.VERB_TRANSITIVE);
		dt.addWord("fall", WordType.VERB_INTRANSITIVE);
		dt.addWord("leaves", WordType.NOUN, WordType.PLURAL);
		dt.addWord("leaves", WordType.VERB_INTRANSITIVE, 0);
		dt.addWord("leaves", WordType.VERB_TRANSITIVE, 0);
		dt.addWord("and", WordType.CONJUNCTION);
		dt.addWord("spring", WordType.ADJECTIVE);
		dt.addWord("spring", WordType.NOUN);
		dt.addWord("spring", WordType.VERB_TRANSITIVE);
		dt.addWord("spring", WordType.VERB_INTRANSITIVE);

		DictionaryParser parser = new DictionaryParser(tokenizer);
		
		parser.add(new Rule("NP ::= art NP"));
		parser.add(new Rule("NP ::= noun"));
		parser.add(new Rule("NP ::= pron"));
		parser.add(new Rule("NP ::= adj NP"));
		parser.add(new Rule("NP ::= LIST"));
		parser.add(new Rule("VP ::= v.tran NP"));
		parser.add(new Rule("VP ::= v.int"));
		parser.add(new Rule("LIST ::= NP PUN|, conj NP"));
		parser.add(new Rule("LIST ::= NP PUN|, NP"));
		parser.add(new Rule("SP ::= NP VP"));
		parser.add(new Rule("SP ::= SP conj SP"));
		parser.add(new Rule("S ::= SP PUN|."));

		parser.parse("This is an very short string.");
		
		//parser.parse("Fall leaves fall and spring leaves spring.");

		//parser.parse("In linguistics, grammar is the set of structural rules that govern the composition of sentences, phrases, and words in any given natural language.");
		
		//parser.parse("can't");
		//parser.showRules(new PrintWriter(System.out));
		//((DictionaryTokenizer) tokenizer).showDictionary(new PrintWriter(System.out));
	}

}
