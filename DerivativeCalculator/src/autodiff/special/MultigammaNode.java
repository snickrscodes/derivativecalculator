package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class MultigammaNode extends UnaryNode {
	public Node p;
	public MultigammaNode(Node p, Node left) {
		super(left);
		this.p = p;
		this.op = "multigamma(" + p.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.multigamma(p.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.multigamma(p.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		Variable k = new Variable("k");
		return new ProductNode(new ProductNode(left.differentiate(val), new MultigammaNode(p, left)), new SumNode(k, new DigammaNode(new AdditionNode(left, new QuotientNode(new SubtractionNode(new ConstantNode(1.0d), k), new ConstantNode(2.0d)))), new ConstantNode(1.0d), p));
	}
}
