package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class LogIntNode extends UnaryNode {
	public LogIntNode(Node left) {
		super(left);
		this.op = "li(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.log_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.log_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(left.differentiate(val), new LogNode(left));
	}
}
