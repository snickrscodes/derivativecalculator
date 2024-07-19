package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class TransportJNode extends UnaryNode {
	public Node n;
	public TransportJNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "transport_j(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.transport_j_integral(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.transport_j_integral(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(left.isConstant(val)) {
			return new ProductNode(left.differentiate(val), new QuotientNode(new ProductNode(new ExponentNode(left, n), new ExpNode(left)), new ExponentNode(new Expm1Node(left), new ConstantNode(2.0d))));
		} else if(right.isConstant(val)) {
			return new ConstantNode(0.0d); // not implemented
		} else {
			return new ConstantNode(0.0d); // not implemented
		}
	}
}

