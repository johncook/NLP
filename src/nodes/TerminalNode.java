package nodes;


public class TerminalNode extends Node {

	protected String text;
	
	protected int pos;
	
	public TerminalNode(String type, String text, int pos) {
		this.type = type.toUpperCase();
		this.text = text;
		this.pos = pos;
	}
	
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		
		if (obj.getClass().equals(this.getClass())) {
			TerminalNode tn = (TerminalNode) obj;
			if ((tn.pos == this.pos) && (tn.type.equals(this.type)) && (tn.text.equals(this.text))) {
				return true;
			}
		}
		return false;
	}

	public int getEnd() {
		return pos;
	}

	public int getStart() {
		return pos;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(type);
		sb.append(" ");
		sb.append(text);
		sb.append(startEnd());
		sb.append("]");
		return sb.toString();
	}

}
