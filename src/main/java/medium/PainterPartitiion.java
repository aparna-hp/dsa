package medium;

import java.util.*;

public class PainterPartitiion {

    public static void main(String[] args) {
        PainterPartitiion painterPartitiion = new PainterPartitiion();
        ArrayList<Integer> C = new ArrayList<>();
        C.add(658);
        C.add(786);
        C.add(531);
        C.add(47);
        C.add(169);
        C.add(397);
        C.add(914);
        System.out.println(painterPartitiion.paint(5,10, C));
    }

    public int paint(int A, int B, ArrayList<Integer> C) {
    int maxLen = 0, minLen = 0;
        for(int len : C) {
        maxLen = Math.max(maxLen, len);
        minLen = Math.min(minLen, len);
    }

        if(A >= C.size()){
        return (maxLen*B % 10000003);
    }

        return findTime(C, (minLen*B % 10000003), (maxLen*B % 10000003), A, B);
}

    public int findTime(ArrayList<Integer> C, int start, int end, int A, int B) {
        if(start > end) {
            return Integer.MAX_VALUE;
        }

        int mid =(start + (end-start)/2) % 10000003;
        System.out.println("start " + start + " end " + end + "mid " + mid);
        int temp = A;
        int sum = 0; int index = 0;
        for(; index < C.size(); index++) {
            if(temp == 0) {
                break;
            }
            sum = (C.get(index)*B % 10000003) + (sum % 10000003);
            if(sum >= mid){
                sum = 0;
                temp --;
            }
        }
        System.out.println("Index " + index);
        if(index == C.size()){
            return Math.min(mid, findTime(C, start, mid-1, A, B));
        }

        return findTime(C, mid+1, end, A, B);
    }
}
