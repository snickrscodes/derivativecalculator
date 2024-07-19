package autodiff;

import java.util.HashMap;

public class AdditionNode extends Node {
	public AdditionNode(Node left, Node right) {
		super(left, right);
		this.precedence = 1;
		op += left.precedence < this.precedence ? "(%s)" : "%s";
		op += "+";
		op += right.precedence < this.precedence ? "(%s)" : "%s";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return left.evaluate(values) + right.evaluate(values);
	}
	public double evaluate(double value) {
		return left.evaluate(value) + right.evaluate(value);
	}
	public Node differentiate(Variable val) {
		// f'(x) + g'(x)
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return right.differentiate(val);
		} else if (right.isConstant(val)) {
			return left.differentiate(val);
		} else {
			return new AdditionNode(left.differentiate(val), right.differentiate(val));
		}
	}
}
