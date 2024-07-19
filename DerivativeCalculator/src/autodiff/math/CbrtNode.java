package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class CbrtNode extends UnaryNode {
	public CbrtNode(Node left) {
		super(left);
		this.op = "cbrt(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.cbrt(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.cbrt(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new ProductNode(new ConstantNode(3.0d), new CbrtNode(new ExponentNode(left, new ConstantNode(2.0d)))));
	}
}

