package autodiff.special;

import java.util.HashMap;
import autodiff.*;

public class ChebyshevTNode extends UnaryNode {
	public Node n;
	public ChebyshevTNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "chebyshev_u(" + n.toString() + ", %s)";
	}
	// generate the nth chebyshev t polynomial
	public Node generate() {
		Variable k = new Variable("k");
//		replace the end bound of new FloorNode(new QuotientNode(n, new ConstantNode(2.0d))) with n to accelerate convergence
		Node term = new ProductNode(new ProductNode(new ExponentNode(left, new SubtractionNode(n, new ProductNode(new ConstantNode(2.0d), k))), new ExponentNode(new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), new ConstantNode(1.0d)), k)), new ChooseNode(n, new ProductNode(new ConstantNode(2.0d), k)));
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
		// return this.generate().differentiate(val);
		return new ProductNode(left.differentiate(val), new ProductNode(n, new ChebyshevUNode(new SubtractionNode(n, new ConstantNode(1.0d)), left)));
	}
}
