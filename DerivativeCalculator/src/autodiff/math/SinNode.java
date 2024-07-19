package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class SinNode extends UnaryNode {
	public SinNode(Node left) {
		super(left);
		this.op = "sin(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.sin(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.sin(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new CosNode(left));
	}
}

