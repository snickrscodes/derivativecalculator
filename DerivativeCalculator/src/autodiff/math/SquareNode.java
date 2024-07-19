package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class SquareNode extends UnaryNode {
	public SquareNode(Node left) {
		super(left);
		this.op = "(%s)^2";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.pow(left.evaluate(values), 2.0d);
	}
	public double evaluate(double value) {
		return Math.pow(left.evaluate(value), 2.0d);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(new ConstantNode(2.0d), left));
	}
}
