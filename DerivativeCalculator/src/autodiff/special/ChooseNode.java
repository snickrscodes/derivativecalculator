package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class ChooseNode extends Node {
	public ChooseNode(Node left, Node right) {
		super(left, right);
		this.op = "choose(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.choose(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.choose(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(left.isConstant(val)) {
			return new ProductNode(new ProductNode(right.differentiate(val), new ChooseNode(left, right)), new SubtractionNode(new DigammaNode(new AdditionNode(new SubtractionNode(left, right), new ConstantNode(1.0d))), new DigammaNode(new AdditionNode(right, new ConstantNode(1.0d)))));
		} else if(right.isConstant(val)) {
			return new ProductNode(new ProductNode(left.differentiate(val), new ChooseNode(left, right)), new SubtractionNode(new DigammaNode(new AdditionNode(left, new ConstantNode(1.0d))), new DigammaNode(new AdditionNode(new SubtractionNode(left, right), new ConstantNode(1.0d)))));
		} else {
			return new ProductNode(new ChooseNode(left, right), new AdditionNode(new ProductNode(right.differentiate(val), new SubtractionNode(new DigammaNode(new AdditionNode(new SubtractionNode(left, right), new ConstantNode(1.0d))), new DigammaNode(new AdditionNode(right, new ConstantNode(1.0d))))), new ProductNode(left.differentiate(val), new SubtractionNode(new DigammaNode(new AdditionNode(left, new ConstantNode(1.0d))), new DigammaNode(new AdditionNode(new SubtractionNode(left, right), new ConstantNode(1.0d)))))));
		}
	}
}
