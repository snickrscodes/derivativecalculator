package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class IncompleteBetaNode extends UnaryNode {
	public ConstantNode a;
	public ConstantNode b;
	public IncompleteBetaNode(Node left, ConstantNode a, ConstantNode b) {
		super(left);
		this.a = a;
		this.b = b;
		this.op = "beta(%s, " + a.toString() + ", " + b.toString() + ")";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.incomplete_beta(left.evaluate(values), a.constant, b.constant);
	}
	public double evaluate(double value) {
		return Functions.incomplete_beta(left.evaluate(value), a.constant, b.constant);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(new ExponentNode(left, new SubtractionNode(a, new ConstantNode(1.0d))), new ExponentNode(new SubtractionNode(new ConstantNode(1.0d), left), new SubtractionNode(b, new ConstantNode(1.0d)))));
	}
}
