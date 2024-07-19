package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class AiryAiPrimeNode extends UnaryNode {
	public AiryAiPrimeNode(Node left) {
		super(left);
		this.op = "Ai'(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.airy_ai_prime_integral(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.airy_ai_prime_integral(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(left, new AiryAiNode(left)));
	}
}

