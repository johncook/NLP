package parser;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nodes.Node;
import nodes.NonTerminalNode;
import nodes.TerminalNode;

import tokenizer.DictionaryToken;
import tokenizer.Token;
import tokenizer.Tokenizer;
import tokenizer.WordType;

public class DictionaryParser {
	
	ArrayList<Rule> rules;
	
	Tokenizer tokenizer;
	Token lastToken = null;
	
	private DictionaryParser() {
		this.rules = new ArrayList<Rule>();
	}
	
	public DictionaryParser(Tokenizer tokenizer) {
		this();
		this.tokenizer = tokenizer;
	}
	
	public void add(Rule rule) {
		rules.add(rule);
	}
	
	public void showRules(Writer out) {
		PrintWriter p = new PrintWriter(out);
		for (int i = 0; i < rules.size(); i++) {
			p.println((i + 1) + ": " + rules.get(i));
		}
		p.flush();
	}
	
	public Node parse(String s) {
		Reader reader = new StringReader(s);
		return parse(reader);
	}
	
	public Node parse(Reader reader) {
		tokenizer.setReader(reader);
		Token tok;
		List<Node> allNodes = new ArrayList<Node>();
		List<Node> nodes;
		int pos = 0;
		
		while (tokenizer.hasMore()) {
			tok = tokenizer.next();
			tokenizer.consume();
			nodes = tokToNodes(tok, pos);
			allNodes.addAll(nodes);
			
			findNodes(allNodes, pos);
			
			System.out.println(":>");
			show(System.out, allNodes, pos);
			
			pos++;
		}
		
		return null;
	}
	
	protected List<Node> tokToNodes(Token token, int pos) {
		if (!(token instanceof DictionaryToken)) {
			return null;
		}
		Node node;
		List<Node> nodes = new ArrayList<Node>();
		DictionaryToken dt = (DictionaryToken) token;
		WordType wt;
		int i = 0;
		do {
			wt = dt.getType(i);
			if (wt != null) {
				node = new TerminalNode(wt.toString(), dt.getText(), pos);
				nodes.add(node);
				i++;
			}
		} while (wt != null);
		
		return nodes;
	}
	
	protected void findNodes(List<Node> nodes, int endPos) {
		boolean ret;
		do {
			ret = false;
			for (int i = rules.size() - 1; i >= 0; i--) {
				Rule rule = rules.get(i);
				boolean r = checkRule(rule, rule.length() - 1, nodes, endPos, new ArrayList<Node>());
				if (r == true) ret = true;
			}
		} while (ret == true);
	}
	
	protected boolean checkRule(Rule rule, int rulePos, List<Node> nodes, int endPos, ArrayList<Node> list) {
		ArrayList<Node> parts = (ArrayList<Node>) list.clone();
		String rulePart = rule.getRight(rulePos);
		boolean ret = false;
				
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);

			if (node.getType().equalsIgnoreCase(rulePart)) {
				if (node.getEnd() == endPos) {
					parts.add(node);
					if (rulePos == 0) {
						NonTerminalNode ntn = new NonTerminalNode(rule.getLeft());
						for (int j = parts.size() - 1; j >= 0; j--) {
							ntn.add(parts.get(j));
						}
						boolean r = addNode(nodes, ntn);
						if (r == true) ret = true;
					} else {
						boolean r = checkRule(rule, rulePos - 1, nodes, node.getStart() - 1, parts);
						if (r == true) ret = true;
					}
					parts.remove(node);
				}
			}
		}
		return ret;
	}
	
	protected boolean addNode(List<Node> nodes, Node node) {
		Iterator<Node> iter = nodes.iterator();
		while (iter.hasNext()) {
			Node n = iter.next();
			if (n.equals(node)) {
				return false;
			}
		}
		nodes.add(node);
		return true;
	}
	
	protected void show(OutputStream outputStream, List<Node> list, int pos) {
		show(new PrintWriter(outputStream), list, pos);
	}
	
	protected void show(Writer writer, List<Node> list, int pos) {
		PrintWriter out = writer instanceof PrintWriter ? (PrintWriter) writer : new PrintWriter(writer);
		Iterator<Node> iter = list.iterator();
		while (iter.hasNext()) {
			Node node = iter.next();
			//if ((node.getStart() == 0) && (node.getEnd() == pos))
					out.println(node);
		}
		out.println("-------------------------------------------------------");
		out.flush();
	}
}
