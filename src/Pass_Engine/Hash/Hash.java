package Pass_Engine.Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Hash {

    private String hint;
    private String salt;


    public Hash(String h, String s){
        this.hint = h;
        this.salt = s;
    }

    public String cutHash(String comp_hash, int minLen, int maxLen, long Key){
        Random rand4Key = new Random(Key);
        int end = minLen + rand4Key.nextInt(maxLen-minLen);
        String hash = comp_hash.substring(0,end);
        return hash;
    }

    public String hash() {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String passWithSalt = hint + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < passHash.length; i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
