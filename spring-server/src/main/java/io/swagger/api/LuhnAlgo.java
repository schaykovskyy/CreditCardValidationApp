package io.swagger.api;

public class LuhnAlgo {
    public static boolean isCCValid(String ccNumber){
        int sum  = 0;
        boolean alternate = false;
        
        for(int i = ccNumber.length() - 1; i >= 0; i--){
            int digit = Integer.parseInt(ccNumber.substring(i, i+1));
            if (alternate){
                digit *= 2;
                if(digit > 9){
                    digit= (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum%10 == 0);
    }
    
}
