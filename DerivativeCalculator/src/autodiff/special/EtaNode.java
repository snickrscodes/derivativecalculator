package autodiff.special;

import java.util.HashMap;

import autodiff.*;
import functions.Functions;

public class EtaNode extends UnaryNode {
	public EtaNode(Node left) {
		super(left);
		this.op = "eta(%s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.eta(left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.eta(left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(new SubtractionNode(new ConstantNode(1.0d), new ExponentNode(new ConstantNode(2.0d), new SubtractionNode(new ConstantNode(1.0d), left))), new ZetaNode(left)).differentiate(val);
	}
}

