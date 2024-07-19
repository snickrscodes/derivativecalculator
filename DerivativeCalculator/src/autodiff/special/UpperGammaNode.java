package autodiff.special;

import java.util.HashMap;

import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class UpperGammaNode extends Node {
	public UpperGammaNode(Node left, Node right) {
		super(left, right);
		this.op = "uppergamma(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.uppergamma(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.uppergamma(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return new NegNode(new ProductNode(right.differentiate(val), new ProductNode(new ExpNode(new NegNode(right)), new ExponentNode(right, new SubtractionNode(left, new ConstantNode(1.0d))))));
		} else if (right.isConstant(val)) {
			return new ConstantNode(0.0d); // not implemented
		} else {
			return new ConstantNode(0.0d); // not implemented
		}
	}
}
