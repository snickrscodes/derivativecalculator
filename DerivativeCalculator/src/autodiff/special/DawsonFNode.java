package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class DawsonFNode extends UnaryNode {
	public DawsonFNode(Node left) {
		super(left);
		this.op = "dawson_f(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.dawson_f(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.dawson_f(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new SubtractionNode(new ConstantNode(1.0d), new ProductNode(new ProductNode(new ConstantNode(2.0d), left), new DawsonFNode(left))));
	}
}
