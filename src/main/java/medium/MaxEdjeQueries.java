package medium;

import java.util.*;

public class MaxEdjeQueries {

    int maxCount = 0;


    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> A = new ArrayList<>() ;
        A.add(new ArrayList<>(Arrays.asList(10, 6, 42)));
        A.add(new ArrayList<>(Arrays.asList(3, 2, 18468)));
        A.add(new ArrayList<>(Arrays.asList(12, 7, 6335)));
        A.add(new ArrayList<>(Arrays.asList(9, 5, 26501)));
        A.add(new ArrayList<>(Arrays.asList(2, 1, 19170)));
        A.add(new ArrayList<>(Arrays.asList(8, 3, 15725)));
        A.add(new ArrayList<>(Arrays.asList(7, 1, 11479)));
        A.add(new ArrayList<>(Arrays.asList(4, 2, 29359)));
        A.add(new ArrayList<>(Arrays.asList(6, 3, 26963)));
        A.add(new ArrayList<>(Arrays.asList(11, 4, 24465)));
        A.add(new ArrayList<>(Arrays.asList(5, 3, 5706)));
        A.add(new ArrayList<>(Arrays.asList(13, 11, 28146)));

        ArrayList<ArrayList<Integer>> B = new ArrayList<>();
        B.add(new ArrayList<>(Arrays.asList(13,5)));

        MaxEdjeQueries maxEdjeQueries = new MaxEdjeQueries();
        System.out.println("Result " + maxEdjeQueries.solve(A, B));
    }

    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {

        Map<Integer, List<Adjacency>> map = new HashMap<>();

        for (ArrayList<Integer> adjacency : A) {
            int node1 = adjacency.get(0);
            int node2 = adjacency.get(1);
            int w = adjacency.get(2);

            List<Adjacency> adjList = new ArrayList<>();
            if (map.containsKey(node1)) {
                adjList = map.get(node1);
            }
            adjList.add(new Adjacency(node2, w));
            map.put(node1, adjList);

            List<Adjacency> adjList2 = new ArrayList<>();
            if (map.containsKey(node2)) {
                adjList2 = map.get(node2);
            }
            adjList2.add(new Adjacency(node1, w));
            map.put(node2, adjList2);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (ArrayList<Integer> query : B) {
            int u = query.get(0);
            int v = query.get(1);

            maxCount = Integer.MIN_VALUE;
            compute(map, new ArrayList<>(), u, v, Integer.MIN_VALUE);
            result.add(maxCount);

        }

        return result;
    }

    public int compute(Map<Integer, List<Adjacency>> map, List<Integer> visited,  int curr, int end, int max) {
        if(curr == end){
            maxCount  = Math.max(maxCount, max);
            return max;
        }

        if(maxCount != Integer.MIN_VALUE) {
            return maxCount;
        }

        if(visited.contains(curr) || visited.size() == map.size()){
            return Integer.MIN_VALUE;
        }

        List<Integer> newVisited = new ArrayList<>(visited);
        newVisited.add(curr);

        for(Adjacency adj : map.get(curr)){
            System.out.println("MaxCount = " + maxCount);
            if(!visited.contains(adj.getNode()) && maxCount == Integer.MIN_VALUE){
                int currWeight = Math.max(max, adj.getWeight());
                int currMax  = compute(map, newVisited, adj.getNode(), end, currWeight );
                max = Math.max(max, currMax);
            }
        }

        return Integer.MIN_VALUE;
    }

    public static class Adjacency{

        int node;
        int weight;

        public Adjacency(int n, int w){
            node = n;
            weight = w;
        }

        public int getNode(){
            return node;
        }

        public int getWeight(){
            return weight;
        }

    }
}

