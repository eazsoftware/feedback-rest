package com.eaz.feedbackrest.util;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author javier
 */
public class FeedbackUtil {
    
    public static BigInteger nextRandomBigInteger() { 
        BigInteger n = BigInteger.valueOf(Long.MAX_VALUE);
        
        Random rand = new Random();
        BigInteger result = new BigInteger(n.bitLength(), rand);
        while( result.compareTo(n) >= 0 ) {
            result = new BigInteger(n.bitLength(), rand);
        }
        return result;
    }
    
}