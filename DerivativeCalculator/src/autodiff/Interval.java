package autodiff;

public class Interval {
	public double min;
	public double max;
	public boolean lower;
	public boolean upper;
	public double n = -Double.MAX_VALUE; // for intervals that are all but a specific value
	public Interval(double min, double max, boolean lower, boolean upper) {
		this.min = min;
		this.max = max;
		this.lower = lower;
		this.upper = upper;
	}
	public Interval(double n) {
		this.n = n;
	}
	public boolean contains(double value) {
		if(n != -Double.MAX_VALUE) return value != n;
		if(lower && upper) {
			return min <= value && value <= max;
		} else if (lower) {
			return min <= value && value < max;
		} else if (upper) {
			return min < value && value <= max;
		} else {
			return min < value && value < max;
		}
	}
}