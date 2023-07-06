package lambdasinaction._01lambda.functional.operator;

import java.util.function.IntBinaryOperator;

public class OperatorExample {
	private static int[] scores = { 92, 95, 87 };

	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);
		}
		return result; }

	public static void main(String[] args) {
		//1. Anonymous Inner Class 형태로 최대값 얻기
		int maxValue = maxOrMin(new IntBinaryOperator() {
			@Override
			public int applyAsInt(int left, int right) {
				if(left >= right) return left;
				else return right;
			}
		});
		System.out.println("maxValue = " + maxValue);
		// 최대값 얻기
		
		
		// 최소값 얻기
		
	}
}
