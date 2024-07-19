package autodiff.math;

import java.util.HashMap;
import autodiff.*;

public class CubeNode extends UnaryNode {
	public CubeNode(Node left) {
		super(left);
		this.op = "(%s)^3";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Math.pow(left.evaluate(values), 3.0d);
	}
	public double evaluate(double value) {
		return Math.pow(left.evaluate(value), 3.0d);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(new ConstantNode(3.0d), new SquareNode(left)));
	}
}
