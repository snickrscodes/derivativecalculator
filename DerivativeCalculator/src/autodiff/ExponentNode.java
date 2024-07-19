package autodiff;

import java.util.HashMap;
import autodiff.math.LogNode;

public class ExponentNode extends Node {
	public ExponentNode(Node left, Node right) {
		super(left, right);
		this.precedence = 3;
		op += left.precedence < this.precedence ? "(%s)" : "%s";
		op += "^";
		op += right.precedence < this.precedence ? "(%s)" : "%s";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.pow(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.pow(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		// d/dx [x^n] = n*x^(n-1)
		// d/dx [b^x] = ln(b)*b^x
		// d/dx [f(x)^g(x)] = [f(x)^g(x)]*(g(x)*f'(x)/f(x)+g'(x)*ln(f(x)))
		// blah.evaluate(0) --> gets the constant
		if (left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d); // derivative of a constant is 0
		} else if (right.isConstant(val)) { // power rule
			return new ProductNode(left.differentiate(val), new ProductNode(right, new ExponentNode(left, new SubtractionNode(right, new ConstantNode(1.0d)))));
		} else if (left.isConstant(val)) { // exponential rule
			return new ProductNode(right.differentiate(val), new ProductNode(new ExponentNode(left, right), new LogNode(left)));
		} else { // derivative of f(x)^g(x)
			return new ProductNode(new ExponentNode(left, right), new AdditionNode(new QuotientNode(new ProductNode(right, left.differentiate(val)), left), new ProductNode(right.differentiate(val), new LogNode(left))));
		}
	}
}
