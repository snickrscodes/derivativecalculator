package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class CeilNode extends UnaryNode {
	public CeilNode(Node left) {
		super(left);
		this.op = "ceil(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.ceil(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.ceil(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		return new ConstantNode(0.0d);
	}
}