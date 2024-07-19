package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class SqrtNode extends UnaryNode {
	public SqrtNode(Node left) {
		super(left);
		this.op = "sqrt(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.sqrt(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.sqrt(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new ProductNode(new ConstantNode(2.0d), new SqrtNode(left)));
	}
}

