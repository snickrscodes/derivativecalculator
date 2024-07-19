package autodiff.special;

import java.util.HashMap;

import autodiff.*;
import functions.Functions;

public class PochhammerNode extends Node {
	public PochhammerNode(Node left, Node right) {
		super(left, right);
		// doing the actual symbol would look ugly
		this.op = "pochhammer(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.pochhammer(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.pochhammer(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(left.isConstant(val)) {
			return new ProductNode(right.differentiate(val), new ProductNode(new PochhammerNode(left, right), new DigammaNode(new AdditionNode(left, right))));
		} else if(right.isConstant(val)) {
			return new ProductNode(left.differentiate(val), new ProductNode(new PochhammerNode(left, right), new SubtractionNode(new DigammaNode(new AdditionNode(left, right)), new DigammaNode(left))));
		} else {
			return new ProductNode(
					new PochhammerNode(left, right),
					new AdditionNode(
							new ProductNode(right.differentiate(val), new DigammaNode(new AdditionNode(left, right))),
							new ProductNode(left.differentiate(val), new SubtractionNode(new DigammaNode(new AdditionNode(left, right)), new DigammaNode(left)))
							)
					);
		}
	}
}
