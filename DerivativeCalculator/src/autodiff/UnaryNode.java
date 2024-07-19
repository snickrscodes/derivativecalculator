package autodiff;

import java.util.HashMap;

public class UnaryNode extends Node {
	public UnaryNode(Node left) {
		super(left);
		this.precedence = 4;
	}
	@Override
	public String toString() {
		return String.format(this.op, left.toString());
	}
	public boolean isConstant() {
		return left.isConstant();
	}
	public boolean isConstant(Variable val) {
		return left.isConstant(val);
	}
	public double evaluate(HashMap<Node, Double> values) {
		return 0.0d;
	}
	public double evaluate(double value) {
		return 0.0d;
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return this;
	}
}
