package autodiff.special;

import java.util.HashMap;

import autodiff.*;

public class GegenbauerCNode extends Node {
	public Node n;
	// left node = lambda, right node = x;
	public GegenbauerCNode(Node n, Node left, Node right) {
		super(left, right);
		this.n = n;
		this.op = "gegenbauer_c(" + n.toString() + ", %s)";
	}
	// generate the nth gegenbauer c polynomial
	public Node generate() {
		Variable k = new Variable("k");
		// replace the end bound of new FloorNode(new QuotientNode(n, new ConstantNode(2.0d))) with n to accelerate convergence for non-integer values of n
		return new SumNode(k, new QuotientNode(new ProductNode(new ProductNode(new ExponentNode(new ConstantNode(-1.0d), k), new ExponentNode(new ProductNode(new ConstantNode(2.0d), right), new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)))), new PochhammerNode(left, new SubtractionNode(n, k))), new ProductNode(new GammaNode(new AdditionNode(k, new ConstantNode(1.0d))), new GammaNode(new AdditionNode(new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)), new ConstantNode(1.0d))))), new ConstantNode(0.0d), n);
	}
	public double evaluate(HashMap<Node, Double> values) {
		return this.generate().evaluate(values);
	}
	public double evaluate(double value) {
		return this.generate().evaluate(value);
	}
	public Node differentiate(Variable val) {
//		return this.generate().differentiate(val);
		return new ProductNode(new ProductNode(new ConstantNode(2.0d), right.differentiate(val)), new ProductNode(left, new GegenbauerCNode(new SubtractionNode(n, new ConstantNode(1.0d)), new AdditionNode(left, new ConstantNode(1.0d)), right)));
	}
}
