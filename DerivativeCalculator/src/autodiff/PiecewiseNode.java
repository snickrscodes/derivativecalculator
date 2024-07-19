package autodiff;

import java.util.ArrayList;
import java.util.HashMap;

public class PiecewiseNode extends Node {
	// these have to be of the same length
	public ArrayList<Interval> intervals;
	public ArrayList<Node> functions;
	public PiecewiseNode(ArrayList<Node> functions, ArrayList<Interval> intervals) {
		this.functions = functions;
		this.intervals = intervals;
	}
	public double evaluate(HashMap<Node, Double> values) {
		double value = values.values().iterator().next();
		for(int i = 0; i < functions.size(); i++) {
			if(intervals.get(i).contains(value)) {
				return functions.get(i).evaluate(values);
			}
		}
		return 0.0d;
	}
	public double evaluate(double value) {
		for(int i = 0; i < functions.size(); i++) {
			if(intervals.get(i).contains(value)) {
				return functions.get(i).evaluate(value);
			}
		}
		return 0.0d;
	}
	public Node differentiate(Variable val) {
		ArrayList<Node> derivatives = new ArrayList<Node>();
		for(int i = 0; i < functions.size(); i++) {
			derivatives.add(functions.get(i).differentiate(val));
		}
		return new PiecewiseNode(derivatives, intervals);
	}
	public boolean equals(Object a) {
		return this.hashCode() == a.hashCode();
	}
}
