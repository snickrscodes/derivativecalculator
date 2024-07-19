package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class AtanIntNode extends UnaryNode {
	public AtanIntNode(Node left) {
		super(left);
		this.op = "Atanint(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.atan_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.atan_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new QuotientNode(new AtanNode(left), left));
	}
}
