package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class CompleteEllipticPINode extends Node {
	public CompleteEllipticPINode(Node left, Node right) {
		super(left, right);
		this.op = "elliptic_pi(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.complete_elliptic_pi_integral(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.complete_elliptic_pi_integral(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return new QuotientNode(new ProductNode(right.differentiate(val), new AdditionNode(new CompleteEllipticPINode(left, right), new QuotientNode(new CompleteEllipticENode(right), new SubtractionNode(right, new ConstantNode(1.0d))))), new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, right)));
		} else if (right.isConstant(val)) {
			return new QuotientNode(new ProductNode(left.differentiate(val), new AdditionNode(new CompleteEllipticENode(right), new QuotientNode(new AdditionNode(new ProductNode(new CompleteEllipticKNode(right), new SubtractionNode(right, left)), new ProductNode(new CompleteEllipticPINode(left, right), new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), right))), left))), new ProductNode(new SubtractionNode(right, left), new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, new ConstantNode(1.0d)))));
		} else {
			return new AdditionNode(new QuotientNode(new ProductNode(left.differentiate(val), new AdditionNode(new CompleteEllipticENode(right), new QuotientNode(new AdditionNode(new ProductNode(new CompleteEllipticKNode(right), new SubtractionNode(right, left)), new ProductNode(new CompleteEllipticPINode(left, right), new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), right))), left))), new ProductNode(new SubtractionNode(right, left), new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, new ConstantNode(1.0d))))), new QuotientNode(new ProductNode(right.differentiate(val), new AdditionNode(new CompleteEllipticPINode(left, right), new QuotientNode(new CompleteEllipticENode(right), new SubtractionNode(right, new ConstantNode(1.0d))))), new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, right))));
		}
	}
}
