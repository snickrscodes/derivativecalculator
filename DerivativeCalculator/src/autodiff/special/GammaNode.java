package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class GammaNode extends UnaryNode {
	public GammaNode(Node left) {
		super(left);
		this.op = "gamma(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.gamma(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.gamma(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(new GammaNode(left), new DigammaNode(left)));
	}
}
