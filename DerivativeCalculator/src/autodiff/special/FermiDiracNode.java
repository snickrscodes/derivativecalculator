package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import functions.Functions;

public class FermiDiracNode extends UnaryNode {
	public Node n;
	public FermiDiracNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.op = "fermi_dirac(" + n.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.fermi_dirac_integral(n.evaluate(values), left.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.fermi_dirac_integral(n.evaluate(value), left.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new FermiDiracNode(new SubtractionNode(n, new ConstantNode(1.0d)), left));
	}
}

