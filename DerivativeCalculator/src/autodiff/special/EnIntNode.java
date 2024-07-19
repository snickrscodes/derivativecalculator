package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class EnIntNode extends UnaryNode {
	public Node base;
	public EnIntNode(Node base, Node left) {
		super(left);
		this.base = base;
		this.op = "E(" + base.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.exp_integral(base.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.exp_integral(base.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(new NegNode(left.differentiate(val)), new EnIntNode(new SubtractionNode(base, new ConstantNode(1.0d)), left));
	}
}
