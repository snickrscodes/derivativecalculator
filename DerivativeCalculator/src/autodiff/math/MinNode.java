package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class MinNode extends Node {
	public MinNode(Node left, Node right) {
		super(left, right);
		this.op = "min(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.min(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.min(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		//  d/dx min(f(x), g(x)) = d/dx (f(x)+g(x)-abs(f(x)-g(x)))/2 = 0.5(f'(x)-g'(x)-sign(f(x)-g(x))*(f'(x)-g'(x)))
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return new ProductNode(new ProductNode(new ConstantNode(0.5d), right.differentiate(val)), new AdditionNode(new ConstantNode(1.0d), new SignumNode(new SubtractionNode(left, right))));
		} else if (right.isConstant(val)) {
			return new ProductNode(new ProductNode(new ConstantNode(0.5d), left.differentiate(val)), new SubtractionNode(new ConstantNode(1.0d), new SignumNode(new SubtractionNode(left, right))));
		} else {
			Node l_deriv = left.differentiate(val);
			Node r_deriv = right.differentiate(val);
			return new ProductNode(new ConstantNode(0.5d), new SubtractionNode(new AdditionNode(l_deriv, r_deriv), new ProductNode(new SignumNode(new SubtractionNode(left, right)), new SubtractionNode(l_deriv, r_deriv))));
		}
	}
}