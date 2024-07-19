package autodiff.math;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class AtanhNode extends UnaryNode {
	public AtanhNode(Node left) {
		super(left);
		this.op = "artanh(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.atanh(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.atanh(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new SubtractionNode(new ConstantNode(1.0d), new ExponentNode(left, new ConstantNode(2.0d))));
	}
}

