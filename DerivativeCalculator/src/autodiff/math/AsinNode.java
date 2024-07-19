package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class AsinNode extends UnaryNode {
	public AsinNode(Node left) {
		super(left);
		this.op = "arcsin(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.asin(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.asin(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ExponentNode(left, new ConstantNode(2.0d)))));
	}
}

