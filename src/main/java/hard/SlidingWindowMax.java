package hard;

import java.util.*;

public class SlidingWindowMax {

    public static void main(String[] args){
        SlidingWindowMax slidingWindowMax = new SlidingWindowMax();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] result = slidingWindowMax.maxSlidingWindowPQ(nums, 3);
        System.out.println(Arrays.toString(result));
        slidingWindowMax.maxSlidingWindow(nums,3).forEach(s -> System.out.println(s));
    }

    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        for(int i=0; i<nums.length; i++) {
            if(!deque.isEmpty() && deque.peekFirst() == i-k){
                deque.removeFirst();
            }

            while(!deque.isEmpty() && nums[i] > nums[deque.peekLast()]){
                deque.removeLast();
            }

            if(deque.isEmpty() || nums[i] < nums[deque.peekLast()]){
                deque.addLast(i);
            }

            if(i >= k-1 && !deque.isEmpty()) {
                result.add(nums[deque.peekFirst()]);
            }
        }

        return result;
    }

        public int[] maxSlidingWindowPQ(int[] nums, int k) {

            List<Integer> ans = new ArrayList<>();
            PriorityQueue<Pair> queue = new PriorityQueue<>((a,b)-> b.value - a.value);
            for(int i =0; i < k && i < nums.length; i++){
                queue.add(new Pair(i, nums[i]));
            }
            Pair peekInt = queue.peek();
            if(peekInt.value == nums[0]){
                queue.remove();
            }
            ans.add(peekInt.value);

            for(int i=k; i<nums.length; i++ ){
                queue.add(new Pair(i, nums[i]));
                peekInt = queue.peek();
                while(peekInt.index < (i-k+1)) {
                    queue.remove();
                    peekInt = queue.peek();
                }
                ans.add(peekInt.value);
                if(peekInt.value == nums[i-k+1]){
                    queue.remove();
                }
            }

            int[] result = new int[ans.size()];
            for(int i=0; i < ans.size(); i++){
                result[i]=ans.get(i);
            }
            return result;

        }

        public static class Pair {
            int index;
            int value;

            public Pair(int index, int value){
                this.index = index;
                this.value = value;
            }

            public int getValue(){
                return this.value;
            }
        }

}
