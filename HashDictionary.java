import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
* HashDictionary is a class that loads a hash and password dictionary from disk.
* <p>
* It can then be used to check if the password that produced a hash is known.
*/
public class HashDictionary {
    
    Map<String,String> dict;
    
    /**
    * Constructor method that loads the hash dictionary from disk.
    * <p>
    * The hash dictionary is loaded from a serialized HashMap that was created by the DictionaryCreater class.
    * 
    * @param        hashDictPath            Path to where the serialized HashMap is located
    */
    public HashDictionary(String hashDictPath) {
        dict = new HashMap<>();
        try {
            FileInputStream fileIn = new FileInputStream(hashDictPath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            dict = (HashMap) objectIn.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Method that returns the password that produced the provided hash or null if the password is not know.
    * 
    * @param        hash                    The hash to look up in the dictionary
    * @return                               The password that produced the hash or null if the password is not found
    */
    public String getPassword(String hash) {
        return (String) dict.get(hash);
    }
}
