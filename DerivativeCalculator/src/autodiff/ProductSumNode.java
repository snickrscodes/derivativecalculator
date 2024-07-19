package autodiff;

import java.util.HashMap;

public class ProductSumNode extends UnaryNode {
	Variable v;
	public static final int MAX_ITER = 100000; // max terms to compute
	public static final double EPSILON = 1e-16; // stop when term size gets below this
	public Node start; // term to start at
	public Node end; // term to end at
	public ProductSumNode(Variable v, Node left) {
		super(left);
		this.v = v;
		start = new ConstantNode(0.0d);
		end = new ConstantNode((double) MAX_ITER);
		this.op = "pi(%s, 0, infinity)";
	}
	public ProductSumNode(Variable v, Node left, Node start, Node end) {
		super(left);
		this.v = v;
		this.start = start;
		this.end = end;
		this.op = "pi(%s, " + start.toString() + ", " + end.toString() + ")";
	}
	public double evaluate(HashMap<Node, Double> values) {
		double sum = 1.0d;
		for(int n = (int) Math.max(0.0d, start.evaluate(values)); n <= (int) Math.min(MAX_ITER, end.evaluate(values)); n++) {
			values.put(v, (double) n);
			double term = left.evaluate(values);
			sum *= term;
			if (Math.abs(term) < EPSILON && term != 0.0d) {
				break;
			}
		}
		return sum;
	}
	public double evaluate(double value) {
		double sum = 1.0d;
		for(int n = (int) Math.max(0.0d, start.evaluate(value)); n <= (int) Math.min(MAX_ITER, end.evaluate(value)); n++) {
			double term = left.evaluate(value);
			sum *= term;
			if (Math.abs(term) < EPSILON && term != 0.0d) {
				break;
			}
		}
		return sum;
	}
	public Node differentiate(Variable val) {
		// derived by logarithmic differentiation and the general product rule
		return new ProductNode(new ProductSumNode(v, left, start, end), new SumNode(v, new QuotientNode(left.differentiate(val), left), start, end));
	}
}
