import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* MD5Hash class implements HashFunction and contains a method to hash a string using the MD5 algorithm.
* 
* @author           Toby Flynn
*/
public class MD5Hash implements HashFunction {
    
    public MD5Hash() {
        
    }
    
    /**
    * Method that hashes a given string using the MD5 algorithm.
    * 
    * @param        password        The string to be hashed
    * @return                       The hash as a string
    */
    public String hashPassword(String password) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
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
