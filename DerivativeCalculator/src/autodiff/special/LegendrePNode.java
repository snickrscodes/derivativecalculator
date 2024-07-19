package autodiff.special;

import java.util.HashMap;
import autodiff.*;

public class LegendrePNode extends UnaryNode {
	public Node n;
	public LegendrePNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "legendre_p(" + n.toString() + ", %s)";
	}
	// generate the nth legendre p polynomial
	public Node generate() {
		Variable k = new Variable("k");
//		use this for non-integer values of n		
//		Node term = new QuotientNode(new ProductNode(new ExponentNode(new QuotientNode(new SubtractionNode(new ConstantNode(1.0d), left), new ConstantNode(2.0d)), k),
//				new ProductNode(
//						new PochhammerNode(new ProductNode(new ConstantNode(-1.0d), n), k), 
//						new PochhammerNode(new AdditionNode(new ConstantNode(1.0d), n), k)
//						)), 
//				new ExponentNode(new GammaNode(new AdditionNode(new ConstantNode(1.0d), k)), new ConstantNode(2.0d))
//				);
//		return new SumNode(k, term, new ConstantNode(0.0), new ConstantNode(100000.0d));
//		a good all around approximation
//		the official end bound for the sum is new FloorNode(new QuotientNode(n, new ConstantNode(2.0d))) but i replace it with n because it can lead to better convergence
		return new ProductNode(new ExponentNode(new ConstantNode(2.0d), new NegNode(n)), new SumNode(k, new ProductNode(new ProductNode(new ExponentNode(new ConstantNode(-1.0d), k), new ExponentNode(left, new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)))), new ProductNode(new ChooseNode(n, k), new ChooseNode(new ProductNode(new ConstantNode(2.0d), new SubtractionNode(n, k)), n))), new ConstantNode(0.0d), n));
		// the below formula is shorter but it only works for integer values of n
//		return new ProductNode(new ExponentNode(new QuotientNode(new SubtractionNode(left, new ConstantNode(1.0d)), new ConstantNode(2.0d)), n), new SumNode(k, new ProductNode(new ExponentNode(new QuotientNode(new AdditionNode(left, new ConstantNode(1.0d)), new SubtractionNode(left, new ConstantNode(1.0d))), k), new ExponentNode(new ChooseNode(n, k), new ConstantNode(2.0d))), new ConstantNode(0.0d), n));
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
		return new QuotientNode(
				new ProductNode(new NegNode(left.differentiate(val)), 
						new ProductNode(
								new AdditionNode(n, new ConstantNode(1.0d)), 
								new SubtractionNode(
										new ProductNode(left, new LegendrePNode(n, left)), 
										new LegendrePNode(new AdditionNode(n, new ConstantNode(1.0d)), left)
										)
								)
						), 
				new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), new ConstantNode(1.0d))
				);
	}
}
