package medium;

import java.util.*;

public class DecodeString {

    public static void main(String[] args){
        DecodeString decodeString = new DecodeString();
        String s = "ajx37nyx97niysdrzice4petvcvmcgqn282zicpbx6okybw93vhk782unctdbgmcjmbqn25rorktmu5ig2qn2y4xagtru2nehmsp";
        System.out.println(decodeString.decodeAtIndex(s, 976159153));
    }

    public String decodeAtIndex(String s, int k) {
        Queue<String> myQueue = new LinkedList<>();

        int count = 0;
        boolean prevCount = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                count = count * 10 + c - '0';
                prevCount = true;
            } else if (prevCount) {
                StringBuilder temp = new StringBuilder();
                while (!myQueue.isEmpty()) {
                    temp.append(myQueue.poll());
                }
                StringBuilder newString = new StringBuilder(temp.toString());
                while (count > 1) {
                    newString.append(temp);
                    if (newString.length() >= k) {
                        return "" + newString.charAt(k - 1);
                    }
                    count--;
                }
                count = 0;
                myQueue.add(newString.toString());
                prevCount = false;
                myQueue.add("" + c);
            } else {
                myQueue.add("" + c);
            }
        }

        StringBuilder temp = new StringBuilder();
        while (!myQueue.isEmpty()) {
            temp.append(myQueue.poll());
        }

        StringBuilder newString = new StringBuilder(temp.toString());
        while (count > 1) {
            newString.append(temp);
            if (newString.length() >= k) {
                return "" + newString.charAt(k - 1);
            }
            count--;
        }

        return "" + newString.charAt(k - 1);
    }

    /*
    string decodeAtIndex(string encodedString, int k) {
        long long decodedLength = 0;
        size_t n = encodedString.length();

        // Calculate the total decoded length
        for (size_t i = 0; i < n; ++i) {
            if (isdigit(encodedString[i])) {
                int digit = encodedString[i] - '0';
                decodedLength *= digit;
            } else {
                decodedLength++;
            }
        }

        // Iterate through the string in reverse to find the kth character
        for (int i = n - 1; i >= 0; --i) {
            k %= decodedLength; // Adjust k based on the current decoded length

            if (k == 0 && isalpha(encodedString[i])) {
                return string(1, encodedString[i]);
            }

            if (isdigit(encodedString[i])) {
                int digit = encodedString[i] - '0';
                decodedLength /= digit;
            } else {
                decodedLength--;
            }
        }

        return ""; // Return an empty string if no character is found
    }
     */

}
