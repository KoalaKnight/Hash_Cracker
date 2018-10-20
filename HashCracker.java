import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
* HashCracker class is the class containing the main method and is used to either import a word list or crack a list of hashes.
* 
* @author           Toby Flynn
*/
public class HashCracker {
    
    /**
    * Method that tries to found corresponding passwords that produced the given hashes.
    * <p>
    * The results of this method will 
    * 
    * @param        dictionaryPath          Path to the dictionary file (that was created by this program)
    * @param        hashesFile            Path to the file containing hashes (formatted so that there is a new line for each hash)
    * @param        outputFile              Path to the file to save the results of this function to
    */
    public static void crackHashes(String dictionaryPath, String hashesFile, String outputFile) {
        //Load the hash-password dictionary
        HashDictionary hashDic = new HashDictionary(dictionaryPath);
        //Load the hashes to be cracked, cycle through them one by one and saving the corresponding password to disk
        //Format of output file line [hash]:[password]
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            //Read in hash list
            in = new BufferedReader(new FileReader(hashesFile));
            //Set up file to output to
            out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
            String hash;
            //Loop through all the words, hash them then add them to the dictionary (HashMap)
            while((hash = in.readLine()) != null) {
                String password = hashDic.getPassword(hash.toLowerCase());
                if(password != null) {
                    out.println(hash + ":" + password);
                } else {
                    out.println("NO PASSWORD FOUND FOR " + hash);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Close IO
                in.close();
                out.close();
            } catch(Exception e) {
            
            }
        }
    }
    
    /**
    * Method that imports a wordlist, creating a dictionary of passwords and hashes.
    * 
    * @param        hashingFunction         String representation of hashing function to use
    * @param        wordListPath            Path to the word list
    * @param        outputFile              Path to where to write the new dictionary to
    */
    public static void importWordList(String hashingFunction, String wordListPath, String outputFile) {
        //Get the hashing function
        HashFunction hashFunc = getHashingFunction(hashingFunction);
        if(hashFunc == null) {
            System.out.println("Unsupported hashing function. Use one of the following options: md5, ");
            System.exit(-1);
        }
        //Create dictionary
        DictionaryCreator dictCreate = new DictionaryCreator(hashFunc, wordListPath, outputFile);
    }
    
    /**
    * Method that returns the specified HashFunction.
    * 
    * @param        hashingFunction         The string that represents a hashing function
    * @return                               The hashing function
    */
    private static HashFunction getHashingFunction(String hashingFunction) {
        HashFunction result;
        
        if(hashingFunction.equals("md5")) {
            result = new MD5Hash();
        } else {
            result = null;
        }
        
        return result;
    }
    
    /**
    * Method that prints a message about how to use this program.
    */
    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("To create a dictionary, using a certain hashing function, by importing a word list (.txt): java HashCracker i [md5/sha1/...] [wordListPath] [outputFile]");
        System.out.println("To crack a list of hashes using a prepared hash-password dictionary (created using this program): java HashCracker c [dictionaryFile] [inputFile] [outputFile]");
        System.out.println("Supported hashing functions: MD5 [md5], ");
    }
    
    //args: [i] [md5/sha1/...] [inputFile] [outputFile]
    //args: [c] [dictionaryFile] [inputFile] [outputFile]
    public static void main(String[] args) {
        if(args.length == 4) {
            if(args[0].equals("i")) {
                importWordList(args[1], args[2], args[3]);
            } else if(args[0].equals("c")) {
                crackHashes(args[1], args[2], args[3]);
            } else {
                printUsage();
                System.exit(-1);
            }
        } else {
            printUsage();
            System.exit(-1);
        }
    }
}
