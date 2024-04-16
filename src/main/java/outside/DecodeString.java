import java.util.*;

class DecodeString {

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        decodeString.decodeString("3[a2[c]]");
        decodeString.decodeString("3[a]2[bc]");
        decodeString.decodeString("2[abc]3[cd]ef");
        decodeString.decodeString("23[a]56[b]89[c]");
    }

    public void decodeString(String s) {
        Stack<String> myStack = new Stack();
        Stack<Integer> valStack = new Stack();
        int nums =0;
        for(Character c : s.toCharArray()) {
            if(c >= '0' && c <= '9') {
                nums = nums*10 + c - '0';
            } else if(c == '[') {
                valStack.push(nums);
                nums=0;
                myStack.push("" + c);
            } else if(c == ']') {
                String temp = "";
                while(!myStack.peek().equals("[")) {
                    temp = myStack.pop() + temp;
                }
                myStack.pop();
                int val = valStack.pop();
                String repeatedStr = "";
                while(val >0) {
                    repeatedStr += temp;
                    val --;
                }
                myStack.push(repeatedStr);
            } else {
                myStack.push("" + c);
            }
        }
        String result = "";
        while(!myStack.isEmpty()) {
            result = myStack.pop() + result;
        }
        System.out.println("Optimal solution " + result);

        decode(s, 0, new Stack<>(), new Stack<>(), "");
    }



    public String decode(String s,int index, Stack<String> myStack, Stack<Integer> valStack, String prevChar) {

        if(!s.contains("]")) {
            System.out.println("Answer " + s);
            return s;
        }

        if(s.charAt(index) == '['){
            myStack.push("");
            prevChar = "[";
        } else if(s.charAt(index) == ']') {
            String str = myStack.pop();
            int val = valStack.pop();
            String result = "";
            String original = val + "\\[" + str + "\\]";
            while(val > 0){
                result +=  str;
                val --;
            }
            s = s.replaceFirst(original, result);
            index = -1;
            myStack = new Stack<>();
            valStack = new Stack<>();
            prevChar = "//]";
        } else if(s.charAt(index) >= '0' && s.charAt(index) <= '9'){
            int val = Integer.parseInt("" + s.charAt(index));
            if(prevChar.equals("val")) {
                val = Integer.parseInt("" + valStack.pop() + s.charAt(index));
            }
            valStack.push(val);
            prevChar = "val";
        } else if(!myStack.isEmpty()) {
            String temp = myStack.pop();
            temp = temp + s.charAt(index);
            myStack.push(temp);
            prevChar = "";
        }

        return decode(s, index+1, myStack, valStack, prevChar);
    }
}