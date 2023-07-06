package lambdasinaction._01lambda.functional.operator;

import java.util.function.IntBinaryOperator;

public class OperatorExample {
    private static int[] scores = {92, 95, 87};

    public static int maxOrMin(IntBinaryOperator operator) {
        int result = scores[0];
        for (int score : scores) {
            result = operator.applyAsInt(result, score);
        }
        return result;
    }

    public static void main(String[] args) {
        //1. Anonymous Inner Class 형태로 최대값 얻기
        int maxValue = maxOrMin(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                if (left >= right) return left;
                else return right;
            }
        });
        System.out.println("maxValue = " + maxValue);
        //2. Lambda 식 최소값 얻기
        int minValue = maxOrMin((n1, n2) -> {
            if (n1 <= n2) return n1;
            else return n2;
        });
		System.out.println("minValue = " + minValue);

		//public static int max(int a, int b)
		maxValue = maxOrMin(Integer::max);
		System.out.println("maxValue = " + maxValue);

		//public static int min(int a, int b)
		minValue = maxOrMin(Integer::min);
		System.out.println("minValue = " + minValue);
    }
}
