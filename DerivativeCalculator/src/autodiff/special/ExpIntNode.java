package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class ExpIntNode extends UnaryNode {
	public ExpIntNode(Node left) {
		super(left);
		this.op = "Ei(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.exp_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.exp_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new QuotientNode(new ExpNode(left), left));
	}
}
