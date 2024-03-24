package medium;

import java.util.*;

public class FactorTree {
    public static void main(String[] args) {
        FactorTree factorTree = new FactorTree();
        int[] arr = {18,3,6,2};
        System.out.println(factorTree.numFactoredBinaryTrees(arr));
        int M = 1000003;
        System.out.println( Math.pow(2, 40)%M);
        System.out.println( Math.pow(2, 40));

    }

    public int numFactoredBinaryTrees(int[] arr) {
        int M = 1000000007;
        if (arr.length == 1) {
            return 1;
        }

        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();

        for(int i =0; i<arr.length; i++){
            map.put(arr[i],1);
        }

        for (int i = 1; i < arr.length; i++) {
            int way = 0;
            for (int j = 0; j < i; j++) {
                int left, right ;
                int rightChild = arr[i]/arr[j];
                if ((arr[i] % arr[j]) == 0 && map.containsKey(rightChild)) {
                    left = map.get(arr[j]);
                    right = map.get(rightChild);
                    way += (left * right);
                }
            }
            map.put(arr[i], map.get(arr[i]) + way);
            System.out.println(" way " + way);
        }

        int result = 0;
        for(Integer key : map.keySet()){
            result += map.get(key);
        }
        return result;
    }

}
