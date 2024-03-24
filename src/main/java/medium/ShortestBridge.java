package medium;

import java.util.*;

public class ShortestBridge {

    public static void main(String[] args) {
        // int[][] grid = {{0,1}, {1,0}};
        //int[][] grid = {{1, 1, 0, 0, 0}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = {{0,0,1,0,1},{0,1,1,0,1},{0,1,0,0,1},{0,0,0,0,0},{0,0,0,0,0}};
        ShortestBridge shortestBridge = new ShortestBridge();
        System.out.println(shortestBridge.shortestBridge(grid));
    }

    public int shortestBridge(int[][] grid) {
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    initQueue(queue, grid, i, j);
                    List<Integer> processed = new ArrayList<>();
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        System.out.println("Level " + level + " size " + size);
                        while(size > 0) {
                            size --;
                            int val = queue.poll();
                            processed.add(val);
                            int row = val / m;
                            int col = val % m;
                            System.out.println("At index " + row + "," + col);
                            if(row < n-1) {
                                if(!queue.contains((row+1)*m + col) && !processed.contains((row+1)*m + col)) {
                                    if(grid[row+1][col] == 1) {
                                        System.out.println("Found island " + (row+1) + "," + col);
                                        return level;
                                    }
                                    queue.add((row + 1) * m + col);
                                }
                            }

                            if(col < m-1) {
                                if(!queue.contains(row*m + col+1) && !processed.contains(row*m + col+1)) {
                                    if(grid[row][col+1] == 1) {
                                        System.out.println("Found island " + (row) + "," + (col+1));
                                        return level;
                                    }
                                    queue.add(row*m + col+1);
                                }
                            }

                            if(col > 0) {
                                if(!queue.contains(row*m + col-1) && !processed.contains(row*m + (col-1))) {
                                    if(grid[row][col-1] == 1) {
                                        System.out.println("Found island " + (row) + "," + (col-1));
                                        return level;
                                    }
                                    queue.add(row*m + col-1);
                                }
                            }

                            if(row > 0) {
                                if(!queue.contains((row-1)*m + col)&& !processed.contains((row-1)*m + col)) {
                                    if(grid[row-1][col] == 1) {
                                        System.out.println("Found island " + (row-1) + "," + col);
                                        return level;
                                    }
                                    queue.add((row-1)*m + col);
                                }
                            }
                        }
                        level++;
                    }
                    return level;
                }
            }
        }


        return level;
    }

    public void initQueue(Queue<Integer> queue, int[][] grid, int i, int j) {

        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] != 1) {
            return;
        }
        int val = i*grid[0].length + j;
        if(!queue.contains(val)) {
            System.out.println("Pushing value " + val);
            queue.add(val);
            initQueue(queue, grid, i + 1, j);
            initQueue(queue, grid, i - 1, j);
            initQueue(queue, grid, i , j-1);
            initQueue(queue, grid, i, j + 1);
        }
    }
}
