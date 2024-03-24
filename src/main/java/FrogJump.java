import java.util.Arrays;

class FrogJump {

    int[] dp;
    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();
        int[] stones = {0,1,3,5,6,8,12,17};
                System.out.println(frogJump.canCross(stones));
    }

    public boolean canCross(int[] stones) {
        if(stones.length == 0) {
            return true;
        }

        if(stones[0] != 0) {
            return false;
        }

        dp = new int[stones.length];
        Arrays.fill(dp, -1);
        boolean ans = check(stones, 1,0,0);
        System.out.println(Arrays.toString(dp));
        return ans;
    }

    public boolean check(int[] stones, int index, int prevJump, int stoneIndex) {
        if(index >= stones.length){
            System.out.println("Return " +  (stones[stones.length -1]==stoneIndex) + " at index " + index );
            return stones[stones.length -1]==stoneIndex ;
        }

        boolean ans = false;

        System.out.println("stoneIndex + prevJump " + stoneIndex + prevJump + " stones[index] " + stones[index]);
        if(stoneIndex + prevJump-1 == stones[index]) {
            ans |=  check(stones, index+1, prevJump-1, stones[index]);
            System.out.println("Return " +  ans + " at index " + index );

        }

         if(stoneIndex + prevJump == stones[index]){
           ans |= check(stones, index+1, prevJump, stones[index]);
           System.out.println("Return " +  (stones[stones.length -1]==stoneIndex) + " at index " + index );

        }

         if(stoneIndex + prevJump+1 == stones[index] ) {
           ans |= check(stones, index+1, prevJump+1,stones[index]);
           System.out.println("Return " +  (stones[stones.length -1]==stoneIndex) + " at index " + index );

        }

         if(!ans) {
                ans |= check(stones, index + 1, prevJump, stoneIndex);
                System.out.println("Return " + (stones[stones.length - 1] == stoneIndex) + " at index " + index);
         }

            dp[index] = ans ? 1 : 0;
            return ans;

    }


}
