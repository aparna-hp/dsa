package medium;

public class LongestPalSubString {

    public static void main(String[] args){
        LongestPalSubString longestPalSubString = new LongestPalSubString();
        System.out.println(longestPalSubString.longestPalindrome("babca"));
    }
        public String longestPalindrome(String s) {
            String result = ""+s.charAt(0);
            for(int i=0; i<s.length(); i++) {
                StringBuilder sub = new StringBuilder(""+ s.charAt(i));
                for(int j=i+1; j<s.length(); j++) {
                    sub.append(s.charAt(j));
                    if(isPalindrome(sub.toString())){
                        if(result.length() < (j-i+1)){
                            result = sub.toString();
                        }
                    }
                }
            }
            return result;
        }

        public boolean isPalindrome(String s){
            int start = 0;
            int end = s.length()-1;
            while(start < end){
                if(s.charAt(start) != s.charAt(end)){
                    return false;
                }
                start ++;
                end --;
            }
            return true;
        }

}
