package autodiff.special;

import java.util.HashMap;

import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class ZetaNode extends Node {
	public ZetaNode(Node left, Node right) {
		super(left, right);
		this.op = "zeta(%s, %s)";
	}
	public ZetaNode(Node left) {
		super(left);
		this.right = new ConstantNode(1.0d);
		this.op = "zeta(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.zeta(left.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.zeta(left.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		Variable n = new Variable("n");
		if(left.isConstant(val) && right.isConstant(val)) {
			return new ConstantNode(0.0d);
		} else if(right.isConstant(val)) {
			return new ProductNode(left.differentiate(val), new SumNode(n, new QuotientNode(new ProductNode(new ConstantNode(-1.0d), new LogNode(new AdditionNode(n, right))), new ExponentNode(new AdditionNode(n, right), left))));
		} else if(left.isConstant(val)) {
			return new NegNode(new ProductNode(left, new ProductNode(right.differentiate(val), new ZetaNode(new AdditionNode(left, new ConstantNode(1.0d)), right))));
		} else {
			return new AdditionNode(new ProductNode(left.differentiate(val), new SumNode(n, new QuotientNode(new ProductNode(new ConstantNode(-1.0d), new LogNode(new AdditionNode(n, right))), new ExponentNode(new AdditionNode(n, right), left)))), new ProductNode(new ProductNode(new ConstantNode(-1.0d), left), new ProductNode(right.differentiate(val), new ZetaNode(new AdditionNode(left, new ConstantNode(1.0d)), right))));
		}
	}
}

