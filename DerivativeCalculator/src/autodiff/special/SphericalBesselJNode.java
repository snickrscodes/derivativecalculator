package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class SphericalBesselJNode extends UnaryNode {
	public Node n;
	public SphericalBesselJNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "spherical_bessel_j(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.spherical_bessel_j(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.spherical_bessel_j(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new NegNode(new QuotientNode(new ProductNode(left.differentiate(val), new AdditionNode(new SphericalBesselJNode(n, left), new ProductNode(left, new SubtractionNode(new SphericalBesselJNode(new AdditionNode(n, new ConstantNode(1.0d)), left), new SphericalBesselJNode(new SubtractionNode(n, new ConstantNode(1.0d)), left))))), new ProductNode(new ConstantNode(2.0d), left)));
	}
}

