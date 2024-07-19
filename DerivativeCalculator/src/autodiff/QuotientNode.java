package autodiff;

import java.util.HashMap;

public class QuotientNode extends Node {
	public QuotientNode(Node left, Node right) {
		super(left, right);
		this.precedence = 2;
		op += left.precedence < this.precedence ? "(%s)" : "%s";
		op += "/";
		op += right.precedence <= this.precedence ? "(%s)" : "%s";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return left.evaluate(values) / right.evaluate(values);
	}
	public double evaluate(double value) {
		return left.evaluate(value) / right.evaluate(value);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(left.isConstant(val)) {
			// d/dx [a/f(x)] = -a*f'(x)/[f(x)]^2
			return new QuotientNode(new ProductNode(new NegNode(left), right.differentiate(val)), new ExponentNode(right, new ConstantNode(2.0d)));
		} else if(right.isConstant(val)) {
			// d/dx [f(x)/a] = f'(x)/a
			return new QuotientNode(left.differentiate(val), right);
		} else {
			// d/dx [f(x)/g(x)] = (g(x)*f'(x) - f(x)*g'(x))/[g(x)]^2
			return new QuotientNode(new SubtractionNode(new ProductNode(right, left.differentiate(val)), new ProductNode(left, right.differentiate(val))), new ExponentNode(right, new ConstantNode(2.0d)));
		}
	}
}
