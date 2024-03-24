package medium;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

//User function template for JAVA

class CombinationSum
{

    public static void main(String[] args){
        ArrayList<Integer> A = new ArrayList<>();
        //8 1 8 6 8
        A.add(8);
        A.add(1);
        A.add(8);
        A.add(6);
        A.add(8);

        ArrayList<ArrayList<Integer>> result = combinationSum(A,12);
        for(ArrayList<Integer> row : result){
            System.out.println(Arrays.toString(row.toArray()));
        }
    }

    //Function to return a list of indexes denoting the required 
    //combinations whose sum is equal to given number.
    static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B)
    {
        // add your code here
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> curr = new ArrayList<>();
        compute(A, B, 0, curr, result);
        return result;
    }

    static void compute(ArrayList<Integer> A, int B, int index, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> result) {

        if (B < 0) {
            return;
        }

        if (B == 0) {
            if(!result.contains(curr)) {
                result.add(curr);
            }
            return;
        }

        if (index >= A.size()) {
            return;
        }

        ArrayList<Integer> currCopy = new ArrayList<>(curr);
        currCopy.add(A.get(index));
        compute(A, B - A.get(index), index, currCopy, result);
        compute(A, B, index + 1, curr, result);
    }
}