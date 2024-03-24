package medium;

import java.util.Arrays;

public class UniqueElements {

    public static void main(String[] args){
        UniqueElements uniqueElements = new UniqueElements();
        int[] arr = {4, 5, 4, 1, 3, 7, 6, 3, 3};
        System.out.println(uniqueElements.minIncrements(arr, 9));
    }

    public long minIncrements(int[] arr, int N) {
        int count = 0;
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        for(int i=1; i<N; i++){
            if(arr[i-1] > arr[i]) {
                int temp = arr[i-1] - arr[i] +1;
                count += temp;
                arr[i] += temp;
            } else if(arr[i] == arr[i-1]){
                count ++;
                arr[i]++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return count;
    }
}
