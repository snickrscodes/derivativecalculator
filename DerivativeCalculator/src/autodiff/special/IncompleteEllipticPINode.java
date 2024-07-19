package autodiff.special;

import java.util.HashMap;
import autodiff.*;
import autodiff.math.*;
import functions.Functions;

public class IncompleteEllipticPINode extends Node {
	public Node center;
	public IncompleteEllipticPINode(Node left, Node center, Node right) {
		super(left, right);
		this.center = center;
		// left = n
		// center = x
		// right = m
		this.op = "elliptic_pi(%s, " + center.toString() + ", %s)";
	}
	public double evaluate(HashMap<Node, Double> values) {
		return Functions.elliptic_pi_integral(left.evaluate(values), center.evaluate(values), right.evaluate(values));
	}
	public double evaluate(double value) {
		return Functions.elliptic_pi_integral(left.evaluate(value), center.evaluate(value), right.evaluate(value));
	}
	public Node differentiate(Variable val) {
		if(left.isConstant(val)) {
			if(center.isConstant(val)) {
				if(right.isConstant(val)) {
					return new ConstantNode(0.0d);
				} else {
				return new QuotientNode(new ProductNode(right.differentiate(val), new AdditionNode(
										new SubtractionNode(new IncompleteEllipticPINode(left, center, right),
												new QuotientNode(
														new ProductNode(
																new SinNode(new ProductNode(new ConstantNode(2.0d), center)), right
																),
														new ProductNode(
																new ProductNode(new ConstantNode(2.0d), new SubtractionNode(right, new ConstantNode(1.0d))),
																new SqrtNode(
																		new SubtractionNode(new ConstantNode(1.0d), 
																				new ProductNode(
																						new ExponentNode(new SinNode(center), new ConstantNode(2.0d)),
																						right
																						)
																				)
																		)
																)
														)
												), 
										new QuotientNode(new IncompleteEllipticENode(center, right), new SubtractionNode(right, new ConstantNode(1.0d)))
										)
								),
						new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, right))
						);
				}
			} else {
				if(right.isConstant(val)) {
					return new QuotientNode(
							center.differentiate(val),
							new ProductNode(
									new SubtractionNode(
											new ConstantNode(1.0d), 
												new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))),
												new SqrtNode(
														new SubtractionNode(
																new ConstantNode(1.0d),
																new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																)
														)
									
									)
							);
				} else {
					return new AdditionNode(
							new QuotientNode(
									center.differentiate(val),
									new ProductNode(
											new SubtractionNode(
													new ConstantNode(1.0d), 
														new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))),
														new SqrtNode(
																new SubtractionNode(
																		new ConstantNode(1.0d),
																		new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																		)
																)
											
											)
									),
							new QuotientNode(
									new ProductNode(right.differentiate(val),
											new AdditionNode(
													new SubtractionNode(
															new IncompleteEllipticPINode(left, center, right),
															new QuotientNode(
																	new ProductNode(
																			new SinNode(new ProductNode(new ConstantNode(2.0d), center)), right
																			),
																	new ProductNode(
																			new ProductNode(new ConstantNode(2.0d), new SubtractionNode(right, new ConstantNode(1.0d))),
																			new SqrtNode(
																					new SubtractionNode(new ConstantNode(1.0d), 
																							new ProductNode(
																									new ExponentNode(new SinNode(center), new ConstantNode(2.0d)),
																									right
																									)
																							)
																					)
																			)
																	)
															), 
													new QuotientNode(new IncompleteEllipticENode(center, right), new SubtractionNode(right, new ConstantNode(1.0d)))
													)
											),
									new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, right))
									)
							
							
							);
				}
			}
		} else {
			if(center.isConstant(val)) {
				if(right.isConstant(val)) {
					return new QuotientNode(
							new ProductNode(left.differentiate(val), 
									new AdditionNode(
											new SubtractionNode(
													new QuotientNode(
															new ProductNode(
																	new SubtractionNode(right, left),
																	new IncompleteEllipticFNode(center, right)
																	),
															left
															),
													new QuotientNode(
															new ProductNode(
																	new ProductNode(left, new SinNode(new ProductNode(new ConstantNode(2.0d), center))),
																	new SqrtNode(
																			new SubtractionNode(
																					new ConstantNode(1.0d),
																					new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																					)
																			)
																	),
															new ProductNode(
																	new ConstantNode(2.0d),
																	new SubtractionNode(
																			new ConstantNode(1.0d),
																			new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																			)
																	)
															)
													),
											new AdditionNode(
													new QuotientNode(new ProductNode(
															new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), right),
															new IncompleteEllipticPINode(left, center, right)
															), left),
													new IncompleteEllipticENode(center, right)
													)
											)
									),
							new ProductNode(new ConstantNode(2.0d), 
									new ProductNode(
											new SubtractionNode(left, new ConstantNode(1.0d)),
											new SubtractionNode(right, left)
											)
									)
							);
				} else {
					return new AdditionNode(
							new QuotientNode(
									new ProductNode(left.differentiate(val), 
											new AdditionNode(
													new SubtractionNode(
															new QuotientNode(
																	new ProductNode(
																			new SubtractionNode(right, left),
																			new IncompleteEllipticFNode(center, right)
																			),
																	left
																	),
															new QuotientNode(
																	new ProductNode(
																			new ProductNode(left, new SinNode(new ProductNode(new ConstantNode(2.0d), center))),
																			new SqrtNode(
																					new SubtractionNode(
																							new ConstantNode(1.0d),
																							new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																							)
																					)
																			),
																	new ProductNode(
																			new ConstantNode(2.0d),
																			new SubtractionNode(
																					new ConstantNode(1.0d),
																					new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																					)
																			)
																	)
															),
													new AdditionNode(
															new QuotientNode(new ProductNode(
																	new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), right),
																	new IncompleteEllipticPINode(left, center, right)
																	), left),
															new IncompleteEllipticENode(center, right)
															)
													)
											),
									new ProductNode(new ConstantNode(2.0d), 
											new ProductNode(
													new SubtractionNode(left, new ConstantNode(1.0d)),
													new SubtractionNode(right, left)
													)
											)
									),
							new QuotientNode(
									new ProductNode(right.differentiate(val),
											new AdditionNode(
													new SubtractionNode(
															new IncompleteEllipticPINode(left, center, right),
															new QuotientNode(
																	new ProductNode(
																			new SinNode(new ProductNode(new ConstantNode(2.0d), center)), right
																			),
																	new ProductNode(
																			new ProductNode(new ConstantNode(2.0d), new SubtractionNode(right, new ConstantNode(1.0d))),
																			new SqrtNode(
																					new SubtractionNode(new ConstantNode(1.0d), 
																							new ProductNode(
																									new ExponentNode(new SinNode(center), new ConstantNode(2.0d)),
																									right
																									)
																							)
																					)
																			)
																	)
															), 
													new QuotientNode(new IncompleteEllipticENode(center, right), new SubtractionNode(right, new ConstantNode(1.0d)))
													)
											),
									new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, right))
									)
							);
				}
			} else {
				if(right.isConstant(val)) {
					return new AdditionNode(
							new QuotientNode(
									new ProductNode(left.differentiate(val), 
											new AdditionNode(
													new SubtractionNode(
															new QuotientNode(
																	new ProductNode(
																			new SubtractionNode(right, left),
																			new IncompleteEllipticFNode(center, right)
																			),
																	left
																	),
															new QuotientNode(
																	new ProductNode(
																			new ProductNode(left, new SinNode(new ProductNode(new ConstantNode(2.0d), center))),
																			new SqrtNode(
																					new SubtractionNode(
																							new ConstantNode(1.0d),
																							new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																							)
																					)
																			),
																	new ProductNode(
																			new ConstantNode(2.0d),
																			new SubtractionNode(
																					new ConstantNode(1.0d),
																					new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																					)
																			)
																	)
															),
													new AdditionNode(
															new QuotientNode(new ProductNode(
																	new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), right),
																	new IncompleteEllipticPINode(left, center, right)
																	), left),
															new IncompleteEllipticENode(center, right)
															)
													)
											),
									new ProductNode(new ConstantNode(2.0d), 
											new ProductNode(
													new SubtractionNode(left, new ConstantNode(1.0d)),
													new SubtractionNode(right, left)
													)
											)
									),
							new QuotientNode(
									center.differentiate(val),
									new ProductNode(
											new SubtractionNode(
													new ConstantNode(1.0d), 
														new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))),
														new SqrtNode(
																new SubtractionNode(
																		new ConstantNode(1.0d),
																		new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																		)
																)
											
											)
									)
							
							);
				} else {
					return new AdditionNode(
							new AdditionNode(
									new QuotientNode(
											new ProductNode(left.differentiate(val), 
													new AdditionNode(
															new SubtractionNode(
																	new QuotientNode(
																			new ProductNode(
																					new SubtractionNode(right, left),
																					new IncompleteEllipticFNode(center, right)
																					),
																			left
																			),
																	new QuotientNode(
																			new ProductNode(
																					new ProductNode(left, new SinNode(new ProductNode(new ConstantNode(2.0d), center))),
																					new SqrtNode(
																							new SubtractionNode(
																									new ConstantNode(1.0d),
																									new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																									)
																							)
																					),
																			new ProductNode(
																					new ConstantNode(2.0d),
																					new SubtractionNode(
																							new ConstantNode(1.0d),
																							new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																							)
																					)
																			)
																	),
															new AdditionNode(
																	new QuotientNode(new ProductNode(
																			new SubtractionNode(new ExponentNode(left, new ConstantNode(2.0d)), right),
																			new IncompleteEllipticPINode(left, center, right)
																			), left),
																	new IncompleteEllipticENode(center, right)
																	)
															)
													),
											new ProductNode(new ConstantNode(2.0d), 
													new ProductNode(
															new SubtractionNode(left, new ConstantNode(1.0d)),
															new SubtractionNode(right, left)
															)
													)
											),
									new QuotientNode(
											center.differentiate(val),
											new ProductNode(
													new SubtractionNode(
															new ConstantNode(1.0d), 
																new ProductNode(left, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))),
																new SqrtNode(
																		new SubtractionNode(
																				new ConstantNode(1.0d),
																				new ProductNode(right, new ExponentNode(new SinNode(center), new ConstantNode(2.0d)))
																				)
																		)
													
													)
											)
									
									),
							
							new QuotientNode(
									new ProductNode(right.differentiate(val),
											new AdditionNode(
													new SubtractionNode(
															new IncompleteEllipticPINode(left, center, right),
															new QuotientNode(
																	new ProductNode(
																			new SinNode(new ProductNode(new ConstantNode(2.0d), center)), right
																			),
																	new ProductNode(
																			new ProductNode(new ConstantNode(2.0d), new SubtractionNode(right, new ConstantNode(1.0d))),
																			new SqrtNode(
																					new SubtractionNode(new ConstantNode(1.0d), 
																							new ProductNode(
																									new ExponentNode(new SinNode(center), new ConstantNode(2.0d)),
																									right
																									)
																							)
																					)
																			)
																	)
															), 
													new QuotientNode(new IncompleteEllipticENode(center, right), new SubtractionNode(right, new ConstantNode(1.0d)))
													)
											),
									new ProductNode(new ConstantNode(2.0d), new SubtractionNode(left, right))
									)
							);
				}
			}
		}
	}
}
