package medium;

import java.util.*;

public class MaxIntUsingBSwap {

    public static void main(String[] args){
        ArrayList<Integer> A = new ArrayList<>();
        A.add(1 );
        A.add(2);
        A.add(3);
        A.add(4);
        MaxIntUsingBSwap maxIntUsingBSwap = new MaxIntUsingBSwap();
        maxIntUsingBSwap.solve(A, 1);

    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {
            int N = A.size();
            Map<Integer, Integer> map = new HashMap<>();
            for(int i =0; i<N; i++) {
                map.put(A.get(i), i);
            }

            int temp = 0;
            for(int i=0; i<N && temp < B; i++){
                if(A.get(i) != (N-i)) {
                    int curr = A.get(i);
                    temp++;
                    int swapIndex = map.get(N-i);
                    Collections.swap(A, i, swapIndex);
                    map.put(N-i, i);
                    map.put(curr, swapIndex);
                }
            }
            System.out.println("Output " + Arrays.toString(A.toArray()));
            return A;
        }

}
