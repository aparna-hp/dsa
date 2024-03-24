package medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GasStation {

    public static void main(String[] args) {
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(gas, cost));
        System.out.println(gasStation.canComplete(gas, cost));

        int[] diff = new int[gas.length];
        for(int i=0; i< gas.length; i++){
            diff[i] = gas[i]-cost[i];
        }
        System.out.println("Diff array :: " + Arrays.toString(diff));

        System.out.println("Optimized " + gasStation.canCompleteOptimized(diff));

        for(int i=0; i< gas.length; i++) {
            int ans = gasStation.conCompleteRec(diff, i, i + 1, diff[i]);
            if (ans != -1) {
                System.out.println(i);
                break;
            }
            else
                System.out.println("-1");
        }
    }

    public int canCompleteOptimized(int[] diffs){
        int total =0;
        int ansIndex = -1;
        int sum = 0;
        for(int i=0; i< diffs.length; i++) {
            if(diffs[i] >= 0 && ansIndex == -1) {
                ansIndex = i;
            }

            if(ansIndex != -1) {
                sum += diffs[i];
            }

            if(sum < 0) {
                ansIndex = -1;
                sum = 0;
            }
            total += diffs[i];
        }

        if(total >= 0) {
            return ansIndex;
        } else return -1;
    }

    public int conCompleteRec(int[] diff, int startIndex, int index, int balance){
        if(balance <0) {
            return -1;
        }

        if(index% diff.length == startIndex){
            return startIndex;
        }

        return conCompleteRec(diff, startIndex, index+1, balance+diff[(index)%diff.length]);

    }

    public int canComplete(int[] gas, int[] cost){
        int n = gas.length;
        int[] diff = new int[gas.length];
        for(int i=0; i<n; i++){
            diff[i] = gas[i]-cost[i];
        }

        for(int i=0; i<n; i++){
            if(diff[i] < 0) {
                continue;
            }
            boolean possible = true;
            int balance = diff[i];
            for(int j=i+1; ;j++){
                balance += diff[j%n];
                if(balance < 0){
                    possible = false;
                    break;
                }
                if(j%n == i){
                    break;
                }
            }
            if(possible) return i;
        }
        return -1;
    }

        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            for(int i=0; i< n; i++) {
                if(gas[i] < cost[i]) {
                    continue;
                }
                int balance = gas[i];
                boolean possible = true;
                for(int j=i+1; ; j++) {
                    balance -=  cost[(j-1)%n];
                    if(balance < 0) {
                        possible = false;
                        break;
                    }
                    balance += gas[j%n];
                    if(j%n == i) break;
                }
                if(possible) return i;
            }
            return -1;
        }

}
