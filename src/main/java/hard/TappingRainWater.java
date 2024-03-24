package hard;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TappingRainWater {

    public static void main(String[] args){
        TappingRainWater tappingRainWater = new TappingRainWater();
        //int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] nums = {4,2,0,3,2,5};
        System.out.println(tappingRainWater.trap(nums));
    }

        public int trap(int[] height) {
            Stack<Integer> stack = new Stack<>();
            int[] lle = new int[height.length];
            int[] rle = new int[height.length];

            for(int i=0; i<height.length; i++){
                while(!stack.isEmpty() && height[stack.peek()] <= height[i]){
                    stack.pop();
                }
                lle[i] = -1;
                if(!stack.isEmpty()) {
                    lle[i] = stack.peek();
                }
                stack.push(i);
            }

            stack = new Stack<>();
            for(int i=height.length-1; i>=0; i--){
                while(!stack.isEmpty() && height[stack.peek()] <= height[i]){
                    stack.pop();
                }
                rle[i] = -1;
                if(!stack.isEmpty()){
                    rle[i]=stack.peek();
                }
                stack.push(i);
            }

            int curr = 0;
            Set<String>  set = new HashSet<>();
            for(int i=1; i<height.length-1; i++){
                if(lle[i] != -1 && rle[i] != -1){
                    System.out.println("At index i " + i + " lle " + height[lle[i]] + " rle " + height[rle[i]]);
                    if(set.add(lle[i] + "," + rle[i])) {
                        int currHeight = Math.min(height[lle[i]], height[rle[i]]) - height[i];
                        int currWidth = rle[i] - lle[i] - 1;
                        System.out.println("currHeight " + currHeight + " currWidth " + currWidth);
                        curr += currHeight * currWidth;
                    }
                }
            }
            return curr;
        }

}
