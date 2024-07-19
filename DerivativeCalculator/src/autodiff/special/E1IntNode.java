package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class E1IntNode extends UnaryNode {
	public E1IntNode(Node left) {
		super(left);
		this.op = "E_1(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.e1_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.e1_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new QuotientNode(new NegNode(new ExpNode(new NegNode(left))), left));
	}
}
