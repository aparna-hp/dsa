import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeSimilarItems {

    public static void main(String[] args) {
        /**
         * Just maintain an array with index from 0 to high value.
         * or a map .. Populate the map/array for items1.
         * While population item2, populate the map/array by adding the same item value
         * Print non zero items. O (n+m)
         */
        MergeSimilarItems solution = new MergeSimilarItems();
        int[][] items1 = {{1,1},{4,5},{3,8}};
        int [][] items2 = {{3,1},{1,5}};
        List<List<Integer>> result = solution.mergeSimilarItems(items1, items2);
        for(List<Integer> integerList : result) {
            System.out.println();
            for(Integer val : integerList){
                System.out.print(val + ",");
            }
        }
    }

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        List<List<Integer>> result = new ArrayList<>();
        int i =0;
        int j=0;

        items1 = sortItems(items1);
        System.out.println("Sorted item 1 array " + Arrays.deepToString(items1));

        items2 = sortItems(items2);
        System.out.println("Sorted item 2 array " + Arrays.deepToString(items2));


        while(i < items1.length && j < items2.length) {
            List<Integer> temp = new ArrayList<>();
            if(items1[i][0] < items2[j][0]){
                temp.add(items1[i][0]);
                temp.add(items1[i][1]);
                i++;
            } else if(items2[j][0] < items1[i][0]){
                temp.add(items2[j][0]);
                temp.add(items2[j][1]);
                j++;
            } else {
                temp.add(items1[i][0]);
                temp.add(items2[j][1] + items1[i][1]);
                i++;
                j++;
            }
            result.add(temp);
        }

        while(i < items1.length) {
            result.add(List.of(items1[i][0], items1[i][1]));
            i++;
        }

        while(j < items2.length) {
            result.add(List.of(items2[j][0], items2[j][1]));
            j++;
        }

        return result;
    }

    public int[][] sortItems(int[][] items){
        if(items.length == 0 || items.length == 1){
            return items;
        }
        int low = 0;
        int high = items.length;
        int mid = (high-low)/2;

        int[][] left = new int[mid][2];
        int[][] right = new int[high-mid][2];

        for(int i=0; i < mid; i++){
            left[i][0] = items[i][0];
            left[i][1] = items[i][1];
        }

        for(int i=0; i< high-mid; i++){
            right[i][0] = items[mid+i][0];
            right[i][1] = items[mid+i][1];
        }

        left = sortItems(left);
        right = sortItems(right);

        return merge(left, right);
    }

    public int[][] merge(int[][] left, int[][] right){
        int[][] result = new int[left.length + right.length][2];

        int i =0;
        int j=0;
        int k =0;

        while(i < left.length && j < right.length){
            if(left[i][0] < right[j][0]){
                result[k][0] = left[i][0];
                result[k][1] = left[i][1];
                k++;
                i++;
            } else if(left[i][0] > right[j][0]){
                result[k][0] = right[j][0];
                result[k][1] = right[j][1];
                k++;
                j++;
            } else {
                result[k][0] = left[i][0];
                result[k][1] = left[i][1];
                k++;
                i++;
                j++;
            }
        }

        while(i < left.length) {
            result[k][0] = left[i][0];
            result[k][1] = left[i][1];
            k++;
            i++;
        }

        while(j< right.length){
            result[k][0] = right[j][0];
            result[k][1] = right[j][1];
            k++;
            j++;
        }

        return result;
    }

}
