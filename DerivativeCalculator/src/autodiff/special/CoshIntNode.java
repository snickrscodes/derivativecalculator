package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class CoshIntNode extends UnaryNode {
	public CoshIntNode(Node left) {
		super(left);
		this.op = "Chi(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.cosh_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.cosh_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new QuotientNode(new CoshNode(left), left));
	}
}
