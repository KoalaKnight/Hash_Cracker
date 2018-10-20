import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* SHA1Hash class implements HashFunction and contains a method to hash a string using the SHA-1 algorithm.
* 
* @author           Toby Flynn
*/
public class SHA1Hash implements HashFunction {
    
    public SHA1Hash() {
        
    }
    
    /**
    * Method that hashes a given string using the SHA-1 algorithm.
    * 
    * @param        password        The string to be hashed
    * @return                       The hash as a string
    */
    public String hashPassword(String password) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hash;
    }
}
