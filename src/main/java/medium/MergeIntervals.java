package medium;

import java.util.*;

public class MergeIntervals {

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        ArrayList<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,3));
        intervals.add(new Interval(6,9));

        Interval newInterval = new Interval(2,5);
        ArrayList<Interval> result = mergeIntervals.insert(intervals, newInterval);
        System.out.println("Result " + Arrays.toString(result.toArray()));

        //[[1,2],[3,5],[6,7],[8,10],[12,16]]
        intervals = new ArrayList<>();
        intervals.add(new Interval(1,2));
        intervals.add(new Interval(3,5));
        intervals.add(new Interval(6,7));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(12,16));

        newInterval = new Interval(4,9);
        result = mergeIntervals.insert(intervals,newInterval);
        System.out.println("Result " + Arrays.toString(result.toArray()));

        newInterval = new Interval(9,14);
        result = mergeIntervals.insert(intervals,newInterval);
        System.out.println("Result " + Arrays.toString(result.toArray()));

        intervals = new ArrayList<>();

    }


    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public ArrayList<Interval> insertUsingLinear(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();
        int index = 0;

        while (index < intervals.size()) {
            Interval interval = intervals.get(index);
            Interval mergedInterval = new Interval();

            if (interval.start < newInterval.start && interval.end < newInterval.start) {
                System.out.println("Adding interval " + interval.start + "," + interval.end);
                result.add(interval);
                index++;
                continue;
            }

            mergedInterval.start = Math.min(interval.start, newInterval.start);
            System.out.println("Adding interval start " + mergedInterval.start );

            int endInterval = interval.end;
            boolean prev = true;

            while (newInterval.end > endInterval) {
                System.out.println("Skipping interval " + endInterval);
                if (prev && index+1 < intervals.size()) {
                    index++;
                    interval = intervals.get(index);
                    prev = false;
                    endInterval = interval.start;
                } else if(prev && index+1 >= intervals.size()) {
                    index++;
                    break;
                } else{
                    endInterval = interval.end;
                    prev = true;
                }
            }

            if(prev) {
                mergedInterval.end = endInterval;
                index++;
            } else {
                mergedInterval.end = newInterval.end;
            }

            System.out.println("Adding interval end " + mergedInterval.end);
            result.add(mergedInterval);
            break;
        }

        if (index < intervals.size()) {
            result.addAll(intervals.subList(index, intervals.size()));
        }
        return result;
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();

        if(intervals.size() == 0){
            result.add(newInterval);
            return result;
        }

        if(newInterval.start > newInterval.end){
            int temp = newInterval.start;
            newInterval.start = newInterval.end;
            newInterval.end = temp;
        }

        if(newInterval.start < intervals.get(0).start && newInterval.end < intervals.get(0).start){
            intervals.add(0, newInterval);
            return intervals;
        }

        if(newInterval.start > intervals.get(intervals.size()-1).end) {
            intervals.add(newInterval);
            return intervals;
        }

        boolean newIntervalAdded = false;

        for(Interval curr : intervals){
            if(!newIntervalAdded && newInterval.start < curr.start && newInterval.end < curr.start){
                result.add(newInterval);
                result.add(curr);
                newIntervalAdded = true;
                continue;
            }

            if(!newIntervalAdded && newInterval.start <= curr.end){
                int start = Math.min(newInterval.start, curr.start);
                int end = Math.max(newInterval.end, curr.end);
                result.add(new Interval(start, end));
                newIntervalAdded = true;
            } else {
                if(result.size() == 0) {
                    result.add(curr);
                } else {
                    Interval top = result.get(result.size()-1);
                    if(top.end  < curr.start){
                        result.add(curr);
                    } else if(top.end > curr.start && top.end < curr.end){
                        top.end = curr.end;
                    }
                }
            }
        }

        return result;
    }

    }


