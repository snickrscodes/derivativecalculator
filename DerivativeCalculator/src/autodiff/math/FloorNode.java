package autodiff.math;

import java.util.HashMap;

import autodiff.*;

public class FloorNode extends UnaryNode {
	public FloorNode(Node left) {
		super(left);
		this.op = "floor(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.floor(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.floor(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		return new ConstantNode(0.0d);
	}
}
