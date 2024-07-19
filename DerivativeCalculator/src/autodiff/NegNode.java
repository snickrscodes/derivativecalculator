package autodiff;

import java.util.HashMap;

public class NegNode extends UnaryNode {
	public NegNode(Node left) {
		super(left);
		this.op = "-";
		op += left.precedence < this.precedence ? "(%s)" : "%s";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.abs(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.abs(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new NegNode(left.differentiate(val));
	}
}
