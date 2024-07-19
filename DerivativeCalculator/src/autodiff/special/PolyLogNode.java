package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class PolyLogNode extends UnaryNode {
	public Node n;
	public PolyLogNode(Node left) {
		super(left);
		this.n = new ConstantNode(1.0d);
		this.op = "Li(" + n.toString() + ", %s)";
	}
	public PolyLogNode(Node n, Node left) {
		super(left);
		this.n = n;
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.polylog(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.polylog(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(new ProductNode(left.differentiate(val), new PolyLogNode(new SubtractionNode(n, new ConstantNode(1.0d)), left)), left);
	}
}
