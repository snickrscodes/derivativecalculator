package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class ErfNode extends UnaryNode {
	public ErfNode(Node left) {
		super(left);
		this.op = "erf(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.erf(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.erf(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(new ConstantNode(2.0d/Math.sqrt(Math.PI)), new ExpNode(new NegNode(new ExponentNode(left, new ConstantNode(2.0d))))));
	}
}
