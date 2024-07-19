package autodiff.math;

import java.util.HashMap;

import autodiff.*;

public class AbsNode extends UnaryNode {
	public AbsNode(Node left) {
		super(left);
		this.op = "|%s|";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.abs(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.abs(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new SignumNode(left));
	}
}
