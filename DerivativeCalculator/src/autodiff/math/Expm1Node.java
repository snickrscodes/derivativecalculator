package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class Expm1Node extends UnaryNode {
	public Expm1Node(Node left) {
		super(left);
		this.op = "e^%s-1";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.expm1(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.expm1(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ExpNode(left));
	}
}

