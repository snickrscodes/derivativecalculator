package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class PolygammaNode extends Node {
	public PolygammaNode(Node left, Node right) {
		super(left, right);
		this.op = "psi(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.polygamma(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.polygamma(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(left.isConstant(val)) {
			return new ProductNode(right.differentiate(val), new PolygammaNode(new AdditionNode(left, new ConstantNode(1.0d)), right));
		} else if(right.isConstant(val)) {
			return new ConstantNode(0.0d); // not implemented
		} else {
			return new ConstantNode(0.0d); // not implemented
		}
	}
}
