package autodiff.special;

import java.util.HashMap;

import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class FresnelSNode extends UnaryNode {
	public FresnelSNode(Node left) {
		super(left);
		this.op = "S(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.fresnel_s_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.fresnel_s_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new SinNode(new ProductNode(new ConstantNode(Math.PI/2.0d), new ExponentNode(left, new ConstantNode(2.0d)))));
	}
}
