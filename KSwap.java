package Easy;

import java.util.*;

public class KSwap {

    public static void main(String[] args){
        KSwap kSwap = new KSwap();
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, 2, 4, 1, 5 ));
        kSwap.solve(A, 3);
        System.out.println("Array " + Arrays.toString(A.toArray()));

    }

    public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {

        Map<Integer,Integer> position = new HashMap<>();
        for(int i=0; i<A.size(); i++){
            position.put(A.get(i), i);
        }

        ArrayList<Integer> sorted = new ArrayList<>(A);
        sorted.sort(Comparator.reverseOrder());

        for(int i=0; i<A.size() && B > 0; i++){
            if(!sorted.get(i).equals(A.get(i))){
                int swapPos = position.get(sorted.get(i));
                position.put(sorted.get(i), i);
                position.put(A.get(i), swapPos);
                Collections.swap(A, i, swapPos);

                B--;
            }
        }

        return A;
    }
}
