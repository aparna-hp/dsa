package medium;

import java.util.*;

public class MinCostPath {

   HashMap<String, Integer> dp;

    public static void main(String[] args) {
        MinCostPath minCostPath = new MinCostPath();
        int[][] heights = {{4,3,4,10,5,5,9,2},{10,8,2,10,9,7,5,6},{5,8,10,10,10,7,4,2},{5,1,3,1,1,3,1,9},{6,4,10,6,10,9,4,6}};
        //int[][] heights = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println(minCostPath.minimumEffortPath(heights));
        System.out.println(minCostPath.minPathDikstra(heights));
    }

    public int minimumEffortPath(int[][] heights) {
        dp = new HashMap<>();
        return minPath(heights,0,0,0,"");
    }

    public int minPath(int[][] heights, int i, int j, int effort, String path) {
        if(i==heights.length && j == heights[0].length) {
            return effort;
        }

        String currPath = path + i + "," + j + ",";
        if(dp.containsKey(currPath)) {
            return dp.get(currPath);
        }
        int a = Integer.MAX_VALUE;
        if(i > 0 && !path.contains(","  + (i-1) + "," +j + ",")) {
            a = Math.min(a, minPath(heights, i - 1, j,  Math.max(effort,Math.abs(heights[i][j]- heights[i-1][j])),
                    currPath));
        }
        if(i < heights.length-1 && !path.contains("," +(i+1) + "," +j + ",")) {
            a = Math.min(a, minPath(heights, i + 1, j,  Math.max(effort,Math.abs(heights[i][j]- heights[i+1][j])),
                    currPath));
        }

        if(j >0 && !path.contains(","+i + "," + (j-1) + ",")) {
           a = Math.min(a, minPath(heights, i, j - 1, Math.max(effort,Math.abs(heights[i][j]- heights[i][j-1])),
                   currPath));
        }

        if(j < heights[0].length-1 && !path.contains(","+i + "," + (j+1) + ",")) {
            a = Math.min(a, minPath(heights, i, j + 1, Math.max(effort,Math.abs(heights[i][j]- heights[i][j+1])),
                    currPath));
        }

        if(a == Integer.MAX_VALUE){
            dp.put(currPath,effort);
            return effort;
        }
        dp.put(currPath, a);
        return a;
    }

    public int minPathDikstra(int[][] heights){
        int[][] pathEffort = new int[heights.length][heights[0].length];
        for(int i=0; i< heights.length; i++) {
            Arrays.fill(pathEffort[i], Integer.MAX_VALUE);
        }
        pathEffort[0][0]=0;

        PriorityQueue<Path> queue = new PriorityQueue<>(Comparator.comparing(Path::getEffort));
        queue.add(new Path(0,0,0));
        while(!queue.isEmpty()) {
            Path curr = queue.poll();
            if(curr.i == heights.length-1 && curr.j == heights[0].length-1) {
                return curr.effort;
            }

            if(curr.i >0) {
                int effort = Math.abs( heights[curr.i][curr.j] - heights[curr.i-1][curr.j]);
                effort = Math.max(effort, curr.effort);
                if(effort < pathEffort[curr.i-1][curr.j]) {
                    queue.add(new Path(curr.i-1, curr.j, effort));
                    pathEffort[curr.i-1][curr.j] = effort;
                }
            }

            if(curr.i < heights.length-1) {
                int effort = Math.abs( heights[curr.i][curr.j] - heights[curr.i+1][curr.j]);
                effort = Math.max(effort, curr.effort);
                if(effort < pathEffort[curr.i+1][curr.j]) {
                    queue.add(new Path(curr.i+1, curr.j, effort));
                    pathEffort[curr.i+1][curr.j] = effort;
                }
            }

            if(curr.j >0) {
                int effort = Math.abs( heights[curr.i][curr.j] - heights[curr.i][curr.j-1]);
                effort = Math.max(effort, curr.effort);
                if(effort < pathEffort[curr.i][curr.j-1]) {
                    queue.add(new Path(curr.i, curr.j-1, effort));
                    pathEffort[curr.i][curr.j-1] = effort;
                }
            }

            if(curr.j < heights[0].length-1) {
                int effort = Math.abs( heights[curr.i][curr.j+1] - heights[curr.i][curr.j]);
                effort = Math.max(effort, curr.effort);
                if(effort < pathEffort[curr.i][curr.j+1]) {
                    queue.add(new Path(curr.i, curr.j+1, effort));
                    pathEffort[curr.i][curr.j+1] = effort;
                }
            }
        }

        return 0;

    }

    public static class Path{
        int i, j, effort;
        public Path(int i, int j, int effort){
            this.i = i;
            this.j = j;
            this.effort = effort;
        }
        public int getEffort(){
            return this.effort;
        }
    }
}
