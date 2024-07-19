package autodiff;

import java.util.HashMap;

public class ConstantNode extends Node {
	public double constant;
	public ConstantNode(double constant) {
		this.constant = constant;
		this.precedence = 4;
	}
	@Override
	public boolean isConstant(Variable val) {
		return true;
	}
	@Override
	public String toString() {
		if(this.isInteger()) return Integer.toString((int) this.constant);
		return Double.toString(this.constant);
	}
	public boolean isConstant() {
		return true;
	}
	public boolean equals(double val) {
		return this.constant == val;
	}
	public boolean isInteger() {
		return this.constant % 1.0d == 0.0d;
	}
	public double evaluate(HashMap<Node, Double> values) {
		return this.constant;
	}
	public double evaluate(double value) {
		return this.constant;
	}
	public Node differentiate(Variable val) {
		// d/dx [c] = 0
		return new ConstantNode(0.0d);
	}
}
