package outside;

/*
    Given three sorted arrays A, B  and Cof not necessarily same sizes.

    Calculate the minimum absolute difference between the maximum and minimum number from the triplet a, b, c such that a, b, c belongs arrays A, B, C respectively.

    i.e. minimize | max(a,b,c) - min(a,b,c) |.

    Example :

    Input:

    A : [ 1, 4, 5, 8, 10 ]
    B : [ 6, 9, 15 ]
    C : [ 2, 3, 6, 6 ]
    Output:

    1
    Explanation: We get the minimum difference for a=5, b=6, c=6 as | max(a,b,c) - min(a,b,c) | = |6-5| = 1.
 */

import java.util.*;

public class MinMaxDiffernce {

    public static void main(String[] args) {

        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(38, 79, 101, 103, 124, 137, 170, 199, 220, 224, 249, 297, 325, 347, 355, 376, 400, 435, 447, 490, 530, 533, 533, 578, 600, 609, 612, 637, 666, 668, 674, 704, 747, 748, 796, 827, 832, 839, 882, 924, 931, 980, 995, 1000, 1019, 1044, 1059, 1104, 1143, 1171, 1208, 1247, 1294, 1330, 1342, 1377, 1378, 1392, 1438, 1459, 1481, 1498, 1545, 1565, 1608, 1636));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(-125, -99, -63, -16, 4, 18, 28, 65, 108, 109, 114, 139, 145, 159, 161, 194, 235, 262, 265, 290, 318, 325, 350, 393, 403, 442, 460, 484, 515, 533, 577, 612, 639, 660, 682, 689, 708, 712, 712, 744, 766, 797, 824, 867, 905, 928, 971, 982, 993, 1032, 1072, 1107, 1146, 1153, 1166, 1197, 1197, 1223, 1267, 1308, 1353, 1379, 1389));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(66, 86, 98, 99, 111, 134, 179, 208, 227, 260, 261, 300, 340, 360, 376, 404, 416, 441, 458, 461, 497, 511, 523, 572, 588, 618, 633, 681, 719, 759, 804, 809, 840, 876, 897, 942, 984, 1002, 1022, 1071, 1079, 1116, 1130, 1173, 1220, 1241, 1287, 1288, 1310, 1344, 1377, 1398, 1416, 1458, 1481, 1516, 1520, 1553, 1579, 1619, 1655, 1656, 1687, 1712, 1754, 1778, 1798, 1805, 1852, 1899, 1934, 1960, 1997, 2043, 2074, 2085, 2132, 2180, 2210, 2252, 2297, 2343, 2360, 2366, 2388, 2392, 2428, 2462, 2466, 2488, 2506, 2538, 2584, 2611, 2635, 2665, 2706));

        System.out.println("result  " + MinMaxDiffernce.solve(A, B, C) );
    }

    public static int solveSort(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {

        int result = Integer.MAX_VALUE;

        Integer a = null, b = null, c = null;
        ArrayList<Integer> sortedList = new ArrayList<>(A);
        sortedList.addAll(B);
        sortedList.addAll(C);

        Collections.sort(sortedList);

        for (int sortedEle : sortedList) {
            if (A.contains(sortedEle)) {
                a = sortedEle;
            }

            if (B.contains(sortedEle)) {
                b = sortedEle;
            }

            if (C.contains(sortedEle)) {
                c = sortedEle;
            }

            if (a != null && b != null && c != null) {
                int max = Math.max(Math.max(a, b), c);
                int min = Math.min(Math.min(a, b), c);
                result = Math.min(Math.abs(max - min), result);
                System.out.println(" a = " + a + " b = "  + b + " c = " + c + " diff = " + Math.abs(max - min));
            }
        }

        return result;
    }

    public static int solve(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C) {

        int result = Integer.MAX_VALUE;
        int i = A.size()-1;
        int j = B.size()-1;
        int k = C.size()-1;

        while(i>=0 && j>=0 && k>=0){
            int a = A.get(i);
            int b = B.get(j);
            int c = C.get(k);

            int max = Math.max(Math.max(a, b), c);
            int min = Math.min(Math.min(a, b), c);

            result = Math.min(Math.abs(max - min), result);

            if(max == a){
                i--;
            } else if(max == b){
                j--;
            } else {
                k--;
            }
        }

        return result;
    }

}
