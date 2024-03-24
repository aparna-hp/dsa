package hard;

import java.util.*;

public class ParallelCourses {

    public static void main(String[] args){
        ParallelCourses parallelCourses = new ParallelCourses();
        int[][] relations = {{1,3},{2,3}};
        int[] time = {3,2,5};
        System.out.println(parallelCourses.minimumTime(3, relations, time));
    }

        public int minimumTime(int n, int[][] relations, int[] time) {
            int minTime = 0;
            int[] maxTime = new int[n+1];

            Map<Integer,List<Integer>> map = new HashMap<>();
            int[] prevCourses = new int[n+1];
            for(int i=0; i<relations.length; i++){
                List<Integer> courses = new ArrayList<>();
                if(map.containsKey(relations[i][0])) {
                    courses = map.get(relations[i][0]);
                }
                courses.add(relations[i][1]);
                map.put(relations[i][0], courses);
                prevCourses[relations[i][1]]++;
            }

            Queue<Integer> zero = new LinkedList<>();
            for(int i=1; i<=n; i++){
                if(prevCourses[i] == 0){
                    zero.add(i);
                }
            }
            while(!zero.isEmpty()) {
                Integer node = zero.remove();
                if(map.containsKey(node)){
                    int currCourseTime = maxTime[node]+time[node-1];
                    for(int dependency : map.get(node)) {
                        prevCourses[dependency]--;
                        if(prevCourses[dependency] == 0){
                            zero.add(dependency);
                        }
                        maxTime[dependency] = Math.max(maxTime[dependency],
                                currCourseTime);
                    }
                }  else {
                    maxTime[node] = maxTime[node]+time[node-1];
                }
            }

            for(int i=1; i<maxTime.length; i++){
                minTime = Math.max(maxTime[i], minTime);

            }

            return minTime;
        }


       /* public int minimumTime(int n, int[][] relations, int[] time) {
            int minTime = 0;
            int[] maxTime = new int[n+1];

            int[] prevCourses = new int[n+1];
            for (int[] relation : relations) {
                prevCourses[relation[1]]++;
            }

            boolean hasZeroPrev = true;
            while(hasZeroPrev) {
                hasZeroPrev = false;
                List<Integer> zero = new ArrayList<>();
                for(int i=1; i<prevCourses.length; i++) {
                    if(prevCourses[i] == 0) {
                        prevCourses[i] = -1;
                        zero.add(i);
                        hasZeroPrev = true;
                    }
                }
                if(!hasZeroPrev) break;

                boolean[] hasDependency = new boolean[n+1];
                for (int[] relation : relations) {
                    if (zero.contains(relation[0])) {
                        hasDependency[relation[0]] = true;
                        prevCourses[relation[1]]--;
                        maxTime[relation[1]] = Math.max(maxTime[relation[1]],
                                maxTime[relation[0]] + time[relation[0] - 1]);
                    }
                }

                for (Integer node : zero) {
                    if (hasDependency[node]) {
                        maxTime[node] = 0;
                    } else {
                        maxTime[node] += time[node - 1];
                    }
                }
            }

            for(int i=1; i<maxTime.length; i++){
                minTime += maxTime[i];
            }

            return minTime;
        }

        */

}
