import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
* DictionaryCreator is a class used to create a dictionary of passwords and their hashes, saving it to disk to be reused.
* 
* @author           Toby Flynn
*/
public class DictionaryCreator {
    
    private HashFunction hashFunc;
    private Map<String,String> dict;
    
    /**
    * Constructor method which creates a dictionary of hashes and passwords.
    * <p>
    * It also saves the dictionary to disk (by serializing the HashMap that is the dictionary).
    * 
    * @param        hashFunction            An object that implements HashFunction that will be used to hash the passwords
    * @param        wordListPath            Path to the word list
    * @param        hashDictPath            Path to where the dictionary will be stored (by convention it should have a .ser extension)
    */
    public DictionaryCreator(HashFunction hashFunction, String wordListPath, String hashDictPath) {
        hashFunc = hashFunction;
        dict = new HashMap<>();
        try {
            //Read in the word list
            BufferedReader in = new BufferedReader(new FileReader(wordListPath));
            String password;
            //Loop through all the words, hash them then add them to the dictionary (HashMap)
            while((password = in.readLine()) != null) {
                dict.put(hashFunc.hashPassword(password), password);
            }
            in.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Serialize the HashMap so it can be used again without having to recomputed the hashes
        try {
            FileOutputStream fileOut = new FileOutputStream(hashDictPath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(dict);
            objectOut.close();
            fileOut.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Method that returns the dictionary of hashes and passwords as a HashMap.
    * 
    * @return                           The dictionary as a HashMap
    */
    public Map<String,String> getDictionary() {
        return dict;
    }
}
