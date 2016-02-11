package CryptoSysteme;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Dylan
 */
public class Pallier extends CryptoSysteme {
    
    private final int CERTAINTY = 64; 
    private int modLength = 512; 
    
    public Pallier(){
        generateKeys();
    }
    
    @Override
    public void generateKeys(){
        BigInteger p = new BigInteger(modLength / 2, CERTAINTY, new Random());     // a random prime
        BigInteger q;
        do {
            q = new BigInteger(modLength / 2, CERTAINTY, new Random()); // a random prime (distinct from p)
        } while (q.compareTo(p) == 0);
        super.setPk(p.multiply(q));
        super.setSk((p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE))));
    }

    @Override
    public BigInteger encrypter(BigInteger m) {
        if (m.compareTo(BigInteger.ZERO) < 0){
            return null;
        }
        BigInteger r;
        do {
            r = new BigInteger(modLength, new Random());
        } while (r.compareTo(super.getPk()) >= 0 || r.gcd(super.getPk()).intValue() != 1);
        BigInteger debut = super.getPk().add(BigInteger.ONE).modPow(m, super.getPk().multiply(super.getPk()));
        BigInteger fin = r.modPow(super.getPk(), super.getPk().multiply(super.getPk()));
        return debut.multiply(fin).mod(super.getPk().multiply(super.getPk()));
    }

    @Override
    public BigInteger decrypter(BigInteger c) {
        if (c.compareTo(BigInteger.ZERO) < 0 /*|| c.compareTo(super.getPk().multiply(super.getPk())) >= 0 || c.gcd(super.getPk().multiply(super.getPk())).intValue() != 1*/){
            return null;
        }
        BigInteger h = c.modPow(super.getSk(),super.getPk().multiply(super.getPk()));
        BigInteger i = h.subtract(BigInteger.ONE).divide(super.getPk());
        BigInteger mu = super.getSk().modPow(new BigInteger("-1"), super.getPk());
        return i.multiply(mu).mod(super.getPk());
    }
    
}