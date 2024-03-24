package Easy;

import java.util.Arrays;

public class SpiralMatrix {

    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        System.out.println(Arrays.deepToString(spiralMatrix.generateMatrix(21)));
    }

    public int[][] generateMatrix(int A) {
        int[][] matrix = new int[A][A];

        int rowMin = 0;
        int rowMax = A - 1;
        int colMin = 0;
        int colMax = A - 1;
        int num = 1;

        while (num <= A * A) {
            //top
            for (int j = colMin; j <= colMax && num <= A * A; j++) {
                System.out.println("Populating top::" + rowMin + "," + j + "=" + num);
                matrix[rowMin][j] = num;
                num++;
            }

            //right
            for (int i = rowMin + 1;  i <= rowMax && num <= A * A; i++) {
                System.out.println("Populating right::" + i + "," + colMax + "=" + num);
                matrix[i][colMax] = num;
                num++;
            }

            //bottom
            for (int j = colMax - 1; j >= colMin && num <= A * A; j--) {
                System.out.println("Populating bottom::" + rowMax + "," + j + "=" + num);
                matrix[rowMax][j] = num;
                num++;
            }

            //left
            for (int i = rowMax - 1;  i > rowMin && num <= A * A; i--) {
                System.out.println("Populating left::" + i + "," + colMin + "=" + num);
                matrix[i][colMin] = num;
                num++;
            }

            rowMin++;
            rowMax--;
            colMin++;
            colMax--;
        }

        return matrix;
    }

}
