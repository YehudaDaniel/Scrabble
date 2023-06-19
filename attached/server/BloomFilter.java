
package server;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.lang.Math;

public class BloomFilter {
    private final BitSet bitSets;
    List<MessageDigest> arrFunc= new ArrayList<>();


    public BloomFilter(int size, String...args) {
        bitSets=new BitSet(size);
        for(String funcName: args){
            try {
                MessageDigest md=MessageDigest.getInstance(funcName);
                arrFunc.add(md);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
    void add(String word) {
        for(MessageDigest funcName: arrFunc) {
            byte[] bts=funcName.digest(word.getBytes());
            BigInteger bi=new BigInteger(bts);
            int num=bi.intValue();
            num=Math.abs(num);
            num=num% bitSets.size();
            if(!(bitSets.get(num)))
                bitSets.flip(num);
        }
    }
    boolean contains(String word){
        for(MessageDigest funcName: arrFunc) {
            byte[] bts=funcName.digest(word.getBytes());
            BigInteger bi=new BigInteger(bts);
            int num=bi.intValue();
            num=Math.abs(num);
            num=num% bitSets.size();
            if(!bitSets.get(num))
                return false;
        }return true;
    }

    @Override
    public String toString() {
        StringBuilder st=new StringBuilder(bitSets.size());
        for(int i=0; i< bitSets.length(); i++){
            st.append(bitSets.get(i)? "1":"0");
        }return  st.toString();
    }
}
