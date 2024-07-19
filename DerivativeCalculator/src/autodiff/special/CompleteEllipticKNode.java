package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class CompleteEllipticKNode extends UnaryNode {
	public CompleteEllipticKNode(Node left) {
		super(left);
		this.op = "elliptic_k(%s, %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.complete_elliptic_k_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.complete_elliptic_k_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
        return new QuotientNode(new ProductNode(new NegNode(left.differentiate(val)), new AdditionNode(new NegNode(new CompleteEllipticKNode(left)), new AdditionNode(new ProductNode(left, new CompleteEllipticKNode(left)), new CompleteEllipticENode(left)))), new ProductNode(new ConstantNode(2.0d), new ProductNode(new SubtractionNode(left, new ConstantNode(1.0d)), left)));
	}
}
