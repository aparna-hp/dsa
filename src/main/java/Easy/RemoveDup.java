package Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDup {

    public int removeDuplicates(ArrayList<Integer> a) {
        ArrayList<Integer> result = new ArrayList<>();
        int prev = -1;
        for(int ele : a){
            if(ele != prev){
                result.add(ele);
                prev = ele;
            }
        }
        a.clear();
        a.addAll(result);
        return a.size();
    }

    public static void main(String[] args) {
        RemoveDup removeDup = new RemoveDup();
         ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        int size = removeDup.removeDuplicates(list);
        System.out.println("Size " + size);
        System.out.println("a = " + Arrays.toString(list.toArray()));
    }
}
