package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class CosNode extends UnaryNode {
	public CosNode(Node left) {
		super(left);
		this.op = "cos(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.cos(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.cos(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new NegNode(new ProductNode(left.differentiate(val), new SinNode(left)));
	}
}

