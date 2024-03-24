package hard;

import java.util.Arrays;

public class SudukuSolver {

        boolean[][] rowValArr = new boolean[9][9];
        boolean[][] colValArr = new boolean[9][9];
        String[] gridValue = new String[9];
        char[][] solvedBoard = new char[9][9];
        boolean solved = false;

        public static void main(String[] args){
            SudukuSolver sudukuSolver = new SudukuSolver();
            char[][] board = {{'.','5','.','.','.','4','.','.','2'},
                    {'.','.','.','7','.','.','3','.','.'},
                    {'.','1','.','.','3','2','.','.','.'},
                    {'8','.','3','.','.','.','.','.','9'},
                    {'.','9','7','.','8','.','6','3','.'},
                    {'5','.','.','.','.','.','2','.','8'},
                    {'.','.','.','2','7','.','.','6','.'},
                    {'.','.','6','.','.','8','.','.','.'},
                    {'9','.','.','1','.','.','.','4','.'}};
            sudukuSolver.solveSudoku(board);
        }

        public void solveSudoku(char[][] board) {
            Arrays.fill(gridValue, "");
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if(board[i][j] != '.'){
                        int num = board[i][j]-'0';
                        rowValArr[num-1][i]= true;
                        colValArr[num-1][j] = true;

                        int gridIndex = (i/3)*3 + (j/3);
                        System.out.println("Populating num " + num + " at grid index " + gridIndex);
                        gridValue[gridIndex] = gridValue[gridIndex] + num;
                    }
                }
            }
            System.out.println("Row Arr " + Arrays.deepToString(rowValArr));
            System.out.println("Col Arr " + Arrays.deepToString(colValArr));
            System.out.println("Grid Arr " + Arrays.deepToString(gridValue));

            populate(board, 0, 0);
            System.out.println("Solved board " + Arrays.deepToString(solvedBoard));
        }

        public void populate(char[][] board, int row, int col) {

            if(row == 9 || col == 9){
                System.out.println("Finished all cells");
                for(int i=0; i<9; i++){
                    for(int j=0; j<9; j++){
                        if(board[i][j] == '.'){
                            if(!solved ) {
                                solvedBoard = new char[9][9];
                            }
                            return;
                        }
                        if(!solved) {
                            solvedBoard[i][j] = board[i][j];
                        }
                    }
                }
                solved = true;
                return;
            }

            if(solved){
                return;
            }

            if(board[row][col] != '.'){
                //move to the next cell
                populate(board, row+ (col+1)/9, (col+1)%9);
                return;
            }

            //Populate with possible numbers
            int gridIndex = (row/3)*3 + (col/3);
            for(int i=0; i<9; i++){

                if(!rowValArr[i][row] && !colValArr[i][col] && !gridValue[gridIndex].contains("" + (i+1))){
                    //set
                    board[row][col] = (char)('0' + (i+1));
                    rowValArr[i][row] = true;
                    colValArr[i][col] = true;
                    String prevGridValue = gridValue[gridIndex];
                    gridValue[gridIndex] = prevGridValue + (i+1);

                    //move to the next cell
                    populate(board, row+ (col+1)/9, (col+1)%9);
                    System.out.println("After setting board " + Arrays.deepToString(board));
                    //unset
                    board[row][col] = '.';
                    rowValArr[i][row] = false;
                    colValArr[i][col] = false;
                    gridValue[gridIndex] = prevGridValue;

                }

            }

        }

}
