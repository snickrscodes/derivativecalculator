package autodiff.special;

import java.util.HashMap;
import autodiff.*;

public class JacobiPNode extends UnaryNode {
	public Node n;
	public Node a;
	public Node b;
	public JacobiPNode(Node n, Node left) {
		super(left);
		this.n = n;
		this.a = new ConstantNode(0.0d);
		this.b = new ConstantNode(0.0d);
		this.op = String.format("jacobi_p(%s, %s, %s, ", n.toString(), a.toString(), b.toString()) + "%s)";
	}
	public JacobiPNode(Node n, Node a, Node b, Node left) {
		super(left);
		this.n = n;
		this.a = a;
		this.b = b;
		this.op = String.format("jacobi_p(%s, %s, %s, ", n.toString(), a.toString(), b.toString()) + "%s)";
	}
	// generate the nth jacobi p polynomial
	public Node generate() {
		Variable k = new Variable("k");
		Node term = new ProductNode(
		new ProductNode(
				new ExponentNode(new QuotientNode(new AdditionNode(left, new ConstantNode(1.0d)), new ConstantNode(2.0d)), new SubtractionNode(n, k)),
				new ExponentNode(new QuotientNode(new SubtractionNode(left, new ConstantNode(1.0d)), new ConstantNode(2.0d)), k)
				), 
		new ProductNode(
				new ChooseNode(new AdditionNode(n, b), k),
				new ChooseNode(new AdditionNode(n, a), new SubtractionNode(n, k))
				)
		);
		return new SumNode(k, term, new ConstantNode(0.0d), n.isInteger() ? n : new AdditionNode(n, new ConstantNode(10.0d)));
	}
	public double evaluate(HashMap<Node, Double> values) {
		return this.generate().evaluate(values);
	}
	public double evaluate(double value) {
		return this.generate().evaluate(value);
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) return new ConstantNode(0.0d);
		return new ProductNode(left.differentiate(val), new ProductNode(new QuotientNode(new AdditionNode(new AdditionNode(a, b), new AdditionNode(n, new ConstantNode(1.0d))), new ConstantNode(2.0d)), new JacobiPNode(new SubtractionNode(n, new ConstantNode(1.0d)), new AdditionNode(a, new ConstantNode(1.0d)), new AdditionNode(b, new ConstantNode(1.0d)), left)));
	}
}
