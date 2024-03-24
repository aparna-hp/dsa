package hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class BestTimeToSellStocks {
    /*
    121. Best Time to Buy and Sell Stock
    122. Best Time to Buy and Sell Stock II
    123. Best Time to Buy and Sell Stock III
    188. Best Time to Buy and Sell Stock IV
    309. Best Time to Buy and Sell Stock with Cooldown
    714. Best Time to Buy and Sell Stock with Transaction Fee
     */
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        BestTimeToSellStocks sellStocks = new BestTimeToSellStocks();
        /*System.out.println(sellStocks.problem121(prices));
        System.out.println(sellStocks.problem122(prices));

        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(sellStocks.problem122(prices2));

        int[] prices3 = {3, 3, 5, 0, 0, 3, 1, 4};*/
        int[] prices5 = {1,2};
        //System.out.println(sellStocks.problem123(prices2));
        //System.out.println(sellStocks.problem123(prices3));
        System.out.println(sellStocks.problem123(prices5));


       /* int[] prices4 = {2,1,4,5,2,9,7};
        System.out.println(sellStocks.problem123(prices4));*/

    }

    public int problem121(int[] prices) {
        int[] rle = new int[prices.length];
        int n = prices.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        rle[n - 1] = 0;
        queue.add(prices[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            while (!queue.isEmpty() && queue.peek() <= prices[i]) {
                queue.poll();
            }
            if (queue.isEmpty()) {
                rle[i] = 0;
            } else {
                rle[i] = queue.peek();
            }
            queue.add(prices[i]);
        }

        int maxDiff = 0;
        for (int i = 0; i < prices.length; i++) {
            maxDiff = Math.max(maxDiff, rle[i] - prices[i]);
        }

        return maxDiff;
    }

    public int problem122(int[] prices) {
        int profit = 0;
        int[] dp = new int[prices.length];
        Arrays.fill(dp, -1);
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, compute122(prices, i, dp));
        }
        return profit;
    }

    public int problem123(int[] prices) {
        int profit = 0;
        int[] rle = new int[prices.length];

        int n = prices.length;
        rle[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
           rle[i] = Math.max(prices[i], rle[i+1]);
        }

       int[] profitArr = new int[n];
        profitArr[n-1] = 0;
        for(int i=n-2; i>=0; i--){
            profitArr[i] = Math.max(rle[i]-prices[i], profitArr[i+1]);
        }

        System.out.println("rle array " + Arrays.toString(rle));
        System.out.println("rle array " + Arrays.toString(profitArr));
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, compute123(prices, i, profitArr));
        }
        return profit;
    }

    private int compute123(int[] prices, int buy, int[] rle) {
        if (buy > prices.length - 2) {
            return 0;
        }

        int a = 0;
        for (int sell = buy + 1; sell < prices.length; sell++) {
            if (prices[sell] > prices[buy]) {
                int maxSale = 0;
                int diff = prices[sell] - prices[buy];
                if(sell+1 < prices.length){
                    maxSale = rle[sell+1];
                }
                System.out.println("MaxSale at buy " + buy + " and sell " + sell + " is " + (diff + maxSale));
                a = Math.max(a, diff + maxSale);
            }
        }
        return a;
    }

    private int compute122(int[] prices, int buy, int[] dp) {
        if (buy > prices.length - 2) {
            return 0;
        }

        if (dp[buy] != -1) {
            return dp[buy];
        }

        int a = 0;
        for (int sell = buy + 1; sell < prices.length; sell++) {
            if (prices[sell] > prices[buy]) {
                int maxSale = 0;
                int diff = prices[sell] - prices[buy];
                for (int buy2 = sell + 1; buy2 < prices.length; buy2++) {
                    maxSale = Math.max(maxSale, compute122(prices, buy2, dp));
                }
                System.out.println("MaxSale at buy " + buy + " and sell " + sell + " is " + (diff + maxSale));
                a = Math.max(a, diff + maxSale);
            }
        }
        dp[buy] = a;
        return a;
    }

}
