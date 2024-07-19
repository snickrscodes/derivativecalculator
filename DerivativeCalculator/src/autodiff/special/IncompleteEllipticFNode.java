package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class IncompleteEllipticFNode extends Node {
	public IncompleteEllipticFNode(Node left, Node right) {
		super(left, right);
		this.op = "elliptic_f(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.elliptic_f_integral(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.elliptic_f_integral(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if (left.isConstant(val)) {
			return new ProductNode(right.differentiate(val), new SubtractionNode(new SubtractionNode(new QuotientNode(new SinNode(new ProductNode(new ConstantNode(2.0d), left)), new ProductNode(new ConstantNode(4.0d), new ProductNode(new SubtractionNode(right, new ConstantNode(1.0d)), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ProductNode(right, new ExponentNode(new SinNode(left), new ConstantNode(2.0d)))))))), new QuotientNode(new IncompleteEllipticFNode(left, right), new ProductNode(new ConstantNode(2.0d), right))), new QuotientNode(new IncompleteEllipticENode(left, right), new ProductNode(new ConstantNode(2.0d), new ProductNode(new SubtractionNode(right, new ConstantNode(1.0d)), right)))));
		} else if (right.isConstant(val)) {
			return new QuotientNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ProductNode(right, new ExponentNode(new SinNode(left), new ConstantNode(2.0d))))));
		} else {
			return new AdditionNode(new QuotientNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ProductNode(right, new ExponentNode(new SinNode(left), new ConstantNode(2.0d)))))), new ProductNode(right.differentiate(val), new SubtractionNode(new SubtractionNode(new QuotientNode(new SinNode(new ProductNode(new ConstantNode(2.0d), left)), new ProductNode(new ConstantNode(4.0d), new ProductNode(new SubtractionNode(right, new ConstantNode(1.0d)), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ProductNode(right, new ExponentNode(new SinNode(left), new ConstantNode(2.0d)))))))), new QuotientNode(new IncompleteEllipticFNode(left, right), new ProductNode(new ConstantNode(2.0d), right))), new QuotientNode(new IncompleteEllipticENode(left, right), new ProductNode(new ConstantNode(2.0d), new ProductNode(new SubtractionNode(right, new ConstantNode(1.0d)), right))))));
		}
	}
}
