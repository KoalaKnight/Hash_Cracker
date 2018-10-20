/**
* HashCracker class is the class containing the main method and is used to either import a word list or crack a list of hashes.
* 
* @author           Toby Flynn
*/
public class HashCracker {
    
    public static void crackHashes(String hashingFunction, String dictionaryPath, String passwordFile, String outputFile) {
    
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
        System.out.println("To crack a list of hashes that use a certain hashing function: java HashCracker c [md5/sha1/...] [dictionaryFile] [inputFile] [outputFile]");
        System.out.println("Supported hashing functions: MD5 [md5], ");
    }
    
    //args: [i] [md5/sha1/...] [inputFile] [outputFile]
    //args: [c] [md5/sha1/...] [dictionaryFile] [inputFile] [outputFile]
    public static void main(String[] args) {
        if(args.length != 0) {
            if(args[0].equals("i")) {
                if(args.length == 4) {
                    importWordList(args[1], args[2], args[3]);
                } else {
                    printUsage();
                    System.exit(-1);
                }
            } else if(args[0].equals("c")) {
                if(args.length == 5) {
                    crackHashes(args[1], args[2], args[3], args[4]);
                } else {
                    printUsage();
                    System.exit(-1);
                }
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
