package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class TanNode extends UnaryNode {
	public TanNode(Node left) {
		super(left);
		this.op = "tan(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.tan(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.tan(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new ExponentNode(new CosNode(left), new ConstantNode(2.0d)));
	}
}

