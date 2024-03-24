package medium;

import java.util.*;

public class SumOfMinMax {

    public static void main(String[] args){
        int[] arr = {2, 5, -1, 7, -3, -1, -2};
        SumOfMinMax sumOfMinMax = new SumOfMinMax();
        System.out.println(sumOfMinMax.findMax(arr, 3));
    }

    public int findMax(int[] arr, int k){
        List<Integer> minList = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();

        Deque<Pair> deque = new LinkedList<>();

        for(int i=0; i<k; i++){
            while(!deque.isEmpty() && deque.peekLast().value <= arr[i]){
                deque.pollLast();
            }

            deque.addLast(new Pair(arr[i], i));
        }
        maxList.add(deque.peekFirst().value);

        for(int i=k; i<arr.length; i++){
            if(!deque.isEmpty()){
                Pair first = deque.peekFirst();
                if(first != null && first.index <= (i-k)){
                    deque.pollFirst();
                }
            }

            while(!deque.isEmpty() && deque.peekLast().value <= arr[i]){
                deque.pollLast();
            }

            deque.addLast(new Pair(arr[i], i));
            maxList.add(deque.peekFirst().value);
        }
        System.out.println("Max list " + Arrays.toString(maxList.toArray()));

        deque = new LinkedList<>();
        for(int i=0; i<k; i++){
            while(!deque.isEmpty() && deque.peekLast().value >= arr[i]){
                deque.pollLast();
            }
            deque.addLast(new Pair(arr[i], i));
        }
        minList.add(deque.peekFirst().value);

        for(int i=k; i<arr.length; i++){
            if(!deque.isEmpty()){
                Pair first = deque.peekFirst();
                if(first != null && first.index <= (i -k)){
                    deque.pollFirst();
                }
            }

            while(!deque.isEmpty() && deque.peekLast().value >= arr[i]){
                deque.pollLast();
            }

            deque.addLast(new Pair(arr[i], i));
            minList.add(deque.peekFirst().value);
        }
        System.out.println("Min list " + Arrays.toString(minList.toArray()));


        int sum =0;
        for(int i=0; i<minList.size(); i++){
            sum += maxList.get(i) + minList.get(i);
        }

        return sum;
    }

    public static class Pair{
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
