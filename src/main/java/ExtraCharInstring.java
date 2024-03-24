import java.util.*;

public class ExtraCharInstring {

    static int[] dp;
    public static void main(String[] args) {
        ExtraCharInstring extraCharInstring = new ExtraCharInstring();
        String[] dictionary = {"ix","qoqw","ax","ar","v","hxpl","nxcg","thr","kod","pns","cdo","euy","es","rf","bxcx","xe","ua","vws","vumr","zren","bzt","qwxn","ami","rrbk","ak","uan","g","vfk","jxmg","fhb","nqgd","fau","rl","h","r","jxvo","tv","smfp","lmck","od"};
        Set<String> mySet = new HashSet<>();
        Collections.addAll(mySet,dictionary);
        String s= "aakodubkrlauvfkzje";
        dp = new int[s.length()];
            Arrays.fill(dp,-1);

        System.out.println(extraCharInstring.minExtraChar(s, dictionary));
        System.out.println(extraCharInstring.findExtraChar(s, mySet, 0));

        s= "dwmodizxvvbosxxw";
        String[] dictionary2 = {"ox","lb","diz","gu","v","ksv","o","nuq","r","txhe","e","wmo","cehy","tskz","ds","kzbu"};

        mySet = new HashSet<>();
        Collections.addAll(mySet, dictionary2);
        dp = new int[s.length()];
        Arrays.fill(dp,-1);
        System.out.println(extraCharInstring.findExtraChar(s, mySet,0));

    }

    public int minExtraChar(String s, String[] dictionary) {
        int result = s.length();
        String temp = s;
        for (int i= dictionary.length-1; i>=0; i--) {
            if (s.contains(dictionary[i])) {
                int foundLength = dictionary[i].length();
                int index = s.indexOf(dictionary[i]);
                while (index != -1) {
                    result -= foundLength;
                    s = s.replaceFirst(dictionary[i], "-1");
                    index = s.indexOf(dictionary[i]);
                }
            }
        }
        return result;
    }

    public int findExtraChar(String s, Set<String> mySet, int startIndex){
        if(startIndex > s.length()-1){
            return 0;
        }
        if(dp[startIndex] != -1) {
            return dp[startIndex];
        }

        int result = s.length();
        String currStr = "";
        for(int i = startIndex; i < s.length(); i++) {
            currStr += s.charAt(i);
            int count ;
            if(mySet.contains(currStr)) {
                count = 0;
            } else {
                count = currStr.length();
            }
            count += findExtraChar(s, mySet, i+1);
            result = Math.min(count, result);
        }
        dp[startIndex] = result;
        return result;
    }

    public int minExtraCharOptimized(String s, String[] dictionary) {
        int[] d = new int[s.length() + 1];
        d[s.length()] = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            String subS = s.substring(i);
            d[i] = d[i + 1] + 1;
            for (int j = 0; j < dictionary.length; j++) {
                if (subS.startsWith(dictionary[j])) {
                    d[i] = Math.min(d[i], d[i + dictionary[j].length()]);
                }
            }
        }
        return d[0];
    }
}
