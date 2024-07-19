package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class SinhNode extends UnaryNode {
	public SinhNode(Node left) {
		super(left);
		this.op = "sinh(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.sinh(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.sinh(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new CoshNode(left));
	}
}

