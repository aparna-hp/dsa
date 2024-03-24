package medium;

import java.util.*;

public class ScheduleMeeting {

    public static void main(String[] args){
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(17, 26)));
        A.add(new ArrayList<>(Arrays.asList(14, 22)));
        A.add(new ArrayList<>(Arrays.asList(7, 29)));
        A.add(new ArrayList<>(Arrays.asList(2, 29)));
        A.add(new ArrayList<>(Arrays.asList(11, 14)));
        A.add(new ArrayList<>(Arrays.asList(5, 24)));
        A.add(new ArrayList<>(Arrays.asList(1, 14)));
        A.add(new ArrayList<>(Arrays.asList(13, 14)));

        ScheduleMeeting scheduleMeeting = new ScheduleMeeting();
        System.out.println(scheduleMeeting.solve(A));
    }

    public int solve(ArrayList<ArrayList<Integer>> A) {
        int max = 0;
        int curr = 0;
        Map<Integer, Integer> pairs = new TreeMap<>();
        for(ArrayList<Integer> meeting : A) {
            int val = 1;
            if(pairs.containsKey(meeting.get(0))) {
                val = pairs.get(meeting.get(0)) +1;
            }
            pairs.put(meeting.get(0), val);

            val = -1;
            if(pairs.containsKey(meeting.get(1))) {
                val = pairs.get(meeting.get(1)) -1;
            }
            pairs.put(meeting.get(1), val);

        }

        for(Integer time : pairs.keySet()){
            curr += pairs.get(time);
            max = Math.max(max, curr);
        }
        return max;
    }

     /*
    public int solve(ArrayList<ArrayList<Integer>> A) {
        int room = 0;
        A.sort(Comparator.comparing(o -> o.get(1)));

        boolean[] visited = new boolean[A.size()];
        for(int i=0; i<A.size(); i++){
            if(!visited[i]){
                room++;
                visited[i] = true;
                markAllMeetings(A, visited, i);
            }
        }

        return room;
    }

    public void markAllMeetings(ArrayList<ArrayList<Integer>> A, boolean[] visited, int index) {
        int preEnd = A.get(index).get(1);


        int nextEnd = index;
        while(nextEnd != -1) {
            int start = nextEnd+1;
            int end = A.size()-1;
            preEnd = A.get(nextEnd).get(1);
            System.out.println("Start at index " + nextEnd + " PreEnd " + preEnd);
            nextEnd = -1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                int midA = A.get(mid).get(0);
                System.out.println("Mid " + mid);
                if (midA == preEnd) {
                    while (mid > 0 && midA == A.get(mid-1).get(0)) {
                        mid--;
                    }
                    visited[mid] = true;
                    nextEnd = mid;
                    System.out.println("next End " + nextEnd);
                    break;
                } else if (midA > preEnd) {
                    nextEnd = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                if (start == end) {
                    break;
                }
            }
        }



        for(int i = index+1; i < A.size(); i++){
            System.out.println("Try start " + A.get(i).get(0));
            if(!visited[i] && A.get(i).get(0) >= preEnd) {
                visited[i]= true;
                preEnd = A.get(i).get(1);
                System.out.println("PreEnd " + preEnd);
            }
        }
        System.out.println(Arrays.toString(visited));
    }
    */

}
