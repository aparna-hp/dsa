
import java.util.*;

public class NetworkDelay {

    Map<Integer, Integer> result = new HashMap<>();
    int minDistance = 0;

    public static void main(String[] args) {
        int[][] times ={{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        System.out.println(new NetworkDelay().networkDelayTime(times, n, k));
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        boolean[] visitedNodes = new boolean[n];
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparing(Pair::getValue));
        populatePQueue(times, visitedNodes, priorityQueue, k, n);
        return minDistance;
    }

    public void populatePQueue(int[][] times, boolean[] visitedNodes, PriorityQueue<Pair> priorityQueue,
                               int source, int n){

        visitedNodes[source-1] = true;


        for (int[] time : times) {
            if (time[0] == source) {
                priorityQueue.add(new Pair(time[1], result.getOrDefault(source,0)+ time[2]));
            }
        }

        Pair leastDistanceNode = priorityQueue.poll();
        System.out.println("Least distance node " + leastDistanceNode + " from source " + source);
        if(null != leastDistanceNode) {
            if (leastDistanceNode.value > minDistance) {
                minDistance = leastDistanceNode.value;
            }
            result.put(leastDistanceNode.key, leastDistanceNode.value);
            populatePQueue(times, visitedNodes, priorityQueue, leastDistanceNode.key, n);
        }

    }

    public static class Pair{
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
