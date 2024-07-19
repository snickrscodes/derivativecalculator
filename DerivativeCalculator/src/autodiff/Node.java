package autodiff;

import java.util.HashMap;

public class Node {
	public Node left;
	public Node right;
	public String name = "";
	public String op = "";
	public int precedence = 0;
	public Node() {
	}
	public Node(Node left, Node right) {
		this.left = left;
		this.right = right;
	}
	public Node(Node left) {
		this.left = left;
	}
	@Override
	public String toString() {
		return String.format(this.op, left.toString(), right.toString());
	}
	public boolean isConstant(Variable val) {
		return left.isConstant(val) && right.isConstant(val);
	}
	public boolean isConstant() {
		return left.isConstant() && right.isConstant();
	}
	public boolean isInteger() {
		return false;
	}
	public boolean equals(double val) {
		return false;
	}
	public double evaluate(HashMap<Node, Double> values) {
		return 0.0d;
	}
	public double evaluate(double value) {
		return 0.0d;
	}
	public Node differentiate(Variable val) {
		return this;
	}
	public boolean equals(Object a) {
		return this.hashCode() == a.hashCode();
	}
}
