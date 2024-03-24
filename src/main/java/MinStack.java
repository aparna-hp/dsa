import java.util.LinkedList;

public class MinStack {

    LinkedList<Long> minStack;
    int size =0;

    public MinStack(LinkedList<Long> minStack, int size) {
        this.minStack = minStack;
        this.size = size;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("abb"));
    }

    public static boolean isPalindrome(String s) {
        int start =0;
        int end = s.length()-1;
         while(start <= end) {
             System.out.println("s.charAt(start) " + s.charAt(start) + " s.charAt(end)" + s.charAt(end)
                     + " " + (s.charAt(start) != s.charAt(end)));

             if(s.charAt(start) != s.charAt(end)) {
                System.out.println("String is not a palindrome " + false);
                return false;
            }
            start ++;
            end --;
        }
        return true;
    }

}
