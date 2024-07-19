package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class CoshNode extends UnaryNode {
	public CoshNode(Node left) {
		super(left);
		this.op = "cosh(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.cosh(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.cosh(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new SinhNode(left));
	}
}

