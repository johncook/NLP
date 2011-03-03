package nodes;

import java.util.ArrayList;


public class ListNode extends Node {

	protected ArrayList<Node> children;
	
	protected int start;
	protected int end;
	
	public ListNode(Node node1, Node node2) {
		children = null;
		add(node1);
		add(node2);
	}
	
	public void add(Node node) {
		if (children == null) {
			children = new ArrayList<Node>();
			start = node.getStart();
		}
		children.add(node);
		end = node.getEnd();
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj.getClass().equals(this.getClass())) {
			ListNode ln = (ListNode)obj;
			if ((ln.start == this.start) && (ln.end == this.end)) {
				if (ln.children.size() == this.children.size()) {
					for (int i = 0; i < children.size(); i++) {
						if (!ln.children.get(i).equals(this.children.get(i))) {
							return false;
						}
					}
					return true;
				}
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
		for (int i = 0; i < children.size(); i++) {
			sb.append(children.get(i).toString());
		}
		return sb.toString();
	}

}
