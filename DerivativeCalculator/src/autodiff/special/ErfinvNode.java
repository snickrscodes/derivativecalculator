package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class ErfinvNode extends UnaryNode {
	public ErfinvNode(Node left) {
		super(left);
		this.op = "erfinv(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.erfinv(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.erfinv(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(new ProductNode(new ConstantNode(Math.sqrt(Math.PI)/2.0d), left.differentiate(val)), new ExpNode(new ExponentNode(new ErfinvNode(left), new ConstantNode(2.0d))));
	}
}
