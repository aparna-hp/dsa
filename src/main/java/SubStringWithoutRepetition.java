import java.util.*;
import java.util.LinkedList;

class SubStringWithoutRepetition {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        Map<Character, Boolean> charMap = new HashMap<>();
        Queue<Character> charQueue = new LinkedList<>();
        for(int i =0; i < s.length(); i++) {
            char c = s.charAt(i);

            //If the character is already found, 
            // Note the length of the queue
            //Deque until you find the character.
            //Mark the character for the dequed character as false;
            if(charMap.containsKey(c) && charMap.get(c)) {
                maxLen = Math.max(charQueue.size(), maxLen);

                while(!charQueue.isEmpty() && charQueue.peek() != c) {
                    System.out.println("Peek " + charQueue.peek());
                    charMap.put(charQueue.remove(), false);
                }
            }

            //If the character is not found, add to the queue and map
            else {
                charMap.put(c, true);
                charQueue.add(c);
            }

            System.out.println("charQueue size at end of  c " + charQueue.size() + " " + c);

        }

        if(!charQueue.isEmpty()) {
            maxLen = Math.max(charQueue.size(), maxLen);
        }

        return maxLen;
    }
}