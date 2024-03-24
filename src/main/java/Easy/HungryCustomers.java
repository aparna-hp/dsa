package Easy;

import java.util.*;

public class HungryCustomers {

    public static List<Integer> jimOrders(List<List<Integer>> orders) {
        // Write your code here

        PriorityQueue<Pair> queue = new PriorityQueue<>();
        for(int i=0; i<orders.size(); i++){
            queue.add(new Pair(i+1, orders.get(i).get(0)+orders.get(i).get(1)));
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            result.add(curr.getCustomerId());
        }

        return result;

    }

    public static class Pair implements Comparable<Pair> {
        private final int customerId;
        private final int value;

        public Pair(int customerId, int value) {
            this.customerId = customerId;
            this.value = value;
        }

        public int getCustomerId(){
            return this.customerId;
        }

        @Override
        public int compareTo(Pair p){
            if(p.value == this.value){
                return this.customerId - p.customerId;
            }

            return this.value - p.value;
        }
    }
}
