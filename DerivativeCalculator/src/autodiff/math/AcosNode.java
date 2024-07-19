package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class AcosNode extends UnaryNode {
	public AcosNode(Node left) {
		super(left);
		this.op = "arccos(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.acos(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Math.acos(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new NegNode(new QuotientNode(left.differentiate(val), new SqrtNode(new SubtractionNode(new ConstantNode(1.0d), new ExponentNode(left, new ConstantNode(2.0d))))));
	}
}

