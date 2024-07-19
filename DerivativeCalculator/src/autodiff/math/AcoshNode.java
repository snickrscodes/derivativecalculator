package autodiff.math;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class AcoshNode extends UnaryNode {
	public AcoshNode(Node left) {
		super(left);
		this.op = "arcosh(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.acosh(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.acosh(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), new ConstantNode(1.0d))));
	}
}

