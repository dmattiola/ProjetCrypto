package Personne;

import CryptoSysteme.CryptoSysteme;

/**
 *
 * @author Dylan
 */
public class Bob extends Thread {
    
    private CryptoSysteme cs;
    
    public Bob(CryptoSysteme cs){
        this.cs = cs;
    }
    
}