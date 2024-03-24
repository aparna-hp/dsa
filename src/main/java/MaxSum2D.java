import java.util.Arrays;

public class MaxSum2D {

    static int[] dp ;


    public static void  main(String[] args) {
        int[][] matrix = {{1,4,5},{2,0,0}};
        dp =  new int[matrix[0].length];
        Arrays.fill(dp, -1);

        int col0 = maxSum(matrix, 0);
        int col1 = maxSum(matrix, 1);
        System.out.println(Math.max(col0, col1));

        System.out.println("Right to left " + maxSum(matrix));
    }

    public static int maxSum(int[][] matrix, int col) {
        if(col >= matrix[0].length) {
            return 0;
        }

        if(dp[col] != -1) {
            return dp[col];
        }

        int maxElement = Math.max(matrix[0][col], matrix[1][col]);
        dp[col] = Math.max(maxElement + maxSum(matrix, col+2), maxElement + maxSum(matrix, col+3));
        return dp[col];
    }

    public static int maxSum(int[][] matrix) {
        int[] dp = new int[matrix[0].length];
        for(int i = matrix[0].length-1; i >= 0; i--) {
            int maxElement = Math.max(matrix[0][i], matrix[1][i]);
            int pluz2 =maxElement, plus3 =maxElement;
            if(i+2 < matrix[0].length){
                pluz2 = maxElement + Math.max(matrix[0][i+2], matrix[1][i+2]);
            }
            if(i+3 < matrix[0].length) {
                plus3 = maxElement + Math.max(matrix[0][i+3], matrix[1][i+3]);
            }
            dp[i] = Math.max(pluz2, plus3);
        }

        int max = dp[0];
        for(int i =1; i < dp.length; i++){
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }
}
