package autodiff.special;

import java.util.HashMap;
import autodiff.*;

public class LaguerreLNode extends UnaryNode {
	public Node n;
	public LaguerreLNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "laguerre_l(" + n.toString() + ", %s)";
	}
	// generate the nth laguerre L polynomial
	public Node generate() {
		Variable k = new Variable("k");
		// we change the end bound from n to a large number for precision for non-integer n values, keep it as n for integer n values
		Node term = new QuotientNode(new ProductNode(new ExponentNode(new NegNode(left), k), new ChooseNode(n, k)), new GammaNode(new AdditionNode(k, new ConstantNode(1.0d))));
		return new SumNode(k, term, new ConstantNode(0.0d), n.isInteger() ? n : new AdditionNode(n, new ConstantNode(100.0d)));
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
		return new QuotientNode(new ProductNode(left.differentiate(val), new ProductNode(n, new SubtractionNode(new LaguerreLNode(n, left), new LaguerreLNode(new SubtractionNode(n, new ConstantNode(1.0d)), left)))), left);
	}
}
