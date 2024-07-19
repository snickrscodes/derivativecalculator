package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class BesselINode extends UnaryNode {
	public Node n;
	public BesselINode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "bessel_i(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.bessel_i(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.bessel_i(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(new ConstantNode(0.5d), new ProductNode(left.differentiate(val), new AdditionNode(new BesselINode(new SubtractionNode(n, new ConstantNode(1.0d)), left), new BesselINode(new AdditionNode(n, new ConstantNode(1.0d)), left))));
	}
}

