package nodes;


public class NonTerminalNode extends Node {
	
	protected Node child;
	
	protected int start;
	protected int end;

	public NonTerminalNode(String left) {
		this.type = left.toUpperCase();
		child = null;
	}
	
	public void add(Node node) {
		if (child == null) {
			child = node;
			start = node.getStart();
			end = node.getEnd();
		} else {
			if (child instanceof ListNode) {
				ListNode ln = (ListNode) child;
				ln.add(node);
			} else {
				child = new ListNode(child, node);
			}
			end = node.getEnd();
		}
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj.getClass().equals(this.getClass())) {
			NonTerminalNode ntn = (NonTerminalNode) obj;
			if ((ntn.start == this.start) && (ntn.end == this.end) && (ntn.type.equals(this.type))&& (ntn.child.equals(this.child))) {
				return true;
			}
		}
		return false;
	}

	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(type);
		sb.append(" ");
		sb.append(child.toString());
		sb.append(startEnd());
		sb.append("]");
		return sb.toString();
	}

}
