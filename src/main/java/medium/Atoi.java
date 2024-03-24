package medium;

public class Atoi {


    public static void main(String[] args){
        Atoi atoi = new Atoi();
        System.out.println("Atoi =" + atoi.atoi("5121478262 8070067M75"));
    }
        // DO NOT MODIFY THE LIST. IT IS READ ONLY
        public int atoi(final String A) {
            String[] temp = A.split("\\s+");

            for(String split : temp){
                split = split.trim();
                if(split.length() == 0){
                    continue;
                }

                int result = 0;
                boolean isNeg = false;
                int index = 0;

                if(split.charAt(0) == '-') {
                    isNeg = true;
                    index = 1;
                } else if(split.charAt(0) == '+'){
                    index = 1;
                }

                while( index < split.length()){
                    char c = split.charAt(index);
                    if(c >= '0' && c <= '9'){
                        int digit = Integer.parseInt(c+ "");
                        if(result  > Integer.MAX_VALUE/10 -digit)  {
                            return Integer.MAX_VALUE;
                        }
                        if (result < Integer.MIN_VALUE/10 + digit){
                            return Integer.MIN_VALUE;
                        }
                        System.out.println("Result " + result);
                        result = result*10 + digit;
                        System.out.println("Result " + result);

                    } else {
                        break;
                    }
                    index++;
                }

                if(isNeg) {
                    result *= -1;
                }

                return result;
            }

            return 0;

        }


}
