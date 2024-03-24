class PenaltyShop {

    public static void main(String[] args) {
        PenaltyShop penaltyShop = new PenaltyShop();
        penaltyShop.bestClosingTime("YYNY");
        System.out.println("Prefix way :::::: ");

        penaltyShop.findPenalty("YYNY");

    }

    public int bestClosingTime(String customers) {
        int minPenalty = Integer.MAX_VALUE;
        int minPenaltyIndex = 0;

        for(int i = 0; i<customers.length(); i++) {
            int penalty = 0;
            for(int j=0; j<i; j++) {
                if(customers.charAt(j)=='N'){
                    penalty++;
                }
            }
            for(int j=i; j < customers.length(); j++) {
                if(customers.charAt(j) == 'Y') {
                    penalty++;
                }
            }
            if(penalty < minPenalty) {
                minPenalty = penalty;
                minPenaltyIndex = i;
            }
            System.out.println("i " + i + " Min Penalty " + minPenalty + " min Penalty Index " + minPenaltyIndex);
        }

        if(minPenaltyIndex == customers.length()-1 && minPenalty > 0) {
            return customers.length();
        }

        System.out.println("Final Min Penalty " + minPenalty + " min Penalty Index " + minPenaltyIndex);
        return minPenaltyIndex;

    }

    public void findPenalty(String customers) {
        int[] nPrefixCount = new int[customers.length()+1];
        int[] ySuffixCount = new int[customers.length()+1];

        int prevCount = 0;
        for(int i=0; i<customers.length(); i++) {
            if(customers.charAt(i) == 'N'){
                nPrefixCount[i] = prevCount++ ;
            } else {
                nPrefixCount[i] = prevCount;
            }
        }
        nPrefixCount[customers.length()] = prevCount;

        prevCount =0;
        ySuffixCount[customers.length()] = 0;
        for(int i=customers.length()-1; i>=0; i--) {
            if(customers.charAt(i) == 'Y') {
                ySuffixCount[i] = ++prevCount;
            } else {
                ySuffixCount[i] = prevCount;
            }
        }

        int minPenalty = Integer.MAX_VALUE;
        int minPenaltyIndex = 0;

        for(int i=0; i<customers.length()+1; i++) {
            int penalty = nPrefixCount[i] + ySuffixCount[i];
            if( penalty < minPenalty){
                minPenalty = penalty;
                minPenaltyIndex = i;
            }
            System.out.println("i " + i + " Min Penalty " + minPenalty + " min Penalty Index " + minPenaltyIndex);
        }

        System.out.println("Final Min Penalty " + minPenalty + " min Penalty Index " + minPenaltyIndex);

    }
}