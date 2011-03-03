package parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class Rule {

	protected String left;
	protected String[] right;
	
	private Rule() { }
	
	public Rule(String rule) {
		this();
		parseRule(rule);
	}
	
	protected void parseRule(String rule) {
		StringReader sr = new StringReader(rule);
		ArrayList<String> al = new ArrayList<String>();
		left = getToken(sr);
		
		String s1 = getToken(sr);
		if (!s1.equals("::=")) return;
		do {
			s1 = getToken(sr);
			if (s1 != null) {
				al.add(s1);
			}
		} while (s1 != null);
		right = al.toArray(new String[0]);
	}
	
	protected String getToken(StringReader sr) {
		try {
			StringBuffer sb = new StringBuffer();
			int c = sr.read();
			while (Character.isWhitespace(c)) {
				c = sr.read();
			}
			if (c != -1) {
				while (!Character.isWhitespace(c) && (c != -1)) {
					sb.append((char)c);
					c = sr.read();
				}
				return sb.toString();
			}
		} catch (IOException e) {
			return null;
		}
		return null;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(left);
		sb.append(" ::=");
		if (right == null) {
			sb.append(" ");
			sb.append(right);
		} else {
			for (int i = 0; i < right.length; i++) {
				sb.append(" ");
				sb.append(right[i]);
			}
		}
		return sb.toString();
	}
	
	public String getLeft() {
		return left;
	}
	
	public String getRight(int pos) {
		if ((pos >= 0) && (pos < right.length)) {
			return right[pos];
		} else {
			return null;
		}
	}
	
	public int length() {
		if (right == null) return 0;
		return right.length;
	}
}
