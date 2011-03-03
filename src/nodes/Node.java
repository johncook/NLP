package nodes;

public abstract class Node {
	
	protected String type;
	
	public String getType() {
		return type;
	}
	
	public abstract String toString();
	
	public abstract int getStart();
	
	public abstract int getEnd();
	
	public abstract boolean equals(Object obj);
	
	public String startEnd() {
		boolean printIt = true;
		if (printIt) {
			StringBuffer sb = new StringBuffer();
			sb.append(" (");
			sb.append(getStart());
			sb.append("-");
			sb.append(getEnd());
			sb.append(")");
			return sb.toString();
		} else {
			return "";
		}
	}

}
