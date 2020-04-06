/**************************************************************           
* Purpose/Description: Finds the specified Fibonacci number         
* Author’s Panther ID: 5864879           
* Certification:    I hereby certify that this work is my own and none of it
* is the work of    any other person.          
**************************************************************/ 

/**
 * 
 * @author Ronan M Kelly
 */
import java.math.*;
public class Fibonacci {
    
    public static void main(String[] args) {
        int term = 250;
        System.out.printf("The %dth Fibonacci term is...\n%s\n", term,
                fibonacci(term));      
    }    
    
    /**
     * Calculates the nth Fibonacci term using the matrix technique
     * Uses the matrix identity by Donald E. Knuth
     * <br>
     * | <strong>F</strong>n+1, <strong>F</strong>n     | <br>                                           
     * | <strong>F</strong>n,   <strong>F</strong>n-1   |
     * <br><br>
     * is equal to
     * <br><br>
     * | <strong>1</strong>, <strong>1</strong>     | ^ n<br>                                           
     * | <strong>1</strong>,   <strong>0</strong>   |
     * <br><br>
     * To find the Fibonacci term <em>n</em>, the matrix must be calculated
     * to the power of <em>n-1</em> then the number in the first row and column 
     * Q[0, 0] is the <em>n</em>th Fibonacci term
     * <br>
     * This method of calculating the <em>n</em>th Fibonacci term is sub-linear 
     * ( O(logn) ) because the method used to raise the matrices to certain powers
     * uses the exponentiation by squaring technique in which f(x^n) = f(x ^ (n / 2))^2
     * which can be done recursively in logn steps
     *
     *
     * @param index
     * @return 
     */
    public static BigInteger fibonacci(int index) 
    {
        System.out.println();
        BigInteger[][] m = new BigInteger[2][2];
        m[0][0] = BigInteger.ONE;
        m[0][1] = BigInteger.ONE;
        m[1][0] = BigInteger.ONE;
        m[1][1] = BigInteger.ZERO;
        
        BigInteger[][] res = raiseMatrixToPower(m, index - 1);
        return res[0][0];
    }
    
    /**
     * Multiplies two 2x2 matrices in O(1) time
     * @param n first 2x2 matrix
     * @param m second 2x2 matrix
     * @return the result of both matrices being multiplied
     */
    private static BigInteger[][] multiplyMatrix(BigInteger[][] n, BigInteger[][] m) {
        BigInteger x1, x2, x3, x4;
        BigInteger y1, y2, y3, y4;
        BigInteger r1, r2, r3, r4;
        
        x1 = n[0][0];
        x2 = n[0][1];
        x3 = n[1][0];
        x4 = n[1][1];
        
        y1 = m[0][0];
        y2 = m[0][1];
        y3 = m[1][0];
        y4 = m[1][1];
        
        r1 = x1.multiply(y1).add(x2.multiply(y3));
        r2 = x1.multiply(y2).add(x2.multiply(y4));
        r3 = x3.multiply(y1).add(x4.multiply(y3));
        r4 = x3.multiply(y2).add(x4.multiply(y4));
        
        BigInteger[][] result = new BigInteger[2][2];
        result[0][0] = r1;
        result[0][1] = r2;
        result[1][0] = r3;
        result[1][1] = r4;
        
        return result;
    }
    
    /**
     * Raises a matrix to a specified power using the exponentiation by squaring 
     * method which is O(logn) as the power is halved every recursive call
     * @param m the matrix to be multiplied by its self <em>n</em> times
     * @param power the power to raise the matrix to
     * @return the matrix to the <em>n</em>th power
     */
    private static BigInteger[][] raiseMatrixToPower(BigInteger[][] m, int power) { 
           if (power <= 1) {
               return m;
           }
           //power is an even integer
           else if (power % 2 == 0){
               return raiseMatrixToPower(multiplyMatrix(m, m), power / 2);
           }
           //power is an odd integer
           else 
           {
               return multiplyMatrix(m, raiseMatrixToPower(multiplyMatrix(m, m),
                       (power - 1) / 2));
           }
    }
}
