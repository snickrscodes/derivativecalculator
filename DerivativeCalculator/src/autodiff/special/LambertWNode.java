package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class LambertWNode extends UnaryNode {
	public LambertWNode(Node left) {
		super(left);
		this.op = "W(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.lambertw(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.lambertw(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(new ProductNode(left.differentiate(val), new LambertWNode(left)), new ProductNode(left, new AdditionNode(new ConstantNode(1.0d), new LambertWNode(left))));
	}
}
