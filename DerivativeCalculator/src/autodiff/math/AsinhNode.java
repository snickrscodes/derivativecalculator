package autodiff.math;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class AsinhNode extends UnaryNode {
	public AsinhNode(Node left) {
		super(left);
		this.op = "arsinh(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.asinh(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.asinh(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new SqrtNode(new AdditionNode(new ExponentNode(left, new ConstantNode(2.0d)), new ConstantNode(1.0d))));
	}
}

