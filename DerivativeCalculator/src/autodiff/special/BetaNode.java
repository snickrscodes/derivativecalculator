package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class BetaNode extends Node {
	public BetaNode(Node left, Node right) {
		super(left, right);
		this.op = "beta(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.beta(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.beta(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(left.isConstant(val)) {
			return new ProductNode(right.differentiate(val), new ProductNode(new BetaNode(left, right), new SubtractionNode(new DigammaNode(left), new DigammaNode(new AdditionNode(left, right)))));
		} else if(right.isConstant(val)) {
			return new ProductNode(left.differentiate(val), new ProductNode(new BetaNode(left, right), new SubtractionNode(new DigammaNode(left), new DigammaNode(new AdditionNode(left, right)))));
		} else {
			return new ProductNode(new BetaNode(left, right), new AdditionNode(new ProductNode(left.differentiate(val), new SubtractionNode(new DigammaNode(left), new DigammaNode(new AdditionNode(left, right)))), new ProductNode(right.differentiate(val), new SubtractionNode(new DigammaNode(right), new DigammaNode(new AdditionNode(left, right))))));
		}
	}
}
