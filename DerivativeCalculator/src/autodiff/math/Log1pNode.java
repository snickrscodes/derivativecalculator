package autodiff.math;

import java.util.HashMap;

import autodiff.*;

public class Log1pNode extends UnaryNode {
	public Log1pNode(Node left) {
		super(left);
		this.op = "ln(1+%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.log1p(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.log1p(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new AdditionNode(left, new ConstantNode(1.0d)));
	}
}
