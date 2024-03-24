package medium;

import java.util.Stack;

public class First2ndMaxXor {

    public static void main(String[] args){
        int[] arr = {9, 8, 3, 5, 7};
        First2ndMaxXor first2ndMaxXor = new First2ndMaxXor();
        System.out.println(first2ndMaxXor.findMaxXorFirstSecondMax(arr));
    }

    public int findMaxXorFirstSecondMax(int[] arr){
        int[] rle = new int[arr.length];
        int[] lle = new int[arr.length];

        Stack<Integer> monotonic = new Stack<>();
        for(int i=0; i<arr.length; i++){
            while(!monotonic.isEmpty() && monotonic.peek() <= arr[i]){
                monotonic.pop();
            }

            if(monotonic.isEmpty()){
                lle[i] = -1;
            } else {
                lle[i] = monotonic.peek();
            }

            monotonic.push(arr[i]);
        }

        monotonic = new Stack<>();
        for(int i=arr.length-1; i>0; i--){
            while(!monotonic.isEmpty() && monotonic.peek() <= arr[i]){
                monotonic.pop();
            }

            if(monotonic.isEmpty()){
                rle[i] = -1;
            } else {
                rle[i] = monotonic.peek();
            }

            monotonic.push(arr[i]);
        }

        int result = 0;
        for(int i=0; i<arr.length; i++){
            int left = Integer.MIN_VALUE;
            if(lle[i] != -1) {
                left = arr[i] ^ lle[i];
            }

            int right = Integer.MIN_VALUE;
            if(rle[i] != -1){
                right = arr[i] ^ rle[i];
            }

            result = Math.max(result, Math.max(left, right));
        }
        return result;
    }
}
