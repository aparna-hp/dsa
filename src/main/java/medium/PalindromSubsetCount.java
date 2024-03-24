package medium;

import java.util.ArrayList;
import java.util.List;

class PalindromeSubsetCount {

    int count = 0;
    List<String> visited = new ArrayList<>();

    public static void main(String[] args){
        PalindromeSubsetCount palindromeSubsetCount = new PalindromeSubsetCount();
        System.out.println(palindromeSubsetCount.countSubstrings("aaaaa"));
    }

    public int countSubstrings(String s) {
        count = s.length();
        verify(s, 0, s.length()-1);
        return count;
    }

    public void verify(String s, int start, int end){
        if(start >= end) {
            return;
        }

        int mid = start + (end-start)/2;
        int low = mid - 1;
        int high = mid + 1;

        if((end-start) % 2 != 0) {
            if(mid-1 < start) {
                low = mid;
                high = mid+1;
            } else {
                low = mid-1;
                high = mid;
            }
        }

        while(!visited.contains(low + "_" + high) && low >= start && high <= end){
            visited.add(low + "_" + high);
            if(isPalindrome(s, low, high)) {
               System.out.println("Low high " + low + " " + high) ;
                count++;
                low--;
                high--;
            } else {
                break;
            }
        }

        verify(s, start+1, end);
        verify(s, start, end-1);
    }

    public boolean isPalindrome(String s, int low, int high) {
        while(low < high){
            if(s.charAt(low) != s.charAt(high)){
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

   /* public int countSubstrings(String s) {
        int count = s.length();
        for(int i=0; i<s.length(); i++) {
            String subStr = "" + s.charAt(i);
            for(int j=i+1; j<s.length(); j++) {
                subStr += s.charAt(j);
                if(isPalindrome(subStr)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isPalindrome(String s) {
        if(s.length() ==1) {
            return true;
        }

        int startPointer = 0;
        int endPointer = s.length()-1;

        while(startPointer < endPointer) {
            if(s.charAt(startPointer) != s.charAt(endPointer)) {
                return false;
            }
            startPointer++;
            endPointer--;
        }
        return true;
    }
    */
}
