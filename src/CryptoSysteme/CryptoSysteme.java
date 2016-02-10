package CryptoSysteme;

import java.math.BigInteger;

/**
 *
 * @author Dylan
 */
public abstract class CryptoSysteme {
    
    private BigInteger pk,sk;
    
    public abstract void generateKeys();
    public abstract BigInteger encrypter(BigInteger m);
    public abstract BigInteger decrypter(BigInteger c);

    public BigInteger getPk() {
        return pk;
    }

    public void setPk(BigInteger pk) {
        this.pk = pk;
    }

    public BigInteger getSk() {
        return sk;
    }

    public void setSk(BigInteger sk) {
        this.sk = sk;
    }
}
