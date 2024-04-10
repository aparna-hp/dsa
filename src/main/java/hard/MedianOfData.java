package hard;

import java.util.*;

public class MedianOfData {

    public static void main(String[] args) {
        MedianOfData medianOfData = new MedianOfData();
        medianOfData.addNum(6);
        System.out.println(medianOfData.findMedian());
        medianOfData.addNum(10);
        System.out.println(medianOfData.findMedian());

        int[] A = {971981081, 76428256, 173826779, 820118823, 422023110, 490305014, 421203292, 57314938, 243747446, 708527582, 926821399, 3370427, 511659653, 688089780, 288854357, 194511642, 956080021, 773632039, 837549897, 331241442, 477939102, 558202801, 421402117, 694166614, 309492528, 709373664, 630331369, 261814971, 647420722, 821194014, 623752527, 524737357, 208590952, 155416577, 694123316, 611456060, 432437073, 261546464, 411083539, 42466036, 800158479, 135728593, 982237295, 232497018, 303629417, 646591106, 897183698, 249168286, 843354313, 84798799, 815959704, 224450781, 292388420, 908518847, 337902027, 136440488, 70520144, 612818050, 941818563, 736416700, 851653048, 577806143, 863152094, 823160246, 982031209, 210849261, 761435184, 970640883, 146690787, 385261159, 440695660, 439431833, 926469668, 967948492, 561951537, 318285216, 84251568, 233122742, 310998237, 628983724, 115735341, 473773603, 137134411, 930158172, 320016880, 107882631, 733399108, 589351369, 468626497, 924425221, 685162557, 200484167, 245932526, 351737070, 503079624, 25908816, 373571646, 185621031, 16493554, 440637861, 310522447, 366012482, 663192501, 380747982, 847498849, 240105535, 873935206, 311409188, 256322617, 173921845 };

        System.out.println(Arrays.toString(medianOfData.solve(A)));
    }

    PriorityQueue<Integer> smaller;
    PriorityQueue<Integer> bigger;

    public MedianOfData() {
        smaller = new PriorityQueue<>(Comparator.reverseOrder());
        bigger = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (smaller.size() == 0 && bigger.size() == 0) {
            smaller.add(num);
            return;
        }

        if (smaller.size() > 0 && num > smaller.peek()) {
            bigger.add(num);
        } else {
            smaller.add(num);
        }

        int diff = Math.abs(smaller.size() - bigger.size());
        if (diff > 1) {
            if (smaller.size() > bigger.size()) {
                bigger.add(smaller.poll());
            } else {
                smaller.add(bigger.poll());
            }
        }
    }

    public double findMedian() {

        int smallerSize = 0;
        int biggerSize = 0;

        if (!smaller.isEmpty()) {
            smallerSize = smaller.size();
        }

        if (!bigger.isEmpty()) {
            biggerSize = bigger.size();
        }

        if (smallerSize == 0 && biggerSize == 0) {
            return 0;
        }

        if (smallerSize > biggerSize) {
            return smaller.peek();
        } else if (smallerSize < biggerSize) {
            return bigger.peek();
        } else {
            return (smaller.peek() + bigger.peek()) / 2.0;
        }
    }

    public int[] solve(int[] A) {

        PriorityQueue<Integer> smaller = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> bigger = new PriorityQueue<>();

        int[] C = new int[A.length];

        smaller.add(A[0]);
        C[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > smaller.peek()) {
                bigger.add(A[i]);
            } else {
                smaller.add(A[i]);
            }

            int diff = Math.abs(smaller.size() - bigger.size());
            if (diff > 1) {
                System.out.println("Bigger Size " + bigger.size() + " Smaller " + smaller.size());
                if (smaller.size() > bigger.size()) {
                    bigger.add(smaller.poll());
                } else {
                    smaller.add(bigger.poll());
                }
                System.out.println("Bigger " + bigger.peek() + " Smaller " + smaller.peek());
            }

            if (bigger.size() > smaller.size()) {
                C[i] = bigger.peek();
            } else {
                C[i] = smaller.peek();
            }
        }
        return C;
    }
}





/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */



