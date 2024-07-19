package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class IncompleteEllipticENode extends Node {
	public IncompleteEllipticENode(Node left, Node right) {
		super(left, right);
		this.op = "elliptic_e(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.elliptic_e_integral(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.elliptic_e_integral(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return new QuotientNode(new ProductNode(right.differentiate(val), new SubtractionNode(new IncompleteEllipticENode(left, right), new IncompleteEllipticFNode(left, right))), new ProductNode(new ConstantNode(2.0d), right));
		} else if (right.isConstant(val)) {
			return new ProductNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ProductNode(right, new ExponentNode(new SinNode(left), new ConstantNode(2.0d))))));
		} else {
			return new AdditionNode(new ProductNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ProductNode(right, new ExponentNode(new SinNode(left), new ConstantNode(2.0d)))))), new QuotientNode(new ProductNode(right.differentiate(val), new SubtractionNode(new IncompleteEllipticENode(left, right), new IncompleteEllipticFNode(left, right))), new ProductNode(new ConstantNode(2.0d), right)));
		}
	}
}
