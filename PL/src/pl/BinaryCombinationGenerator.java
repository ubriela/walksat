/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

/**
 *
 * @author ubriela
 */
 
public class BinaryCombinationGenerator {
 
    public static int[] next(int[] current, int radix) {
        int[] n= new int[current.length];
         
        for (int i= n.length; i-- > 0;) {
            if (current[i]+1 == radix)
                n[i]= 0;
            else {
                n[i]= current[i]+1;
                for (; i-- > 0; n[i]= current[i]);
                return n;
            }
        }
        return null;
    }
}