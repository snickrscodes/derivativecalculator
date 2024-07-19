package test;
import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import autodiff.special.*;
public class DerivativeTest {
	public static void main(String[] args) {
		Variable x = new Variable("x");
		Variable y = new Variable("y");
		Variable z = new Variable("z");
		// TESTING TIME
		Node f1 = f1(x, y, z);
		Node f2 = f2(x, y, z);
		Node f3 = f3(x, y, z);
		HashMap<Node, Double> values = new HashMap<Node, Double>();
		values.put(x, 0.3429d);
		values.put(y, 0.5871d);
		values.put(z, 0.7021d);
		System.out.println("Evaluations: ");
		System.out.println("f1(0.3429, 0.5871, 0.7021) = " + f1.evaluate(values));
		System.out.println("f2(0.3429, 0.5871, 0.7021) = " + f2.evaluate(values));
		System.out.println("f3(0.3429, 0.5871, 0.7021) = " + f3.evaluate(values));
		System.out.println("Derivatives: ");
		System.out.println("d/dx f1(x, y, z) = " + f1.differentiate(x));
		System.out.println("d/dy f2(x, y, z) = " + f2.differentiate(y));
		System.out.println("d/dz f3(x, y, z) = " + f3.differentiate(z));
	}
    // 1. f1(x, y, z) = sinh(x) * log_2(y) / (exp(z) - tanh(x + y))
    public static Node f1(Node x, Node y, Node z) {
        return new QuotientNode(
            new ProductNode(
                new SinhNode(x),
                new LogNode(new ConstantNode(2.0d), y)
            ),
            new SubtractionNode(
                new ExpNode(z),
                new TanhNode(new AdditionNode(x, y))
            )
        );
    }

    // 2. f2(x, y, z) = acos(x^2 + y^2) * ln(z) + atan(x / y) - cosh(x * y * z)
    public static Node f2(Node x, Node y, Node z) {
        return new AdditionNode(
            new ProductNode(
                new AcosNode(new AdditionNode(new SquareNode(x), new SquareNode(y))),
                new LogNode(z)
            ),
            new SubtractionNode(
                new AtanNode(new QuotientNode(x, y)),
                new CoshNode(new ProductNode(new ProductNode(x, y), z))
            )
        );
    }

    // 3. f3(x, y, z) = (x^3 + y^3) / sqrt(z) + sinh(ln(x) * cos(y)) - exp(tanh(x + z))
    public static Node f3(Node x, Node y, Node z) {
        return new AdditionNode(
            new QuotientNode(
                new AdditionNode(
                    new CubeNode(x),
                    new CubeNode(y)
                ),
                new SqrtNode(z)
            ),
            new SubtractionNode(
                new SinhNode(
                    new ProductNode(
                        new LogNode(x),
                        new CosNode(y)
                    )
                ),
                new ExpNode(new TanhNode(new AdditionNode(x, z)))
            )
        );
    }
}
