package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class CompleteEllipticENode extends UnaryNode {
	public CompleteEllipticENode(Node left) {
		super(left);
		this.op = "elliptic_e(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.complete_elliptic_e_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.complete_elliptic_e_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
        return new QuotientNode(new ProductNode(left.differentiate(val), new SubtractionNode(new CompleteEllipticENode(left), new CompleteEllipticKNode(left))), new ProductNode(new ConstantNode(2.0d), left));
	}
}
