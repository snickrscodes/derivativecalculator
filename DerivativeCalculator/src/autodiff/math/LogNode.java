package autodiff.math;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class LogNode extends UnaryNode {
	public Node base;
	public LogNode(Node left) {
		super(left);
		this.base = new ConstantNode(Math.E); // natural log
		this.op = "ln(%s)";
	}
	public LogNode(Node base, Node left) {
		super(left);
		this.base = base;
		this.op = "log(" + base.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.log(base.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.log(base.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		if(base.equals(Math.E)) return new QuotientNode(left.differentiate(val), left);
		else return new QuotientNode(left.differentiate(val), new ProductNode(new LogNode(base), left));
	}
}
