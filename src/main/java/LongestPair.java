import java.util.*;

class LongestPair {

    static int[] dp;

    public static void main(String[] agrs) {
        LongestPair longestPair = new LongestPair();
        int[][] pairs = {{-6,9},{1,6},{8,10},{-1,4},{-6,-2},{-9,8},{-5,3},{0,3}};

        System.out.println(longestPair.findLongestChain(pairs));
    }

    public int findLongestChain(int[][] pairs) {
        dp = new int[pairs.length];
        Arrays.fill(dp, -1);
        List<Pair> pairList = new ArrayList<>();
        for (int[] ints : pairs) {
            Pair pair = new Pair(ints[0], ints[1]);
            pairList.add(pair);
        }
        Collections.sort(pairList);

        System.out.println(Arrays.toString(pairList.toArray()));
        return maxChain(pairList, Integer.MIN_VALUE, dp.length);
    }

    public int maxChain(List<Pair> pairList, int lastRight, int n) {
        if(pairList.size() == 0) {
            return 0;
        }

        if(dp[n - pairList.size()] != -1) {
            return dp[n- pairList.size()];
        }

        Pair p = pairList.get(0);
        int include = 0, exclude =0;
        int i = 1;
        while(i < pairList.size()) {
            if(p.key > lastRight) {
                break;
            }
            p = pairList.get(i);
            i ++;
        }
        if(p.key > lastRight) {
            include = 1 + maxChain(pairList.subList(i, pairList.size()), p.val, n);
        }
        exclude = maxChain(pairList.subList(1, pairList.size()), lastRight, n);

        dp[n - pairList.size()] = Math.max(include,exclude);
        return Math.max(include, exclude);

    }



    public static class Pair implements Comparable<Pair>{
        int key;
        int val;

        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(Pair p2) {
            return this.val - p2.val;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }
}
