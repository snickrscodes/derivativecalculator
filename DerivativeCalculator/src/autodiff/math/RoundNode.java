package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class RoundNode extends UnaryNode {
	public RoundNode(Node left) {
		super(left);
		this.op = "round(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.round(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.round(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		return new ConstantNode(0.0d);
	}
}