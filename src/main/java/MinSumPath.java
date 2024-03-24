import java.util.Arrays;

class MinSumPath {

    int[][] dp;

    public static void main(String[] agrs) {
        MinSumPath minSumPath = new MinSumPath();
        int[][] grid =  {{1,4,8,6,2,2,1,7},{4,7,3,1,4,5,5,1},{8,8,2,1,1,8,0,1},{8,9,2,9,8,0,8,9},{5,7,5,7,1,8,5,5},{7,0,9,4,5,6,5,6},{4,9,9,7,9,1,9,0}};
        System.out.println(minSumPath.minPathSum(grid));
        minSumPath.minPathSumOptimised(grid);

        int[][] grid2 = {{1,3,1},{1,5,1},{4,2,1}};
        minSumPath.minPathSumOptimised(grid2);
        System.out.println(Arrays.deepToString(minSumPath.dp));

        minSumPath.minPathSumOptimised(grid);
        System.out.println(Arrays.deepToString(minSumPath.dp));

    }

    public int minPathSum(int[][] grid) {
        return findPathSum(grid, 0,0,0);
    }

    public int minPathSumOptimised(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        dp= new int[row][col];
        dp[row-1][col-1] = grid[row-1][col-1];
        for(int i=row-2; i>=0; i--) {
            dp[i][col-1] = grid[i][col-1] + dp[i+1][col-1];
        }
        for(int j = col-2; j>=0; j--) {
            dp[row-1][j] = grid[row-1][j] + dp[row-1][j+1];
        }

            for(int j=col-2; j>=0; j--) {
                for (int i = row - 2; i >= 0; i--) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j+1], dp[i+1][j]);
                }
            }

        return dp[0][0];
    }

    public int findPathSum(int[][] grid, int i, int j, int sum ) {
        if((i == grid.length -1) && (j== grid[0].length-1)) {
            sum += grid[i][j];
            return sum;
        }

        if(i > grid.length-1 || j > grid[0].length-1) {
            return Integer.MAX_VALUE;
        }

        int right =  findPathSum(grid, i, j+1, sum + grid[i][j]);

        int down =  findPathSum(grid, i+1, j, sum + grid[i][j]);

        return Math.min(right, down);
    }
}