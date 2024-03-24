package Easy;

import java.util.*;

public class PermutationSwaps {

    /*Permutation Swaps!

    Rishabh has a permutation A of N integers 1, 2, ... N but he doesn't like it. Rishabh wants to get a permutation B.

Also, Rishabh has some M good pairs given in a form of 2D matrix C of size M x 2  where (C[i][0], C[i][1]) denotes that two indexes of the permutation A.

In one operation he can swap Ax and Ay only if (x, y) is a good pair.

You have to tell whether Rishabh can obtain permutation B by performing the above operation any number of times on permutation A.

If the permutation B can be obtained return 1 else return 0.

Example Input
Input 1: Output - 0

 A = [1, 3, 2, 4]
 B = [1, 4, 2, 3]
 C = [
        [3, 4]
     ]
Input 2: Output -1

 A = [1, 3, 2, 4]
 B = [1, 4, 2, 3]
 C = [
        [2, 4]
     ]

     */

    public static void main(String[] args) {
        PermutationSwaps permutationSwaps = new PermutationSwaps();
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(7, 29, 51, 54, 24, 27, 70, 46, 49, 21, 1, 65, 10, 11, 4, 63, 69, 30, 43, 32, 71, 33, 3, 40, 14, 12, 31, 39, 26, 48, 13, 67, 64, 34, 17, 36, 16, 58, 6, 5, 15, 35, 9, 56, 55, 25, 61, 50, 22, 45, 72, 57, 47, 53, 44, 37, 62, 20, 38, 8, 18, 28, 23, 42, 66, 59, 41, 2, 60, 52, 68, 19));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(36, 29, 2, 54, 21, 48, 72, 26, 41, 24, 39, 65, 10, 51, 4, 63, 47, 45, 31, 32, 18, 15, 3, 61, 14, 37, 53, 1, 25, 27, 5, 67, 49, 60, 11, 7, 46, 43, 68, 9, 33, 16, 44, 56, 13, 50, 40, 35, 20, 30, 6, 59, 69, 58, 55, 12, 62, 22, 70, 8, 64, 34, 23, 42, 66, 71, 57, 38, 28, 52, 17, 19));
        ArrayList<ArrayList<Integer>> C = new ArrayList<>();
        int[][] cArr = {
                {3, 67},
                {12, 32},
                {39, 69},
                {45, 65},
                {62, 71},
                {26, 59},
                {22, 41},
                {37, 42},
                {25, 30},
                {51, 67},
                {19, 27},
                {7, 67},
                {16, 32},
                {29, 37},
                {35, 62},
                {24, 47},
                {35, 71},
                {19, 54},
                {69, 71},
                {4, 57},
                {33, 52},
                {9, 52},
                {52, 61},
                {17, 53},
                {12, 36},
                {2, 15},
                {47, 63},
                {63, 72},
                {34, 71},
                {31, 65},
                {34, 35},
                {14, 51},
                {15, 53},
                {1, 32},
                {25, 70},
                {11, 23},
                {5, 10},
                {8, 48},
                {30, 70},
                {20, 23},
                {12, 16},
                {37, 48},
                {2, 53},
                {38, 54},
                {20, 28},
                {5, 60},
                {1, 12},
                {3, 51},
                {19, 38},
                {62, 69},
                {49, 64},
                {40, 43},
                {16, 36},
                {33, 61},
                {18, 50},
                {56, 59},
                {6, 25},
                {41, 46},
                {35, 69},
                {39, 71},
                {24, 63},
                {21, 61},
                {44, 57},
                {8, 37},
                {39, 62},
                {43, 55},
                {51, 68},
                {40, 55},
                {4, 44},
                {27, 38},
                {49, 58},
                {31, 45},
                {9, 33},
                {27, 54},
        };


        for (int i = 0; i < cArr.length; i++) {
            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(cArr[i][0]);
            curr.add(cArr[i][1]);
            C.add(curr);
        }

        System.out.println(permutationSwaps.solve(A, B, C));
    }

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> C) {

        Map<Integer, Integer> position = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            position.put(A.get(i), i);
        }

        Map<Integer, List<Integer>> goodPairs = new HashMap<>();
        for (int i = 0; i < A.size(); i++) {
            goodPairs.put(i, new ArrayList<>());
        }

        for (int i = 0; i < C.size(); i++) {
            goodPairs.get(C.get(i).get(0) - 1).add(C.get(i).get(1) - 1);
            goodPairs.get(C.get(i).get(1) - 1).add(C.get(i).get(0) - 1);
        }

        int[] visited = new int[A.size()];
        int parentNode = 1;
        for (int i = 0; i < A.size(); i++) {
            if (visited[i] == 0) {
                connect(goodPairs, visited, i, parentNode);
                parentNode++;
            }
        }

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != B.get(i)) {
                if (visited[i] != visited[position.get(B.get(i))]) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public void connect(Map<Integer, List<Integer>> goodPairs, int[] visited, int node, int parentNode) {
        if (visited[node] != 0) {
            return;
        }

        visited[node] = parentNode;

        for (int neighbour : goodPairs.get(node)) {
            connect(goodPairs, visited, neighbour, parentNode);
        }

        return;
    }
}
