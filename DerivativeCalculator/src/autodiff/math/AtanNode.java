package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class AtanNode extends UnaryNode {
	public AtanNode(Node left) {
		super(left);
		this.op = "arctan(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.atan(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.atan(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new AdditionNode(new ExponentNode(left, new ConstantNode(2.0d)), new ConstantNode(1.0d)));
	}
}

