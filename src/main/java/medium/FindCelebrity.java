package medium;

import java.util.*;

public class FindCelebrity {

    public static void main(String[] args){
        String input = "0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0";
        int N = 18;
        String[] temp = input.split(" ");
        int[][] M = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                M[i][j]= Integer.parseInt(temp[i+j]);
            }
        }

        FindCelebrity findCelebrity = new FindCelebrity();
        System.out.println(findCelebrity.celebrity(M, N));
    }

    int celebrity(int M[][], int n)
    {
        List<Integer> zero = new ArrayList<>();
        for(int i=0; i<M.length; i++){
            boolean found = false;
            for(int j=0; j<M[0].length; j++){
                if(M[i][j] == 1){
                    found = true;
                    break;
                }
            }
            if(!found) zero.add(i);
        }

        int ans = -1;
        for(int person : zero) {
            boolean found = true;
            for(int i=0; i<M.length; i++) {
                if(i == person) {
                    continue;
                }
                if(M[i][person] != 1){
                    found = false;
                    break;
                }
            }
            if(found){
                ans = person;
                break;
            }
        }

        return ans;
    }
}
