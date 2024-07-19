package autodiff.special;

import java.util.HashMap;
import autodiff.*;

public class ChebyshevUNode extends UnaryNode {
	public Node n;
	public ChebyshevUNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "chebyshev_u(" + n.toString() + ", %s)";
	}
	// generate the nth chebyshev u polynomial
	public Node generate() {
		Variable k = new Variable("k");
//		replace the end bound of new FloorNode(new QuotientNode(n, new ConstantNode(2.0d))) with n to accelerate convergence
		Node term = new QuotientNode(
				new ProductNode(
						new ProductNode(new ExponentNode(new ConstantNode(-1.0d), k), new ExponentNode(new ProductNode(new ConstantNode(2.0d), left), new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)))), 
						new GammaNode(new AdditionNode(new SubtractionNode(n, k), new ConstantNode(1.0d)))
						), 
				new ProductNode(new GammaNode(new AdditionNode(k, new ConstantNode(1.0d))), new GammaNode(new AdditionNode(new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k)), new ConstantNode(1.0d))))
				);
		return new SumNode(k, term, new ConstantNode(0.0d), n);
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
				new ProductNode(left.differentiate(val), 
						new SubtractionNode(
								new ProductNode(n, new ProductNode(left, new ChebyshevUNode(n, left))),
								new ProductNode(new AdditionNode(n, new ConstantNode(1.0d)), new ChebyshevUNode(new SubtractionNode(n, new ConstantNode(1.0d)), left))
								)
						),
				new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), new ConstantNode(1.0d))
				);
	}
}
