package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class Debye1Node extends UnaryNode {
	public ConstantNode n;
	public Debye1Node(ConstantNode n, Node left) {
		super(left);
		this.n = n;
		this.op = "debye_1(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.debye1_integral(n.constant, left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.debye1_integral(n.constant, left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(new ProductNode(left.differentiate(val), new ExponentNode(left, n)), new Expm1Node(left));
	}
}

