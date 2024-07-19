package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class BesselKNode extends UnaryNode {
	public Node n;
	public BesselKNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "bessel_k(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.bessel_k(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.bessel_k(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(new ConstantNode(-0.5d), new ProductNode(left.differentiate(val), new AdditionNode(new BesselKNode(new SubtractionNode(n, new ConstantNode(1.0d)), left), new BesselKNode(new AdditionNode(n, new ConstantNode(1.0d)), left))));
	}
}

