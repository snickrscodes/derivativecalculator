package autodiff;

import java.util.HashMap;
import autodiff.math.FloorNode;

public class ModNode extends Node {
	public ModNode(Node left, Node right) {
		super(left, right);
		this.op = "mod(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return left.evaluate(values) % right.evaluate(values);
	}
	public double evaluate(double value) {
		return left.evaluate(value) % right.evaluate(value);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return new ProductNode(right.differentiate(val), new NegNode(new FloorNode(new QuotientNode(left, right))));
		} else if (right.isConstant(val)) {
			return left.differentiate(val);
		} else {
			return new SubtractionNode(left.differentiate(val), new ProductNode(right.differentiate(val), new FloorNode(new QuotientNode(left, right))));
		}
	}
}