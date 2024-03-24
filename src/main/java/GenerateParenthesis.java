import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateParenthesis {

    public static void main(String[] args){
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        generateParenthesis.generate(3);
    }

    public void generate(int n) {
        List<String> result = new ArrayList<>();
        printParenthesis(n, "", result, 0, 0);
        System.out.println(Arrays.toString(result.toArray()));

        result = new ArrayList<>();
        generateParenthesis(n, 0, 0, "", result);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public void printParenthesis(int size, String temp, List<String> result, int open, int close) {
        if (close == size && open == size) {
            result.add(temp);
            return;
        }

        if (open < size) {
            printParenthesis(size, temp + "{", result, open + 1, close);
        }

        if (open > close) {
            printParenthesis(size,  temp+ "}", result, open, close + 1);
        }

    }

    public static void
    generateParenthesis(int n, int open, int close,
                        String s, List<String> ans)
    {

        // if the count of both open and close parentheses
        // reaches n, it means we have generated a valid
        // parentheses. So, we add the currently generated
        // string s to the final ans and return.
        if (open == n && close == n) {
            ans.add(s);
            return;
        }

        // At any index i in the generation of the string s,
        // we can put an open parentheses only if its count
        // until that time is less than n.
        if (open < n) {
            generateParenthesis(n, open + 1, close, s + "{",
                    ans);
        }

        // At any index i in the generation of the string s,
        // we can put a closed parentheses only if its count
        // until that time is less than the count of open
        // parentheses.
        if (close < open) {
            generateParenthesis(n, open, close + 1, s + "}",
                    ans);
        }
    }
}
