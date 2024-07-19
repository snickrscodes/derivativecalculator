package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class SphericalBesselYNode extends UnaryNode {
	public Node n;
	public SphericalBesselYNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "spherical_bessel_y(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.spherical_bessel_y(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.spherical_bessel_y(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new QuotientNode(new ProductNode(left.differentiate(val), new SubtractionNode(new ProductNode(n, new SphericalBesselYNode(n, left)), new ProductNode(left, new SphericalBesselYNode(new AdditionNode(n, new ConstantNode(1.0d)), left)))), left);
	}
}

