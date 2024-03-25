package hard;

import java.util.*;

public class NQueen {

        boolean[] colVal;
        boolean[] leftDiagonal;
        boolean[] rightDiagonal;

        public static void main(String[] args){
            NQueen nQueen = new NQueen();
            ArrayList<ArrayList<String>> solution = nQueen.solveNQueens(9);
            for(int i=0; i<solution.size(); i++){
                System.out.println(Arrays.toString(solution.get(i).toArray()));
            }
        }

        public ArrayList<ArrayList<String>> solveNQueens(int A) {
            colVal = new boolean[A];
            leftDiagonal = new boolean[2*A];
            rightDiagonal = new boolean[2*A];

            ArrayList<ArrayList<String>> ans = new ArrayList<>();
            List<List<Character>> curr = new ArrayList<>();

            List<Character> row = new ArrayList<>();
            for(int i=0; i<A; i++){
                row.add('.');
            }

            for(int i=0; i<A; i++){
                curr.add(new ArrayList<>(row));
            }

            compute(ans, curr, 0, A);
            return ans;
        }

        public void compute(ArrayList<ArrayList<String>> ans, List<List<Character>> curr, int index, int size){
            if(index == size){
                //Verify if all the rows contain Queen
                ArrayList<String> board = new ArrayList<>();
                for(int i=0; i<size; i++){
                    if(!curr.get(i).contains('Q')){
                        return;
                    }
                    StringBuilder row = new StringBuilder();
                    for(int j=0; j<size; j++){
                        row.append(curr.get(i).get(j));
                    }
                    board.add(row.toString());
                }
                //Add curr to ans
                ans.add(board);
                return;
            }

            for(int col =0; col < size; col++){
                if(!colVal[col] && !leftDiagonal[index+col] && !rightDiagonal[index-col+size]) {
                    //If safe set the values and the flags
                    curr.get(index).set(col,'Q');
                    colVal[col] = true;
                    leftDiagonal[index+col] = true;
                    rightDiagonal[index-col+size]=true;
                    //populate next row
                    compute(ans, curr, index+1, size);

                    //unset the values and flags
                    curr.get(index).set(col, '.');
                    colVal[col] = false;
                    leftDiagonal[index+col] = false;
                    rightDiagonal[index-col+size] = false;
                }
            }
        }


}
