package medium;

import java.util.*;

class UniqueBinary {

    public static void main(String[] args) {
        UniqueBinary uniqueBinary = new UniqueBinary();
        String[] nums = {"0"};
        System.out.println(uniqueBinary.findDifferentBinaryString(nums));
    }

    public String findDifferentBinaryString(String[] nums) {
        List<Integer> numList = new ArrayList<>();
        for (String num : nums) {
            numList.add(getNumFromBinary(num));
        }
        System.out.println(Arrays.toString(numList.toArray()));

        for(int i=0; i< Math.pow(2, nums.length); i++) {
            System.out.println("Checking " + i);
            if(!numList.contains(i)) {
                return convertToBinary(i,nums.length);
            }
        }

        return null;
    }

    public int getNumFromBinary(String s ) {
        int base = 1, num = 0;
        for(int i = s.length()-1; i >=0 ; i--) {
            if(s.charAt(i) == '1') {
                num+= base;
            }
            base *=2;
        }
        return num;
    }

    public String convertToBinary(int num, int n) {
        String binary = "";
        while(num > 0) {
            if(num %2 == 1) {
                binary = "1" + binary;
            } else {
                binary = "0" + binary;
            }
            num = num/2;
        }
        System.out.println(binary);
        while(binary.length() < n) {
            binary = "0"+ binary;
        }
        System.out.println(binary);
        return binary;
    }
}

