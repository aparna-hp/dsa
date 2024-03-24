package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Seats {

    public static void main(String[] args){
        Seats seats = new Seats();
        System.out.println(seats.seats("............x.x"));

        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(-49, 58, 72, -78, 9, 65, -42, -3 ));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(30, -13, -70, 58, -34, 79, -36, 27));
        System.out.println(seats.mice(A, B));
    }

    public int seats(String A) {
        int M = 10000003;
        List<Integer> pos = new ArrayList<>();
        for(int i=0; i<A.length(); i++){
            char c = A.charAt(i);
            if(c == 'x'){
                pos.add(i);
            }
        }

        if(pos.size() <= 1){
            return 0;
        }

        int count = 0;
        int median = pos.get(pos.size()/2);
        int vacant = median -1;
        for(int i = pos.size()/2-1; i >=0 ; i--){
            count = (count%M + Math.abs(vacant - pos.get(i))) % M;
            vacant --;
        }

        vacant = median +1;
        for(int i= pos.size()/2+1; i<pos.size(); i++){
            count = (count%M + Math.abs(vacant - pos.get(i))) % M;
            vacant++;
        }
        return count;
    }

    public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);

        int max = 0;
        for(int i=0; i<A.size(); i++){
            int curr = Math.abs(A.get(i) - B.get(i));
            max = Math.max(curr, max);
        }

        return max;
    }
}
