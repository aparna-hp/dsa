package medium;

import java.util.PriorityQueue;

public class HomogeneousString {

    public static void main(String[] args){
        System.out.println(HomogeneousString.countHomogenous("abbcccaa"));
        System.out.println(HomogeneousString.countTexts("222222222222222222222222222222222222"));
        String text = "222222222222222222222222222222222222";
        System.out.println(text.length());


    }

    public static int countHomogenous(String s) {
        int M = 1000000009;
        long sum = 0;
        long currSum = 0;
        long count =0;
        char prev = s.charAt(0);
        for(int i=0; i<s.length(); i++){
            char curr = s.charAt(i);
            if(prev != curr){
                sum = (sum%M + (count%M * (count+1)%M)/2 % M) % M;
                currSum += count * (count+1)/2;
                count =1;
            } else {
                count++;
            }
            prev = curr;
        }
        sum = (sum%M + (count%M * (count+1)%M)/2 % M) % M;
        currSum += count * (count+1)/2;
        System.out.println("Correct Sum " + currSum);
        return (int)sum;
    }

    public static int countTexts(String pressedKeys) {
        int[] combination = {1,2,4};
        //int M = 1000000007;

        int count = 0;
        char prev = pressedKeys.charAt(0);
        long sum = 1;
        for(int i=0; i<pressedKeys.length(); i++){
            char curr = pressedKeys.charAt(i);
            if(prev == curr){
                count++;
            } else {
                if(count <= 3) {
                    sum = (sum * combination[count - 1]);
                } else {
                    int groupOfThree = count/3;
                    sum = sum * combination[(count%3)];
                    sum = sum * (long)Math.pow(4, groupOfThree);
                }
                count =1;
            }
            prev = curr;
        }
        if(count <= 3) {
            sum = (sum * combination[count - 1]);
        } else {
            int groupOfThree = count/3;
            sum = sum * combination[(count%3)];
            sum = sum * (long)Math.pow(4, groupOfThree);
        }
        return (int)sum;
    }
}
