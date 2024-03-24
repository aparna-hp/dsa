import java.util.Arrays;

class MinTapsToGarden {

    public static void main(String[] args) {
        MinTapsToGarden minTapsToGarden = new MinTapsToGarden();
        int[] ranges = {1,2,1,0,2,1,0,1};
        System.out.println(minTapsToGarden.minTapsOptimized(7, ranges));

    }

    public int minTapsOptimized(int n, int[] ranges) {
        int[] rangeSpan = new int[ranges.length];
        for(int i=0; i<ranges.length; i++) {
            if(ranges[i] == 0) {
                continue;
            }
            int left = Math.max(0, i-ranges[i]);
            rangeSpan[left] = Math.max(rangeSpan[left], i+ranges[i]);
        }

        System.out.println(Arrays.toString(rangeSpan));

        int noOftaps =0;
        int index =0;

        while(index < n) {
            noOftaps++;
            int lastCoveredIndex = rangeSpan[index];
            if(lastCoveredIndex >= n) {
                return noOftaps;
            }
            int nextIndex = index;
            int nextMaxCoverage = lastCoveredIndex;
            for(int i= index+1; i <= lastCoveredIndex ; i++){
                if(rangeSpan[i] > nextMaxCoverage) {
                    nextMaxCoverage = rangeSpan[i];
                    nextIndex = i;
                }
            }
            if(nextIndex == index) {
                return -1;
            }

            index =nextIndex;

        }

        return noOftaps;
    }


    public int minTaps(int n, int[] ranges) {
        int[] coveredArea = new int[ranges.length];

        int result = findMinTaps(ranges, coveredArea, n, 0, 0);
        if(result == Integer.MAX_VALUE || result > n) {
            return -1;
        }
        return result;
    }

    public int findMinTaps(int[] ranges, int[] coveredArea, int n, int index, int numOfTaps) {
        boolean done = true;
        for (int j : coveredArea) {
            if (j == 0) {
                done = false;
                break;
            }
        }
        if(done) {
            return numOfTaps;
        }

        if(index > n) {
            return Integer.MAX_VALUE;
        }

        int startIndex = Math.max(0, index-ranges[index]);
        int endIndex = Math.min(n, index+ranges[index]);

        int[] newCoveredArea = coveredArea.clone();


        for(int i = startIndex; i<=endIndex; i++) {
            newCoveredArea[i] = 1;
        }
        return Math.min(findMinTaps(ranges, newCoveredArea, n, index+1, numOfTaps +1),
                findMinTaps(ranges, coveredArea, n, index+1, numOfTaps));

    }
}