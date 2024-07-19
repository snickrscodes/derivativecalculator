package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class SignumNode extends UnaryNode {
	public SignumNode(Node left) {
		super(left);
		this.op = "sign(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.signum(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.signum(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		return new ConstantNode(0.0d);
	}
}

