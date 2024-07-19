package autodiff;

import java.util.HashMap;

public class Variable extends Node {
	public Variable(String name) {
		this.name = name;
		this.precedence = 4;
	}
	@Override
	public String toString() {
		return name;
	}
	public boolean isConstant(Variable val) {
		return val.hashCode() != this.hashCode();
	}
	@Override
	public double evaluate(HashMap<Node, Double> values) {
		return values.get(this);
	}
	public double evaluate(double value) {
		return value;
	}
	public Node differentiate(Variable val) {
		// partial derivative or not
		return this.isConstant(val) ? new ConstantNode(0.0d) : new ConstantNode(1.0d);
	}
}
