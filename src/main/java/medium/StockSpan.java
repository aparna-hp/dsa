package medium;

import java.util.Arrays;
import java.util.Stack;

public class StockSpan {

    public static void main(String[] args){
        //int[] price = {100, 80, 60, 70, 60, 75, 85};
        int[] price = {10, 4, 5, 90, 120, 80};
        System.out.println(Arrays.toString(calculateSpan(price, price.length)));
    }

    //Function to calculate the span of stockâ€™s price for all n days.
    public static int[] calculateSpan(int price[], int n)
    {
        int[] result = new int[n];
        Stack<Integer> rle = new Stack<>();

        for(int i=n-1; i>=0; i--){
            while(!rle.isEmpty() && price[i] > price[rle.peek()] ){
                rle.pop();
            }

            result[i] = -1;
            if(!rle.isEmpty()){
                int rleIndex = rle.peek();
                if(result[rleIndex] == -1 || result[rleIndex] > i) {
                    result[rleIndex] = i;
                }
            }

            rle.push(i);
        }

        for(int i=0; i<n; i++){
            if(result[i] == -1) {
                result[i] = 1;
            }
            else {
                result[i] = i - result[i] + result[result[i]];
            }
        }
        return result;
    }
}
