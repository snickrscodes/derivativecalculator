package autodiff;
import java.util.HashMap;
import autodiff.special.*;
import autodiff.math.*;

public class Main {
	public static void main(String[] args) {
		Variable x = new Variable("x");
		Variable y = new Variable("y");
		HashMap<Node, Double> values = new HashMap<Node, Double>(); 
		values.put(x, 0.69d);
		values.put(y, 0.32d);
//		Node binary_crossentropy = new ProductNode(new ConstantNode(-1.0d), new AdditionNode(new ProductNode(y, new LogNode(x)), new ProductNode(new SubtractionNode(new ConstantNode(1.0d), y), new LogNode(new SubtractionNode(new ConstantNode(1.0d), x)))));
		// Function 1: f(x, y) = sin(x) * cosh(y) + e^(xy) * log(x + y)
//		Node function1 = new AdditionNode(new ProductNode(new SinNode(x), new CoshNode(y)), new ProductNode(new ExpNode(new ProductNode(x, y)), new LogNode(new AdditionNode(x, y))));
//		// Function 2: g(x, y) = atan(x) * sinh(y) + x^2 * cos(y) - y * log(x)
//		Node function2 = new AdditionNode(new AdditionNode(new ProductNode(new AtanNode(x), new SinhNode(y)), new ProductNode(new ExponentNode(x, new ConstantNode(2.0)), new CosNode(y))), new ProductNode(new ConstantNode(-1.0), new ProductNode(y, new LogNode(x))));
//		// Function 3: h(x, y) = atan(x + y) * sqrt(x^2 + y^2) + cosh(xy) - asin(x) * e^y
//		Node function3 = new AdditionNode(new AdditionNode(new ProductNode(new AtanNode(new AdditionNode(x, y)), new SqrtNode(new AdditionNode(new ExponentNode(x, new ConstantNode(2.0)), new ExponentNode(y, new ConstantNode(2.0))))), new CoshNode(new ProductNode(x, y))), new ProductNode(new ConstantNode(-1.0), new ProductNode(new AsinNode(x), new ExpNode(y))));
//		System.out.println("Evaluations: x = 0.69, y = 0.32");
//		System.out.println("Function 1: " + function1.evaluate(values) + " Expected: " + 0.681815743108d);
//		System.out.println("Function 2: " + function2.evaluate(values) + " Expected: " + 0.76726139583d);
//		System.out.println("Function 3: " + function3.evaluate(values) + " Expected: " + 0.57695926798d);
//		System.out.println("Derivatives: x = 0.69, y = 0.32");
//		System.out.println("Function 1: " + function1.differentiate(x).evaluate(values) + " Expected: " + 2.04976946497d);
//		System.out.println("Function 2: " + function2.differentiate(x).evaluate(values) + " Expected: " + 1.06668307063d);
//		System.out.println("Function 3: " + function3.differentiate(x).evaluate(values) + " Expected: " + -0.737850324504d);
//		Node p = new ExponentNode(new SinNode(new ExponentNode(x, new ConstantNode(2.0d))), new AtanNode(x));
//		Node p = new IncompleteEllipticENode(new SinNode(y), new ExponentNode(x, new ConstantNode(2.0d)));
//		Node p = new IncompleteEllipticPINode(x, new SinNode(x), new LogNode(x));
//		Node p = new LowerGammaNode(new ConstantNode(2.0d), x);
//		Node p = new ZetaNode(new ExponentNode(x, new ConstantNode(2.0d)), x);
//		Node p = new ChooseNode(new ConstantNode(15.0d), new ExponentNode(x, new ConstantNode(2.0d)));
//		Node p = new ChebyshevUNode(new ConstantNode(5.5d), x);
//		Node p = new PochhammerNode(new TanNode(x), x);
//		Node p = new GegenbauerCNode(new ConstantNode(5.5d), new ConstantNode(0.5d), x);
//		Node p = new LegendrePNode(new ConstantNode(5.5d), x);
//		Node p = new HermiteHNode(new ConstantNode(5.5d), x);
//		Node p = new LaguerreLNode(new ConstantNode(5.5d), x);
//		Node p = new AiryBiPrimeNode(x);
//		Node p = new DawsonFNode(x);
//		Node p = new Debye2Node(new ConstantNode(1.5d), x);
//		Node p = new Log1pNode(new ExponentNode(x, new ConstantNode(2.0d)));
//		Node p = new MultigammaNode(new ConstantNode(1.0d), new ExponentNode(x, new ConstantNode(2.0d)));
//		Node p = new TransportJNode(new ConstantNode(2.0d), new ExponentNode(x, new ConstantNode(2.0d)));
//		Node p = new FermiDiracNode(new ConstantNode(2.0d), x);
//		Node p = new JacobiPNode(new ConstantNode(5.5d), new ConstantNode(3.5d), new ConstantNode(4.5d), x);
		Node p = new SphericalBesselYNode(new ConstantNode(-1.4d), x);
		System.out.println(p);
		System.out.println(p.differentiate(x));
		System.out.println(p.evaluate(values));
		System.out.println(p.differentiate(x).evaluate(values));
	}
}
