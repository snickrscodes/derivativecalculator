package autodiff.special;

import java.util.HashMap;

import autodiff.*;
import functions.Functions;

public class DigammaNode extends UnaryNode {
	public DigammaNode(Node left) {
		super(left);
		this.op = "digamma(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.digamma(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.digamma(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new PolygammaNode(new ConstantNode(1.0d), left));
	}
}
