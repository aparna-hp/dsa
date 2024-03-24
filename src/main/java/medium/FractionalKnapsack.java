package medium;

import java.util.*;

public class FractionalKnapsack {

    public static void main(String[] args){
        Item[] arr = new Item[3];
        arr[0] = new Item(60, 10);
        arr[1] = new Item(100,20);
        arr[2] = new Item(120,30);

        FractionalKnapsack fractionalKnapsack = new FractionalKnapsack();
        System.out.println(fractionalKnapsack.fractionalKnapsack(50, arr, 3));
    }

    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n)
    {
        double ans = 0.0;
        Arrays.sort(arr, Comparator.comparing(s -> {return (double)s.value/(double)s.weight;}));
        for(int i= arr.length-1; i >=0 && W > 0; i--){
            System.out.println("Value " + arr[i].value + " W " + arr[i].weight);
            if(arr[i].weight < W) {
                W -= arr[i].weight;
                ans += (double)arr[i].value;
            } else {
                double fraction = (double)W/arr[i].weight;
                System.out.println("Fraction " + fraction + " value " + fraction * arr[i].value);
                double value = fraction * arr[i].value;
                ans += value;
                W =0;
            }
        }
        return ans;
    }

    static class Item {
        int value, weight;
        Item(int x, int y){
            this.value = x;
            this.weight = y;
        }

        public int getValue(){
            return this.value;
        }

        public int getWeight(){
            return this.weight;
        }
    }
}
