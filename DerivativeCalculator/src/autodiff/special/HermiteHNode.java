package autodiff.special;

import java.util.HashMap;

import autodiff.*;

public class HermiteHNode extends UnaryNode {
	public Node n;
	public HermiteHNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "hermite_h(" + n.toString() + ", %s)";
	}
	// generate the nth hermite h polynomial
	public Node generate() {
		Variable k = new Variable("k");
//		replace the end bound of new FloorNode(new QuotientNode(n, new ConstantNode(2.0d))) with n to accelerate convergence
		Node term = new QuotientNode(
				new ProductNode(new ExponentNode(new ConstantNode(-1.0d), k), new ExponentNode(new ProductNode(new ConstantNode(2.0d), left), new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)))), 
				new ProductNode(new GammaNode(new AdditionNode(k, new ConstantNode(1.0d))), new GammaNode(new AdditionNode(new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)), new ConstantNode(1.0d))))
				);
//		Node term = new QuotientNode(
//				new ProductNode(
//						new ProductNode(new ExponentNode(new ConstantNode(-1.0d), k), new ExponentNode(left, new ProductNode(new ConstantNode(-2.0d), k))), 
//						new ProductNode(new PochhammerNode(new QuotientNode(new SubtractionNode(new ConstantNode(1.0d), n), new ConstantNode(2.0d)), k), new PochhammerNode(new QuotientNode(n, new ConstantNode(-2.0d)), k))
//						), new GammaNode(new AdditionNode(k, new ConstantNode(1.0d))));
		return new ProductNode(new GammaNode(new AdditionNode(n, new ConstantNode(1.0d))), new SumNode(k, term, new ConstantNode(0.0d), n));
	}
	public double evaluate(HashMap<Node, Double> values) {
		return this.generate().evaluate(values);
	}
	public double evaluate(double value) {
		return this.generate().evaluate(value);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
//		return this.generate().differentiate(val);
		return new ProductNode(left.differentiate(val), new ProductNode(new ConstantNode(2.0d), new ProductNode(n, new HermiteHNode(new SubtractionNode(n, new ConstantNode(1.0d)), left))));
	}
}
