package Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bulbs {

    public static void main(String[] args) {
        Bulbs bulbs = new Bulbs();
        bulbs.bulbs(new ArrayList<>(Arrays.asList(0,1,0,1)));

        List<String> A = new ArrayList<>();
        A.add("/");
        System.out.println("True " + A.get(0).equals("/"));
    }

    public int bulbs(ArrayList<Integer> A) {

        int count = 0;
        for(int i=0; i<A.size(); i++){
            int state = A.get(i);
            if(((state+count) % 2) == 0){
                count++;
            }
        }
        System.out.println("Count " + count);
        return count;



    }
}
