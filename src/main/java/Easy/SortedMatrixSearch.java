package Easy;

public class SortedMatrixSearch {

    public static void main(String[] args) {
        SortedMatrixSearch sortedMatrixSearch = new SortedMatrixSearch();
        int[][] A = {
                {5, 6, 7, 8, 12, 13, 14},
  {15, 20, 23, 23, 23, 31, 32},
  {35, 37, 37, 37, 44, 45, 45},
  {48, 50, 52, 55, 55, 60, 61},
  {62, 63, 64, 66, 67, 68, 68},
  {69, 70, 72, 78, 81, 81, 83},
  {90, 93, 93, 96, 96, 98, 99}
        };
        System.out.println(sortedMatrixSearch.searchMatrix(A, 79));
    }

        public int searchMatrix(int[][] A, int B) {
            //find the first row with rightmost value
            // which is just greater than B.

            int N = A.length;
            int M = A[0].length;

            int row = 0;
            int col = M-1;

            while(row < N && col >=0) {
                System.out.println("Row " + row + " col " + col);
                int ans = findTarget(A, B, 0, col);
                if(ans != -1){
                    return 1;
                }
                row ++;
                col--;
            }

            if(row < N) {
               if(-1 != findTarget(A, B, row, ++col)) {
                   return 1;
               }
            }

            if(col >=0){
                if(findTarget(A, B, --row, col) != -1){
                    return 1;
                }
            }

            return 0;
        }

        public int findTarget(int[][] A, int B, int row, int col) {

            int[] sorted = new int[A.length + A[0].length];
            int index = 0;
            for(int i= 0; i <= col; i++){
                sorted[index++] = A[row][i];
            }

            for(int i=row; i<A.length; i++){
                sorted[index++] = A[i][col];
            }

            int low = 0;
            int high = index-1;

            while(low <= high){
                int mid = low + (high-low)/2;
                if(sorted[mid] == B){
                    return mid;
                }

                if(low == high){
                    return -1;
                }

                if(sorted[mid] < B){
                    low = mid +1;
                } else {
                    high = mid-1;
                }
            }
            return -1;
        }

}
