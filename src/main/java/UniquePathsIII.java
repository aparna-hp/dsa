class UniquePathsIII {

    public static void main(String[] args) {
        UniquePathsIII uniquePathsIII = new UniquePathsIII();
        int[][] grid = {{-1,0,1,-1},{2,0,0,0}};
        System.out.println(uniquePathsIII.uniquePathsIII(grid));
    }

    public int uniquePathsIII(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        int[] startIndex = findStartIndex(grid);
        return findPaths(grid, startIndex[0], startIndex[1], startIndex[2], ""+ startIndex[0] + startIndex[1] + ",");
    }

    public int[] findStartIndex(int[][] grid){
        int[] startIndex = new int[3];
        int numBoxesToVisit = 0;
        for(int m =0; m<grid.length; m++) {
            for(int n=0; n<grid[0].length; n++) {
                if(grid[m][n] == 1) {
                    startIndex[0] = m;
                    startIndex[1] = n;
                }
                else if(grid[m][n] != -1) {
                    numBoxesToVisit++;
                }
            }
        }
        startIndex[2] = numBoxesToVisit;
        return startIndex;
    }

    public int findPaths(int[][] grid, int m, int n, int numBoxesToVisit, String visitedBoxes ) {
        if(m >= grid.length || n >= grid[0].length || m <0 || n<0 || grid[m][n] == -1) {
            return 0;
        }

        if(grid[m][n] == 2){
            System.out.println("visitedBoxes" + visitedBoxes);
            if(visitedBoxes.split(",").length == numBoxesToVisit+1) {
                return 1;
            }
            return 0;
        }

        int a=0;

        String currIndexStr = "" + (m+1) +n + ",";
        if(!visitedBoxes.contains(currIndexStr)) {
            a= findPaths(grid, m+1, n, numBoxesToVisit,visitedBoxes + currIndexStr );
        }

        currIndexStr =""+ m+ (n+1) + ",";
        if(!visitedBoxes.contains(currIndexStr)) {
            a += findPaths(grid, m, n+1, numBoxesToVisit,visitedBoxes +currIndexStr);
        }

        currIndexStr = ""+ (m-1)+ n + ",";
        if(!visitedBoxes.contains(currIndexStr)) {
            a += findPaths(grid, m-1, n, numBoxesToVisit,visitedBoxes +currIndexStr);
        }

        currIndexStr =""+  m + (n-1) + ",";
        if(!visitedBoxes.contains(currIndexStr)) {
            a += findPaths(grid, m, n-1, numBoxesToVisit,visitedBoxes +currIndexStr);
        }
        return a;
    }

}