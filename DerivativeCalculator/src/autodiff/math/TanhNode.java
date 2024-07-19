package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class TanhNode extends UnaryNode {
	public TanhNode(Node left) {
		super(left);
		this.op = "tanh(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.tanh(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.tanh(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new SubtractionNode(new ConstantNode(1.0d), new ExponentNode(new TanhNode(left), new ConstantNode(2.0d))));
	}
}

