package autodiff;

import java.util.HashMap;

public class ProductNode extends Node {
	public ProductNode(Node left, Node right) {
		super(left, right);
		this.precedence = 2;
		op += left.precedence < this.precedence ? "(%s)" : "%s";
		op += "*";
		op += right.precedence < this.precedence ? "(%s)" : "%s";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return left.evaluate(values) * right.evaluate(values);
	}
	public double evaluate(double value) {
		return left.evaluate(value) * right.evaluate(value);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			// d/dx [c*f(x)] = c*f'(x)
			return new ProductNode(left, right.differentiate(val));
		} else if(right.isConstant(val)) {
			return new ProductNode(right, left.differentiate(val));
		} else {
			// f'(x)*g(x) + g'(x)*f(x)
			return new AdditionNode(new ProductNode(left.differentiate(val), right), new ProductNode(right.differentiate(val), left));
		}
	}
}
